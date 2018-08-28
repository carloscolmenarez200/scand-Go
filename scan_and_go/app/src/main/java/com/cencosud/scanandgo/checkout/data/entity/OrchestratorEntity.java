package com.cencosud.scanandgo.checkout.data.entity;


import com.cencosud.scanandgo.wallet.data.entity.CardEntity;

import java.util.List;

/**
 * Created by joseamaro on 05-06-18.
 */

public class OrchestratorEntity {

    public double subTotal;
    public List<DiscountEntity> discounts;
}
