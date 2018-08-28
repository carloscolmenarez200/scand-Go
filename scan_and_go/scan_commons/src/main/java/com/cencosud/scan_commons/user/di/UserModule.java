package com.cencosud.scan_commons.user.di;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.BuildConfig;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.data.remote.UserApi;
import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.cencosud.scan_commons.user.data.repository.UserRepositoryImp;
import com.cencosud.scan_commons.user.data.repository.mapper.UserEntityToDomainMapper;
import com.core.data.remote.ApiServiceFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by carlos on 26-03-18.
 */

@Module
public class UserModule {

    @Provides
    UserRepository provideRepository(UserApi api, UserPreferences preferences,UserEntityToDomainMapper mapper) {
        return new UserRepositoryImp(api,preferences,mapper);
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides @Singleton
    UserApi provideApiService() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return ApiServiceFactory.build(okHttpClient, UserApi.class, BuildConfig.BaseUrl);
    }
}
