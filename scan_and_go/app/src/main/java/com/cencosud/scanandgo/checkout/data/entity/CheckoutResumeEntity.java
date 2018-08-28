package com.cencosud.scanandgo.checkout.data.entity;

import com.cencosud.scanandgo.checkout.domain.model.Orchestrator;

/**
 * Created by joseamaro on 05-06-18.
 */

public class CheckoutResumeEntity {

    public String transactionId;
    public String rut;
    public String email;
    public int state;
    public String date;
    public String time;
    public double subTotal;
    public String storeId;
    public int paymentAuthorizationId;
    public OrchestratorEntity orchestrator;
}
