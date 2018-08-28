package com.cencosud.scanandgo.checkout.data.repository.mapper;

import com.cencosud.scanandgo.checkout.data.entity.CheckoutResumeEntity;
import com.cencosud.scanandgo.checkout.domain.model.CheckoutResume;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by joseamaro on 05-06-18.
 */

public class ResumeEntityToDomainMapper extends Mapper<CheckoutResumeEntity, CheckoutResume> {

    private final OrchestratorEntityToDomainMapper mapperOrchestrator;

    @Inject
    public ResumeEntityToDomainMapper(OrchestratorEntityToDomainMapper mapperOrchestrator) {
        this.mapperOrchestrator = mapperOrchestrator;
    }

    @Override
    public CheckoutResume map(CheckoutResumeEntity value) {

        CheckoutResume checkoutResume = new CheckoutResume();
        checkoutResume.transactionId = value.transactionId;
        checkoutResume.rut = value.rut;
        checkoutResume.email = value.email;
        checkoutResume.state = value.state;
        checkoutResume.date = value.date;
        checkoutResume.time = value.time;
        checkoutResume.subTotal = value.subTotal;
        checkoutResume.storeId = value.storeId;
        checkoutResume.paymentAuthorizationId = value.paymentAuthorizationId;

        if(value.orchestrator!=null){
            checkoutResume.orchestrator = mapperOrchestrator.map(value.orchestrator);
        }
        return checkoutResume;
    }

    @Override
    public CheckoutResumeEntity reverseMap(CheckoutResume value) {

        CheckoutResumeEntity checkoutResumeEntity = new CheckoutResumeEntity();
        checkoutResumeEntity.transactionId = value.transactionId;
        checkoutResumeEntity.rut = value.rut;
        checkoutResumeEntity.email = value.email;
        checkoutResumeEntity.state = value.state;
        checkoutResumeEntity.date = value.date;
        checkoutResumeEntity.time = value.time;
        checkoutResumeEntity.subTotal = value.subTotal;
        checkoutResumeEntity.storeId = value.storeId;
        checkoutResumeEntity.paymentAuthorizationId = value.paymentAuthorizationId;

        if(value.orchestrator!=null){
            checkoutResumeEntity.orchestrator = mapperOrchestrator.reverseMap(value.orchestrator);
        }
        return checkoutResumeEntity;
    }
}
