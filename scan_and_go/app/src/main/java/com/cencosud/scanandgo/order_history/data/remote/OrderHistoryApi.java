package com.cencosud.scanandgo.order_history.data.remote;

import com.cencosud.scanandgo.order_history.data.remote.response.OrderHistoryResponse;
import com.core.data.entity.response.ResponseBaseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by joseamaro on 19-07-18.
 */

public interface OrderHistoryApi {

    @GET("syg/orders/findByEmail")
    Observable<ResponseBaseEntity<OrderHistoryResponse>> getOrders(@Header("x-api-key") String apiToken, @Query("email") String email);
}
