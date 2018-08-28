package com.cencosud.scanandgo.checkout.data.remote.request;

import com.cencosud.scan_commons.product.data.entity.ProductEntity;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.cybersource.client.Card;
import com.cencosud.scanandgo.wallet.data.entity.CardEntity;

import java.util.List;

/**
 * Created by joseamaro on 05-06-18.
 */

public class RequestCheckout {

    public String rut;
    public String email;
    public String transactionId;
    public String storeId;
    public double subTotal;
    public List<ProductEntity> products;
    public List<CardEntity> cards;

}
