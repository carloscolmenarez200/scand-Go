package com.cencosud.scanandgo.checkout.data.repository.mapper;

import com.cencosud.scan_commons.product.data.repository.mapper.ProductEntityToDomainMapper;
import com.cencosud.scanandgo.checkout.data.entity.DiscountEntity;
import com.cencosud.scanandgo.checkout.domain.model.Discount;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by joseamaro on 05-06-18.
 */

public class DiscountEntityToDomainMapper extends Mapper<DiscountEntity, Discount> {

    private final ProductDiscountEntityToDomainMapper productDiscountMapper;

    @Inject
    public DiscountEntityToDomainMapper(ProductDiscountEntityToDomainMapper productDiscountMapper) {
        this.productDiscountMapper = productDiscountMapper;
    }

    @Override
    public Discount map(DiscountEntity value) {

        Discount discount = new Discount();
        discount.cardId = value.cardId;
        discount.totalAmount = value.totalAmount;
        discount.bin = value.bin;
        discount.tenderCode = value.tenderCode;

        if(value.productsDiscounts!=null){
            discount.products = productDiscountMapper.map(value.productsDiscounts);
        }
        return discount;
    }

    @Override
    public DiscountEntity reverseMap(Discount value) {

        DiscountEntity discountEntity = new DiscountEntity();
        discountEntity.cardId = value.cardId;
        discountEntity.totalAmount = value.totalAmount;
        discountEntity.bin = value.bin;
        discountEntity.tenderCode = value.tenderCode;

        if(value.products!=null){
            discountEntity.productsDiscounts = productDiscountMapper.reverseMap(value.products);
        }
        return discountEntity;
    }
}
