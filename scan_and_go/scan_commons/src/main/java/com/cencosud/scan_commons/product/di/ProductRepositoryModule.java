package com.cencosud.scan_commons.product.di;

import com.cencosud.scan_commons.BuildConfig;
import com.cencosud.scan_commons.product.ConfigProduct;
import com.cencosud.scan_commons.product.data.remote.ProductApi;
import com.cencosud.scan_commons.product.data.repository.ProductRepository;
import com.cencosud.scan_commons.product.data.repository.ProductRepositoryImp;
import com.cencosud.scan_commons.product.data.repository.mapper.ProductEntityToDomainMapper;
import com.core.data.remote.ApiServiceFactory;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by consultor on 02-05-18.
 */

@Module
public class ProductRepositoryModule {

    @Provides
    ProductRepository provideRepository(ProductApi api, ProductEntityToDomainMapper mapper) {
        return new ProductRepositoryImp(api,mapper);
    }

    @Provides
    ProductApi provideApiServiceProduct() {
        OkHttpClient okHttpClient = new OkHttpClient();
        return ApiServiceFactory.build(okHttpClient, ProductApi.class, BuildConfig.BaseUrl);
    }
}
