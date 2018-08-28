package com.cencosud.scanandgo.terms_and_conditions.data.remote;

import com.cencosud.scanandgo.terms_and_conditions.data.remote.response.CmsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by carlos on 13-06-18.
 */

public interface CmsApi {

    @GET("syg/cms/contactos")
    Observable<List<CmsResponse>> getContacts(@Header("x-api-key") String apiToken);

    @GET("syg/cms/terminosycondiciones")
    Observable<List<CmsResponse>> getTermsAndConditions(@Header("x-api-key") String apiToken);



}
