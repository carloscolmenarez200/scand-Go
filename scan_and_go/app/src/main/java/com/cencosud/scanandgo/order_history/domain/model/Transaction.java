package com.cencosud.scanandgo.order_history.domain.model;

import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;

import java.util.Date;
import java.util.List;

/**
 * Created by joseamaro on 19-07-18.
 */

public class Transaction {

    public String cardId;
    public String rut;
    public String storeName;
    public String storeId;
    public String transactionId;
    public String referenceNumber;
    public String paymentAuthorization;
    public Date date;
    public double totalAmount;
    public boolean header;
    public List<ProductDiscount> productDiscounts;

}
