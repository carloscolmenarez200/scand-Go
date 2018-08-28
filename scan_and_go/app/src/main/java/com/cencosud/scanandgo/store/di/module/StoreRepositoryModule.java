package com.cencosud.scanandgo.store.di.module;

import com.cencosud.scan_commons.store.data.remote.StoreApi;
import com.cencosud.scan_commons.store.data.repository.StoreRepository;
import com.cencosud.scan_commons.store.data.repository.StoreRepositoryImp;
import com.cencosud.scan_commons.store.data.repository.mapper.StoreEntityToDomainMapper;
import com.cencosud.scanandgo.BuildConfig;
import com.core.data.remote.ApiServiceFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by joseamaro on 03-07-18.
 */

@Module
public class StoreRepositoryModule {

    @Provides
    StoreRepository provideRepository(StoreApi api, StoreEntityToDomainMapper storeMapper) {
        return new StoreRepositoryImp(api, storeMapper);
    }

    @Provides
    StoreApi provideApiServiceStore() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return ApiServiceFactory.build(okHttpClient, StoreApi.class, BuildConfig.BaseUrl);
    }
}
