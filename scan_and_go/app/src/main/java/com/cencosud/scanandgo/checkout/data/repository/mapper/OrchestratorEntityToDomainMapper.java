package com.cencosud.scanandgo.checkout.data.repository.mapper;

import com.cencosud.scanandgo.checkout.data.entity.OrchestratorEntity;
import com.cencosud.scanandgo.checkout.domain.model.Orchestrator;
import com.cencosud.scanandgo.wallet.data.repository.mapper.CardToDomainMapper;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by joseamaro on 05-06-18.
 */

public class OrchestratorEntityToDomainMapper extends Mapper<OrchestratorEntity, Orchestrator> {

    private final CardToDomainMapper cardMapper;
    private final DiscountEntityToDomainMapper discountMapper;

    @Inject
    public OrchestratorEntityToDomainMapper(CardToDomainMapper cardMapper, DiscountEntityToDomainMapper discountMapper) {
        this.cardMapper = cardMapper;
        this.discountMapper = discountMapper;
    }


    @Override
    public Orchestrator map(OrchestratorEntity value) {

        Orchestrator orchestrator = new Orchestrator();
        orchestrator.subTotal = value.subTotal;

        if(value.discounts!=null){
            orchestrator.discounts = discountMapper.map(value.discounts);
        }

        return orchestrator;
    }

    @Override
    public OrchestratorEntity reverseMap(Orchestrator value) {

        OrchestratorEntity orchestratorEntity = new OrchestratorEntity();
        orchestratorEntity.subTotal = value.subTotal;

        if(value.discounts!=null){
            orchestratorEntity.discounts = discountMapper.reverseMap(value.discounts);
        }

        return orchestratorEntity;
    }
}
