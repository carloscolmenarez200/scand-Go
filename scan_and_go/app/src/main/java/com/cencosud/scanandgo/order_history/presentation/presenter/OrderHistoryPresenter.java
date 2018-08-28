package com.cencosud.scanandgo.order_history.presentation.presenter;

import com.cencosud.scan_commons.store.domain.model.Store;
import com.cencosud.scan_commons.store.domain.usecase.GetStoreJumboLocalUseCase;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.cencosud.scanandgo.order_history.domain.usecase.GetOrderHistoryUseCase;
import com.cencosud.scanandgo.order_history.presentation.contract.OrderHistoryFragmentContract;
import com.core.domain.usecase.UseCaseObserver;

import java.util.List;

/**
 * Created by joseamaro on 19-07-18.
 */

public class OrderHistoryPresenter implements OrderHistoryFragmentContract.Presenter {

    private final GetOrderHistoryUseCase getOrderHistoryUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetStoreJumboLocalUseCase getStoreJumboLocalUseCase;

    private OrderHistoryFragmentContract.View view;
    private User user;

    public OrderHistoryPresenter(GetOrderHistoryUseCase getOrderHistoryUseCase, GetUserUseCase getUserUseCase, GetStoreJumboLocalUseCase getStoreJumboLocalUseCase) {
        this.getOrderHistoryUseCase = getOrderHistoryUseCase;
        this.getUserUseCase = getUserUseCase;
        this.getStoreJumboLocalUseCase = getStoreJumboLocalUseCase;
    }

    @Override
    public void initialize(OrderHistoryFragmentContract.View view) {
        this.view = view;
        getUserLocal();
        getOrderHistory();
    }

    private void getUserLocal() {
        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            this.user = user;
        }
    }

    private void getOrderHistory() {
        view.showProgress(true);
        getOrderHistoryUseCase.setData(user.email).execute(new UseCaseObserver<List<Transaction>>() {
            @Override
            public void onNext(List<Transaction> value) {
                setNameLocalStore(value);
                view.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                view.showProgress(false);
            }

        });
    }

    private void setNameLocalStore(List<Transaction> value){

        for(Transaction transaction: value){
            Store store = getStoreJumboLocalUseCase.setData(transaction.storeId).getStore();
            transaction.storeName = store.name;
        }
        view.displayOrder(value);
    }



}
