package com.cencosud.scanandgo.checkout.di.module;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.product.data.repository.mapper.ProductEntityToDomainMapper;
import com.cencosud.scan_commons.security.Decrypt;
import com.cencosud.scan_commons.security.data.local.SecurityPreferences;
import com.cencosud.scan_commons.security.domain.DecryptUseCase;
import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.data.remote.CheckoutResumeApi;
import com.cencosud.scanandgo.checkout.data.repository.CheckoutRepository;
import com.cencosud.scanandgo.checkout.data.repository.CheckoutRepositoryImp;
import com.cencosud.scanandgo.checkout.data.repository.mapper.ResumeEntityToDomainMapper;
import com.cencosud.scanandgo.wallet.data.repository.mapper.CardToDomainMapper;
import com.core.data.remote.ApiServiceFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by joseamaro on 05-06-18.
 */

@Module
public class CheckoutRepositoryModule {

    @Provides
    CheckoutRepository provideRepository(CheckoutResumeApi api, ResumeEntityToDomainMapper mapper, ProductEntityToDomainMapper productMapper, CardToDomainMapper cardMapper, CheckoutPreferences checkoutPreferences, DecryptUseCase decryptUseCase) {
        return new CheckoutRepositoryImp(api,mapper, productMapper, cardMapper, checkoutPreferences, decryptUseCase);
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides
    CheckoutResumeApi provideApiServicePromotion() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return ApiServiceFactory.build(okHttpClient, CheckoutResumeApi.class, BuildConfig.BaseUrl);
    }
}
