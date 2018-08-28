package com.cencosud.scanandgo.order_history.data.entity;

import com.cencosud.scanandgo.checkout.data.entity.ProductDiscountEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by joseamaro on 19-07-18.
 */

public class TransactionEntity {

    public String cardId;
    public String rut;
    public String storeId;
    public String transactionId;
    public String referenceNumber;
    public String paymentAuthorization;
    public String date;
    public double totalAmount;
    public List<ProductDiscountEntity> products;

}
