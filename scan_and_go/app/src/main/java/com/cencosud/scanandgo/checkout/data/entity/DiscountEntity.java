package com.cencosud.scanandgo.checkout.data.entity;

import java.util.List;

/**
 * Created by joseamaro on 05-06-18.
 */

public class DiscountEntity {

    public String cardId;
    public double totalAmount;
    public String bin;
    public String tenderCode;
    public List<ProductDiscountEntity> productsDiscounts;

}
