package com.cencosud.scanandgo.nps.data.remote.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by carlos on 12-06-18.
 */

public class NpsRequest {

    @SerializedName("campaign_code")
    @Expose
    public String campaignCode;

    public List<NpsAnswer> answers;

    public List<NpsTag> tags;
}
