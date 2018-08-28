package com.cencosud.scanandgo.terms_and_conditions.data.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by carlos on 13-06-18.
 */

public class CmsResponse {

    public String terms;
    public String mail;
    @SerializedName("phone_number")
    @Expose
    public String phoneNumber;

}
