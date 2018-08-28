package com.cencosud.scan_commons.user.data.remote;

import com.cencosud.scan_commons.user.data.entity.UserEntity;
import com.cencosud.scan_commons.user.data.entity.response.ResponseUser;
import com.core.data.entity.response.ResponseBaseEntity;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by carlos on 23-03-18.
 */

public interface UserApi {

    @POST("jumbo/users/login")
    Observable<ResponseBaseEntity<String>> getLogin(@Header("x-api-key") String apiToken, @Body JsonObject jsonObject);

    @POST("jumbo/clients/refresh")
    Observable<ResponseBaseEntity<ResponseUser>> refreshUser(@Header("x-api-key") String apiToken, @Header("tokenjwt") String token);

    @PUT("jumbo/users/update")
    Observable<ResponseBaseEntity<String>> updateUser(@Header("x-api-key") String apiToken, @Header("jwt") String jwt, @Body UserEntity userEntity);

    @POST("jumbo/users/encripter")
    Observable<ResponseBaseEntity<String>> encrypt(@Header("x-api-key") String apiToken, @Body JsonObject jsonObject);

    @POST("jumbo/users/parser")
    Observable<ResponseBaseEntity<UserEntity>> parser(@Header("x-api-key") String apiToken, @Body JsonObject jsonObject);

    @POST("jumbo/users/register")
    Observable<ResponseBaseEntity<String>> register(@Header("x-api-key") String apiToken, @Body UserEntity userEntity);

    @POST("jumbo/users/validateUser")
    Observable<ResponseBaseEntity<String>> validateUser(@Header("x-api-key") String apiToken, @Body UserEntity userEntity);

    @POST("jumbo/users/generate_token")
    Observable<ResponseBaseEntity<String>> generateToken(@Header("x-api-key") String apiToken, @Body JsonObject jsonObject);

    @POST("jumbo/users/new_password")
    Observable<ResponseBaseEntity<String>> changePassword(@Header("x-api-key") String apiToken, @Body JsonObject jsonObject);

    @GET("syg/whitelists/findByEmail")
    Observable<ResponseBaseEntity<Boolean>> whiteLists(@Header("x-api-key") String apiToken, @Query("email") String email);


}
