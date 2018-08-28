package com.cencosud.scanandgo.nps.data.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by carlos on 12-06-18.
 */

public class NpsAnswer {

    public String name;
    public String email;
    public double score;
    public String justification;
    public String phone;
    @SerializedName("create_time")
    @Expose
    public Date createTime;
}
