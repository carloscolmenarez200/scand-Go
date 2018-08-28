package com.cencosud.scanandgo.nps.data.remote;

import com.cencosud.scanandgo.nps.data.remote.request.NpsRequest;
import com.cencosud.scanandgo.nps.data.remote.response.ResponseNps;
import com.core.data.entity.response.ResponseBaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by carlos on 12-06-18.
 */

public interface NpsApi {

    @POST("v2/answer")
    Observable<ResponseNps> sendNps(@Header("Authorization") String authorizationToken, @Body NpsRequest body);
}
