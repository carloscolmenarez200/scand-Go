package com.cencosud.scan_commons.product.data.repository.mapper;

import com.cencosud.scan_commons.product.data.entity.ProductEntity;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by carlos on 04-05-18.
 */

public class ProductEntityToDomainMapper extends Mapper<ProductEntity, Product> {

    private final StoreEntityToDomainMapper storeMapper;

    @Inject
    public ProductEntityToDomainMapper(StoreEntityToDomainMapper storeMapper) {
        this.storeMapper = storeMapper;
    }

    @Override
    public Product map(ProductEntity value) {

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

        if(!value.isPesable && value.nativeEan!=null){
            product.isPesable = value.nativeEan.substring(0,2).equals("24");
        }else product.isPesable = value.isPesable;

        product.productQuantity = value.productQuantity;

        if(value.stores!=null){
            product.stores = storeMapper.map(value.stores);
        }

        return product;
    }

    @Override
    public ProductEntity reverseMap(Product value) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.description = value.description;
        productEntity.ean = value.ean;
        productEntity.nativeEan = value.nativeEan;
        productEntity.fatSeal = value.fatSeal;
        productEntity.sodiumSeal = value.sodiumSeal;
        productEntity.caloriesSeal = value.caloriesSeal;
        productEntity.sugarSeal = value.sugarSeal;
        productEntity.name = value.name;
        productEntity.fullName = value.fullName;
        productEntity.imageUrl = value.imageUrl;
        productEntity.isPesable = value.isPesable;
        productEntity.productQuantity = value.productQuantity;
        productEntity.amount = value.amount;
        productEntity.department = value.department;
        productEntity.id = value.id;
        productEntity.structureCode = value.structureCode;
        productEntity.quantity = value.quantity;
        productEntity.total = value.total;
        productEntity.discount = value.discount;
        productEntity.totalWithDiscount = value.totalWithDiscount;
        productEntity.promotion = value.promotion;
        productEntity.brandId = value.brandId;
        productEntity.brandName = value.brandName;

        if(value.stores!=null){
            productEntity.stores = storeMapper.reverseMap(value.stores);
        }

        return productEntity;
    }
}