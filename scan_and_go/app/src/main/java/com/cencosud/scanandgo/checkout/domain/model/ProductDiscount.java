package com.cencosud.scanandgo.checkout.domain.model;

import java.util.List;

/**
 * Created by carlos on 27-06-18.
 */

public class ProductDiscount {

    public String ean;
    public String nativeEan;
    public String name;
    public int quantity;
    public double total;
    public double totalWithDiscount;
    public boolean isPesable;
    public String imageUrl;
    public List<Promotion> promotions;
}
