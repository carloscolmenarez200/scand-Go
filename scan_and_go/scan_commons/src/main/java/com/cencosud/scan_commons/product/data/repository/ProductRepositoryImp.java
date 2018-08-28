package com.cencosud.scan_commons.product.data.repository;

import com.cencosud.scan_commons.BuildConfig;
import com.cencosud.scan_commons.product.ConfigProduct;
import com.cencosud.scan_commons.product.data.entity.ProductEntity;
import com.cencosud.scan_commons.product.data.remote.ProductApi;
import com.cencosud.scan_commons.product.data.repository.mapper.ProductEntityToDomainMapper;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.core.data.entity.response.ResponseBaseEntity;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 04-05-18.
 */

public class ProductRepositoryImp implements ProductRepository{

    private final ProductApi api;
    private final ProductEntityToDomainMapper mapper;

    public ProductRepositoryImp(ProductApi api, ProductEntityToDomainMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    @Override
    public Observable<Product> getProduct(String ean,String storeId) {
        return api.getProduct(BuildConfig.ApiKey,ean,storeId).map(new Function<ResponseBaseEntity<ProductEntity>, Product>() {
            @Override
            public Product apply(ResponseBaseEntity<ProductEntity> response) throws Exception {

                if (response.internalCode != null && !response.internalCode.equals("200")){
                    throw new Exception(response.internalCode);
                }

                return mapper.map(response.payload);
            }
        });
    }
}
