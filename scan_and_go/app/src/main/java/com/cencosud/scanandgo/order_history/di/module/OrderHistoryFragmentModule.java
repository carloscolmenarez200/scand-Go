package com.cencosud.scanandgo.order_history.di.module;

import com.cencosud.scan_commons.store.domain.usecase.GetStoreJumboLocalUseCase;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.order_history.domain.usecase.GetOrderHistoryUseCase;
import com.cencosud.scanandgo.order_history.presentation.adapter.OrderHistoryAdapter;
import com.cencosud.scanandgo.order_history.presentation.contract.OrderHistoryFragmentContract;
import com.cencosud.scanandgo.order_history.presentation.fragment.OrderDetailsFragment;
import com.cencosud.scanandgo.order_history.presentation.presenter.OrderHistoryPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 12-07-18.
 */

@Module
public class OrderHistoryFragmentModule {

    @Provides
    OrderDetailsFragment provideShoppingHistoryFragment(){
        return new OrderDetailsFragment();
    }

    @Provides
    OrderHistoryAdapter provideAdapter() {
        return new OrderHistoryAdapter();
    }

    @Provides
    OrderHistoryFragmentContract.Presenter providePresenter(GetOrderHistoryUseCase getOrderHistoryUseCase, GetUserUseCase getUserUseCase, GetStoreJumboLocalUseCase getStoreJumboLocalUseCase){
        return new OrderHistoryPresenter(getOrderHistoryUseCase, getUserUseCase, getStoreJumboLocalUseCase);
    }
}
