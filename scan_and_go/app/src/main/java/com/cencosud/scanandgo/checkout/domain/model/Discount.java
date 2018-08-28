package com.cencosud.scanandgo.checkout.domain.model;

import java.util.List;

/**
 * Created by joseamaro on 05-06-18.
 */

public class Discount {
    public String cardId;
    public double totalAmount;
    public String bin;
    public String tenderCode;
    public List<ProductDiscount> products;
}
