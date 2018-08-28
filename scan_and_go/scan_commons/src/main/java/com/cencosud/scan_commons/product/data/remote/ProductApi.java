package com.cencosud.scan_commons.product.data.remote;

import com.cencosud.scan_commons.product.data.entity.ProductEntity;
import com.core.data.entity.response.ResponseBaseEntity;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by consultor on 02-05-18.
 */

public interface ProductApi {

    @GET("syg/products/findByEan")
    Observable<ResponseBaseEntity<ProductEntity>> getProduct(@Header("x-api-key") String apiToken, @Query("ean") String ean, @Query("storeId") String storeId);
}
