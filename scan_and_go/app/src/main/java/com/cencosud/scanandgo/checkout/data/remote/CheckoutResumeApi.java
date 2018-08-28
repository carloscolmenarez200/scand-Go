package com.cencosud.scanandgo.checkout.data.remote;

import com.cencosud.scanandgo.checkout.data.entity.CheckoutResumeEntity;
import com.cencosud.scanandgo.checkout.data.remote.request.RequestCheckout;
import com.cencosud.scanandgo.checkout.data.remote.response.TransactionResponse;
import com.core.data.entity.response.ResponseBaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by joseamaro on 05-06-18.
 */

public interface CheckoutResumeApi {

    @POST("syg/salescenter/process")
    Observable<ResponseBaseEntity<CheckoutResumeEntity>> getPromotion(@Header("x-api-key") String apiToken, @Body RequestCheckout body);

    @POST("syg/salescenter/update")
    Observable<ResponseBaseEntity<TransactionResponse>> updateTransaction(@Header("x-api-key") String apiToken, @Body TransactionResponse body);

    @GET("syg/salescenter/getTransactionStatus")
    Observable<ResponseBaseEntity<TransactionResponse>> getTransactionStatus(@Header("x-api-key") String apiToken, @Query("id") String transactionId);
}
