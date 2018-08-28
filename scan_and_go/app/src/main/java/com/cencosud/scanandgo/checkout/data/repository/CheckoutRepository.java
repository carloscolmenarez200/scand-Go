package com.cencosud.scanandgo.checkout.data.repository;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.checkout.domain.model.CheckoutResume;
import com.cencosud.scanandgo.wallet.domain.model.Card;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 05-06-18.
 */

public interface CheckoutRepository {
    Observable<CheckoutResume> getPromotion(String rut, String email, String transactionId, String storeId, double subTotal, List<Product> products, List<Card> cards);
    Observable<Boolean> updateTransaction(String transactionId,String cardId, String email, String paymentAuthorizationId, String referenceNumber);
    Observable<String> getTransactionStatus(String transactionId);

}
