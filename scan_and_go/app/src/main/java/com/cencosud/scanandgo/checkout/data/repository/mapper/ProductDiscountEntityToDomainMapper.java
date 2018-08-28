package com.cencosud.scanandgo.checkout.data.repository.mapper;

import com.cencosud.scanandgo.checkout.data.entity.ProductDiscountEntity;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.core.data.repository.mapper.Mapper;
import javax.inject.Inject;

/**
 * Created by carlos on 27-06-18.
 */

public class ProductDiscountEntityToDomainMapper extends Mapper<ProductDiscountEntity, ProductDiscount> {

    private final PromotionEntityToDomainMapper promotionMapper;

    @Inject
    public ProductDiscountEntityToDomainMapper(PromotionEntityToDomainMapper promotionMapper) {
        this.promotionMapper = promotionMapper;
    }

    @Override
    public ProductDiscount map(ProductDiscountEntity value) {

        ProductDiscount productDiscount = new ProductDiscount();
        productDiscount.ean = value.ean;
        productDiscount.name = value.name;
        productDiscount.nativeEan = value.nativeEan;
        productDiscount.quantity = value.quantity;
        productDiscount.total = value.total;
        productDiscount.totalWithDiscount = value.totalWithDiscount;
        productDiscount.isPesable = value.isPesable;
        productDiscount.imageUrl = value.imageUrl;

        if (value.promotions != null) {
            productDiscount.promotions = promotionMapper.map(value.promotions);
        }
        return productDiscount;
    }

    @Override
    public ProductDiscountEntity reverseMap(ProductDiscount value) {

        ProductDiscountEntity productDiscountEntity = new ProductDiscountEntity();
        productDiscountEntity.ean = value.ean;
        productDiscountEntity.name = value.name;
        productDiscountEntity.nativeEan = value.nativeEan;
        productDiscountEntity.quantity = value.quantity;
        productDiscountEntity.total = value.total;
        productDiscountEntity.totalWithDiscount = value.totalWithDiscount;
        productDiscountEntity.isPesable = value.isPesable;
        productDiscountEntity.imageUrl = value.imageUrl;

        if (value.promotions != null) {
            productDiscountEntity.promotions = promotionMapper.reverseMap(value.promotions);
        }
        return productDiscountEntity;
    }
}