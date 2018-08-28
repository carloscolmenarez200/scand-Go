package com.cencosud.scanandgo.nps.data.repository;

import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.nps.data.remote.NpsApi;
import com.cencosud.scanandgo.nps.data.remote.request.NpsAnswer;
import com.cencosud.scanandgo.nps.data.remote.request.NpsRequest;
import com.cencosud.scanandgo.nps.data.remote.response.ResponseNps;
import com.cencosud.scanandgo.nps.domain.repository.NpsRepository;


import java.util.ArrayList;
import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 12-06-18.
 */

public class NpsRepositoryImp implements NpsRepository {

    private final NpsApi api;
    public NpsRepositoryImp(NpsApi api) {
        this.api = api;
    }

    @Override
    public Observable<Boolean> sendNps(NpsRequest npsRequest) {

        return api.sendNps(BuildConfig.ApiKeyNps, npsRequest).map(new Function<ResponseNps, Boolean>() {
            @Override
            public Boolean apply(ResponseNps responseNps) throws Exception {

                if (responseNps.status.inserted == 1) {
                    return true;
                } else
                    return false;
            }

        });
    }
}
