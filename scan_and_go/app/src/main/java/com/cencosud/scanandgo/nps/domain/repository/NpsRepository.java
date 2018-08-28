package com.cencosud.scanandgo.nps.domain.repository;

import com.cencosud.scanandgo.nps.data.remote.request.NpsRequest;

import io.reactivex.Observable;

/**
 * Created by carlos on 12-06-18.
 */

public interface NpsRepository {

    Observable<Boolean> sendNps(NpsRequest npsRequest);
}
