package com.cencosud.scanandgo.order_history.data.repository;

import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.order_history.data.remote.OrderHistoryApi;
import com.cencosud.scanandgo.order_history.data.remote.response.OrderHistoryResponse;
import com.cencosud.scanandgo.order_history.data.repository.mapper.TransactionEntityToDomainMapper;
import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.core.data.entity.response.ResponseBaseEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by joseamaro on 19-07-18.
 */

public class OrderHistoryRepositoryImp implements OrderHistoryRepository {

    private final OrderHistoryApi api;
    private final TransactionEntityToDomainMapper transactionMapper;

    public OrderHistoryRepositoryImp(OrderHistoryApi api, TransactionEntityToDomainMapper transactionMapper) {
        this.api = api;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Observable<List<Transaction>> getOrders(String email) {

        return api.getOrders(BuildConfig.ApiKey, email).map(new Function<ResponseBaseEntity<OrderHistoryResponse>, List<Transaction>>() {
            @Override
            public List<Transaction> apply(ResponseBaseEntity<OrderHistoryResponse> response) throws Exception {


                if (response.internalCode != null && !response.internalCode.equals("200")) {
                    throw new Exception(response.internalCode);
                }

                return transactionMapper.map(response.payload.transactions);
            }
        });
    }
}
