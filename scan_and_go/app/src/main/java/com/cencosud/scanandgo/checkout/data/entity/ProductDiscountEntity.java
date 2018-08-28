package com.cencosud.scanandgo.checkout.data.entity;

import java.util.List;

/**
 * Created by carlos on 27-06-18.
 */

public class ProductDiscountEntity {

    public String ean;
    public String nativeEan;
    public String name;
    public int quantity;
    public double total;
    public double totalWithDiscount;
    public boolean isPesable;
    public String imageUrl;
    public List<PromotionEntity> promotions;
}
