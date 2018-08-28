package com.cencosud.scanandgo.checkout.domain.usecase;

import com.cencosud.scan_commons.product.data.entity.ProductEntity;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.checkout.data.repository.CheckoutRepository;
import com.cencosud.scanandgo.checkout.domain.model.CheckoutResume;
import com.cencosud.scanandgo.wallet.data.entity.CardEntity;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 05-06-18.
 */

public class GetPromotionUseCase extends UseCase<CheckoutResume> {

    private final CheckoutRepository repository;
    private String rut;
    private String email;
    private String transactionId;
    private String storeId;
    private double subTotal;
    private List<Product> products;
    private List<Card> cards;

    @Inject
    public GetPromotionUseCase(CheckoutRepository repository) {
        this.repository = repository;
    }

    public GetPromotionUseCase setData(String rut, String email, String transactionId, String storeId, double subTotal, List<Product> products,List<Card> cards){
        this.rut = rut;
        this.email = email;
        this.transactionId = transactionId;
        this.storeId = storeId;
        this.subTotal = subTotal;
        this.products = products;
        this.cards = cards;
        return this;
    }

    @Override
    protected Observable<CheckoutResume> createObservableUseCase() {
        return repository.getPromotion(rut, email, transactionId, storeId, subTotal, products, cards);
    }
}

