package com.cencosud.scanandgo.terms_and_conditions.domain.repository;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by carlos on 13-06-18.
 */

public interface CmsRepository {

    Observable<String> getTermsAndConditions();
    Observable<List<String>> getContacts();
}
