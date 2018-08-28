package com.cencosud.scan_commons.store.data.remote;

import com.cencosud.scan_commons.store.data.entity.StoreEntity;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by joseamaro on 29-06-18.
 */

public interface StoreApi {

    @GET("syg/cms/locales")
    Observable<List<StoreEntity>> getStore(@Header("x-api-key") String apiToken);
}
