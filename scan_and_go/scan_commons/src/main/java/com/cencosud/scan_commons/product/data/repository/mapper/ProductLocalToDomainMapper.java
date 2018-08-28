package com.cencosud.scan_commons.product.data.repository.mapper;

import com.cencosud.scan_commons.product.data.local.ProductLocal;
import com.cencosud.scan_commons.product.data.local.StoreLocal;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.product.domain.model.Store;
import com.core.data.repository.mapper.Mapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import io.realm.RealmList;

/**
 * Created by carlos on 04-05-18.
 */

public class ProductLocalToDomainMapper extends Mapper<ProductLocal, Product> {


    private final StoreLocalToDomainMapper storeMapper;

    @Inject
    ProductLocalToDomainMapper(StoreLocalToDomainMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Override
    public Product map(ProductLocal value) {
        Product product = new Product();
        product.description = value.description;
        product.ean = value.ean;
        product.nativeEan = value.nativeEan;
        product.fatSeal = value.fatSeal;
        product.sodiumSeal = value.sodiumSeal;
        product.caloriesSeal = value.caloriesSeal;
        product.sugarSeal = value.sugarSeal;
        product.name = value.name;
        product.fullName = value.fullName;
        product.imageUrl = value.imageUrl;
        product.productQuantity = value.productQuantity;
        product.amount = value.amount;
        product.department = value.department;
        product.id = value.id;
        product.structureCode = value.structureCode;
        product.quantity = value.quantity;
        product.total = value.total;
        product.discount = value.discount;
        product.totalWithDiscount = value.totalWithDiscount;
        product.promotion = value.promotion;
        product.brandId = value.brandId;
        product.brandName = value.brandName;

        if(!value.isPesable){
            product.isPesable = value.nativeEan.substring(0,2).equals("24");
        }else product.isPesable = value.isPesable;


        if(value.stores!=null){

            Map<String,Store> storeMap = new HashMap<>();

            for (StoreLocal i : value.stores)
                storeMap.put(i.storeId,storeMapper.map(i));

            product.stores = storeMap;
        }

        return product;

    }

    @Override
    public ProductLocal reverseMap(Product value) {

        ProductLocal productLocal = new ProductLocal();
        productLocal.description = value.description;
        productLocal.ean = value.ean;
        productLocal.nativeEan = value.nativeEan;
        productLocal.fatSeal = value.fatSeal;
        productLocal.sodiumSeal = value.sodiumSeal;
        productLocal.caloriesSeal = value.caloriesSeal;
        productLocal.sugarSeal = value.sugarSeal;
        productLocal.name = value.name;
        productLocal.fullName = value.fullName;
        productLocal.imageUrl = value.imageUrl;
        productLocal.isPesable = value.isPesable;
        productLocal.productQuantity = value.productQuantity;
        productLocal.amount = value.amount;
        productLocal.department = value.department;
        productLocal.id = value.id;
        productLocal.structureCode = value.structureCode;
        productLocal.quantity = value.quantity;
        productLocal.total = value.total;
        productLocal.discount = value.discount;
        productLocal.totalWithDiscount = value.totalWithDiscount;
        productLocal.promotion = value.promotion;
        productLocal.brandId = value.brandId;
        productLocal.brandName = value.brandName;

        if (value.stores != null){
            Map<String,StoreLocal> storeLocals = storeMapper.reverseMap(value.stores);
            List<StoreLocal> storeLocalList = new ArrayList<>();

            for (Map.Entry<String, StoreLocal> entry : storeLocals.entrySet()) {

                entry.getValue().storeId = entry.getKey();
                storeLocalList.add(entry.getValue());
            }

            productLocal.stores = new RealmList<>(storeLocalList.toArray(new StoreLocal[storeLocals.size()]));
        }

        return productLocal;
    }

}