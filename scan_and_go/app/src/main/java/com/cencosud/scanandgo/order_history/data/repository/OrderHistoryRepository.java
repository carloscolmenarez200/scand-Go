package com.cencosud.scanandgo.order_history.data.repository;

import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 19-07-18.
 */

public interface OrderHistoryRepository {

    Observable<List<Transaction>> getOrders(String email);
}
