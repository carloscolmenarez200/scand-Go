package com.cencosud.scanandgo.order_history.domain.usecase;

import com.cencosud.scanandgo.order_history.data.repository.OrderHistoryRepository;
import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.core.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 19-07-18.
 */

public class GetOrderHistoryUseCase extends UseCase<List<Transaction>> {

    private final OrderHistoryRepository orderHistoryRepository;
    private String email;

    @Inject
    public GetOrderHistoryUseCase(OrderHistoryRepository orderHistoryRepository) {
        this.orderHistoryRepository = orderHistoryRepository;
    }

    public GetOrderHistoryUseCase setData(String email){
        this.email = email;
        return this;
    }

    @Override
    protected Observable<List<Transaction>> createObservableUseCase() {
        return orderHistoryRepository.getOrders(email);
    }
}
