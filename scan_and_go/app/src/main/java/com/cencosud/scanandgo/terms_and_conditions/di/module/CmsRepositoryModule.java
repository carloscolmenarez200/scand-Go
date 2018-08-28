package com.cencosud.scanandgo.terms_and_conditions.di.module;


import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.terms_and_conditions.data.remote.CmsApi;
import com.cencosud.scanandgo.terms_and_conditions.data.repository.CmsRepositoryImp;
import com.cencosud.scanandgo.terms_and_conditions.domain.repository.CmsRepository;
import com.core.data.remote.ApiServiceFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by joseamaro on 14-06-18.
 */

@Module
public class CmsRepositoryModule {

    @Provides
    CmsRepository provideRepository(CmsApi api) {
        return new CmsRepositoryImp(api);
    }

    @Provides
    CmsApi provideApiServiceCms() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return ApiServiceFactory.build(okHttpClient, CmsApi.class, BuildConfig.BaseUrl);
    }

}
