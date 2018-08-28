package com.cencosud.scanandgo.order_history.data.remote.response;

import com.cencosud.scanandgo.order_history.data.entity.TransactionEntity;

import java.util.List;

/**
 * Created by joseamaro on 19-07-18.
 */

public class OrderHistoryResponse {

    public String email;
    public List<TransactionEntity> transactions;
}
