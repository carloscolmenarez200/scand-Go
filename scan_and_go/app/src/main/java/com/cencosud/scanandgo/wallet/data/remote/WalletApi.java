package com.cencosud.scanandgo.wallet.data.remote;

import com.cencosud.scanandgo.wallet.data.entity.CardEntity;
import com.cencosud.scanandgo.wallet.data.remote.response.GetCardsResponse;
import com.core.data.entity.response.ResponseBaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by carlos on 27-03-18.
 */

public interface WalletApi {

    @GET("syg/ewallets/findByEmail")
    Observable<ResponseBaseEntity<GetCardsResponse>> getCards(@Header("x-api-key") String apiToken, @Query("email") String email);

    @HTTP(method = "DELETE", path = "syg/ewallets/remove", hasBody = true)
    Observable<ResponseBaseEntity<String>> removeCard(@Header("x-api-key") String apiToken, @Body GetCardsResponse body);

    @POST("syg/ewallets/defaultCard")
    Observable<ResponseBaseEntity<String>> setDefaultCard(@Header("x-api-key") String apiToken, @Body GetCardsResponse body);

    @GET("syg/ewallets/findDefault")
    Observable<ResponseBaseEntity<CardEntity>> getDefaultCard(@Header("x-api-key") String apiToken, @Query("email") String email);
}
