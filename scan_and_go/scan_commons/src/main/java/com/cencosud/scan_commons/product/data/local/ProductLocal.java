package com.cencosud.scan_commons.product.data.local;

import java.math.BigDecimal;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by carlos on 04-05-18.
 */

public class ProductLocal extends RealmObject {

    @PrimaryKey
    public String nativeEan;
    public String ean;
    public String name;
    public String fullName;
    public String description;
    public String imageUrl;
    public double amount;
    public boolean fatSeal;
    public int department;
    public String id;
    public String structureCode;
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
    public RealmList<StoreLocal> stores;
    public void deleteCascade(){
        stores.deleteAllFromRealm();
        deleteFromRealm();
    }
}
