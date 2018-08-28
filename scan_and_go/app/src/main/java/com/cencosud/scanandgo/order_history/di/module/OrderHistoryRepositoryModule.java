package com.cencosud.scanandgo.order_history.di.module;

import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.order_history.data.remote.OrderHistoryApi;
import com.cencosud.scanandgo.order_history.data.repository.OrderHistoryRepository;
import com.cencosud.scanandgo.order_history.data.repository.OrderHistoryRepositoryImp;
import com.cencosud.scanandgo.order_history.data.repository.mapper.TransactionEntityToDomainMapper;
import com.core.data.remote.ApiServiceFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by joseamaro on 20-07-18.
 */

@Module
public class OrderHistoryRepositoryModule {

    @Provides
    OrderHistoryRepository provideRepository(OrderHistoryApi api, TransactionEntityToDomainMapper transactionMapper) {
        return new OrderHistoryRepositoryImp(api, transactionMapper);

    }

    @Provides
    OrderHistoryApi provideApiServiceOrders() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return ApiServiceFactory.build(okHttpClient, OrderHistoryApi.class, BuildConfig.BaseUrl);
    }
}
