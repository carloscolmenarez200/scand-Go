package com.cencosud.scanandgo.checkout.domain.model;

/**
 * Created by joseamaro on 05-06-18.
 */

public class CheckoutResume {

    public String transactionId;
    public String rut;
    public String email;
    public int state;
    public String date;
    public String time;
    public double subTotal;
    public String storeId;
    public int paymentAuthorizationId;
    public Orchestrator orchestrator;
}
