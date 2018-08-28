package com.cencosud.scanandgo.wallet.di.module;

import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.wallet.data.remote.WalletApi;
import com.cencosud.scanandgo.wallet.data.repository.WalletRepository;
import com.cencosud.scanandgo.wallet.data.repository.mapper.CardToDomainMapper;
import com.cencosud.scanandgo.wallet.domain.repository.CardRepository;
import com.core.data.remote.ApiServiceFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by carlos on 28-03-18.
 */

@Module
public class RepositoryModule {

    @Provides
    CardRepository providesRepository(WalletApi api, CardToDomainMapper cardMapper){
        return new WalletRepository(api,cardMapper);
    }


    @Provides WalletApi providesApi(){
        return ApiServiceFactory.build(new OkHttpClient(),WalletApi.class, BuildConfig.BaseUrl);
    }
}
