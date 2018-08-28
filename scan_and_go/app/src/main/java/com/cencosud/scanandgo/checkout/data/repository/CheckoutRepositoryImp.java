package com.cencosud.scanandgo.checkout.data.repository;

import com.cencosud.scan_commons.product.data.repository.mapper.ProductEntityToDomainMapper;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.security.Decrypt;
import com.cencosud.scan_commons.security.data.local.SecurityPreferences;
import com.cencosud.scan_commons.security.domain.DecryptUseCase;
import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.checkout.data.entity.CheckoutResumeEntity;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.data.remote.CheckoutResumeApi;
import com.cencosud.scanandgo.checkout.data.remote.request.RequestCheckout;
import com.cencosud.scanandgo.checkout.data.remote.response.TransactionResponse;
import com.cencosud.scanandgo.checkout.data.repository.mapper.ResumeEntityToDomainMapper;
import com.cencosud.scanandgo.checkout.domain.model.CheckoutResume;
import com.cencosud.scanandgo.wallet.data.repository.mapper.CardToDomainMapper;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.data.entity.response.ResponseBaseEntity;
import com.google.gson.Gson;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by joseamaro on 05-06-18.
 */

public class CheckoutRepositoryImp implements CheckoutRepository {

    private final CheckoutResumeApi api;
    private final ResumeEntityToDomainMapper mapper;
    private final ProductEntityToDomainMapper productMapper;
    private final CardToDomainMapper cardMapper;
    private final CheckoutPreferences checkoutPreferences;
    private final DecryptUseCase decryptUseCase;

    public CheckoutRepositoryImp(CheckoutResumeApi api, ResumeEntityToDomainMapper mapper, ProductEntityToDomainMapper productMapper, CardToDomainMapper cardMapper, CheckoutPreferences checkoutPreferences, DecryptUseCase decryptUseCase) {
        this.api = api;
        this.mapper = mapper;
        this.productMapper = productMapper;
        this.cardMapper = cardMapper;
        this.checkoutPreferences = checkoutPreferences;
        this.decryptUseCase = decryptUseCase;
    }

    @Override
    public Observable<CheckoutResume> getPromotion(String rut, String email, String transactionId, String storeId, double subTotal, List<Product> products, List<Card> cards) {

        RequestCheckout requestCheckout = new RequestCheckout();
        requestCheckout.rut = rut.replace(".", "");
        requestCheckout.email = email;
        requestCheckout.transactionId = transactionId;
        requestCheckout.storeId = storeId;
        requestCheckout.subTotal = subTotal;

        if (cards != null) {
            requestCheckout.cards = cardMapper.reverseMap(cards);
        }

        String clave = decryptKey();

        if (products != null) {
            requestCheckout.products = productMapper.reverseMap(products);
        }

       String jsonRequest = new Gson().toJson(requestCheckout); // luego eliminar;


        return api.getPromotion(BuildConfig.ApiKey, requestCheckout).map(new Function<ResponseBaseEntity<CheckoutResumeEntity>, CheckoutResume>() {
            @Override
            public CheckoutResume apply(ResponseBaseEntity<CheckoutResumeEntity> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")) {
                    throw new Exception(response.internalCode);
                }

                return mapper.map(response.payload);
            }
        });
    }

    @Override
    public Observable<Boolean> updateTransaction(String transactionId, String cardId, String email, String paymentAuthorizationId, String referenceNumber) {

        TransactionResponse body = new TransactionResponse();
        body.transactionId = transactionId;
        body.cardId = cardId;
        body.email = email;
        body.paymentAuthorizationId = paymentAuthorizationId;
        body.referenceNumber = referenceNumber;

        return api.updateTransaction(BuildConfig.ApiKey, body).map(new Function<ResponseBaseEntity<TransactionResponse>, Boolean>() {
            @Override
            public Boolean apply(ResponseBaseEntity<TransactionResponse> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")) {
                    throw new Exception(response.internalCode);
                }

                checkoutPreferences.saveTransactionStatus("2"); //status pagado y avisado a servidor central
                return true;
            }
        });

    }

    @Override
    public Observable<String> getTransactionStatus(String transactionId) {
        return api.getTransactionStatus(BuildConfig.ApiKey, transactionId).map(new Function<ResponseBaseEntity<TransactionResponse>, String>() {
            @Override
            public String apply(ResponseBaseEntity<TransactionResponse> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")) {
                    throw new Exception(response.internalCode);
                }

                return response.payload.transactionStatus;
            }
        });
    }

    private String decryptKey() {
        return decryptUseCase.setData("prueba").decryptData();
    }
}
