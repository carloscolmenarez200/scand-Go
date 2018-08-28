package com.cencosud.scanandgo.nps.di.module;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.nps.data.remote.NpsApi;
import com.cencosud.scanandgo.nps.data.repository.NpsRepositoryImp;
import com.cencosud.scanandgo.nps.domain.repository.NpsRepository;
import com.core.data.remote.ApiServiceFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by joseamaro on 12-06-18.
 */

@Module
public class NpsRepositoryModule {

    @Provides
    NpsRepository provideRepository(NpsApi api) {
        return new NpsRepositoryImp(api);
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides
    NpsApi provideApiServicePromotion() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return ApiServiceFactory.build(okHttpClient, NpsApi.class, BuildConfig.BaseUrlNps);
    }
}
