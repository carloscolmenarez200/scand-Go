package com.cencosud.scan_commons.product.data.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by carlos on 04-05-18.
 */

public class ProductEntity {

        public String ean;
        public String nativeEan;
        public String name;
        public String fullName;
        public String description;
        public String imageUrl;
        public int department;
        public String id;
        public String structureCode;
        public double amount;
        public double amountNative;
        public boolean fatSeal;
        public boolean sodiumSeal;
        public boolean sugarSeal;
        public boolean caloriesSeal;
        public boolean isPesable;
        public int productQuantity;
        public double quantity;
        public double total;
        public double discount;
        public double totalWithDiscount;
        public String promotion;
        public String brandId;
        public String brandName;
        public Map<String,StoreEntity> stores;
}
