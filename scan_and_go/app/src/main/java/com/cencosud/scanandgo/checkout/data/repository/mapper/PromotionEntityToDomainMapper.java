package com.cencosud.scanandgo.checkout.data.repository.mapper;

import com.cencosud.scanandgo.checkout.data.entity.PromotionEntity;
import com.cencosud.scanandgo.checkout.domain.model.Promotion;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by carlos on 27-06-18.
 */

public class PromotionEntityToDomainMapper extends Mapper<PromotionEntity, Promotion> {


    @Inject
    public PromotionEntityToDomainMapper() {
    }

    @Override
    public Promotion map(PromotionEntity value) {

        Promotion promotion = new Promotion();
        promotion.discount = value.discount;
        promotion.promotion = value.promotion;
        promotion.promotionId = value.promotionId;

        return promotion;
    }

    @Override
    public PromotionEntity reverseMap(Promotion value) {

        PromotionEntity promotionEntity = new PromotionEntity();
        promotionEntity.discount = value.discount;
        promotionEntity.promotion = value.promotion;
        promotionEntity.promotionId = value.promotionId;

        return promotionEntity;

    }
}