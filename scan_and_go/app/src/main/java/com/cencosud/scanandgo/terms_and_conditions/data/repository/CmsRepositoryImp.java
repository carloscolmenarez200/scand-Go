package com.cencosud.scanandgo.terms_and_conditions.data.repository;

import com.cencosud.scanandgo.BuildConfig;
import com.cencosud.scanandgo.terms_and_conditions.data.remote.CmsApi;
import com.cencosud.scanandgo.terms_and_conditions.data.remote.response.CmsResponse;
import com.cencosud.scanandgo.terms_and_conditions.domain.repository.CmsRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 13-06-18.
 */

public class CmsRepositoryImp implements CmsRepository {

    private final CmsApi api;

    public CmsRepositoryImp(CmsApi api) {
        this.api = api;
    }

    @Override
    public Observable<String> getTermsAndConditions() {
        return api.getTermsAndConditions(BuildConfig.ApiKey).map(new Function<List<CmsResponse>, String>() {
            @Override
            public String apply(List<CmsResponse> cmsResponse) throws Exception {

                if(!cmsResponse.isEmpty())
                    return cmsResponse.get(0).terms;

                return null;
            }
        });
    }

    @Override
    public Observable<List<String>> getContacts() {

        return api.getContacts(BuildConfig.ApiKey).map(new Function<List<CmsResponse>, List<String>>() {
            @Override
            public List<String> apply(List<CmsResponse> cmsResponse) throws Exception {

                List<String> contacts = new ArrayList<>();

                if(!cmsResponse.isEmpty())
                   contacts.add(cmsResponse.get(0).mail);

                if(!cmsResponse.isEmpty())
                    contacts.add(cmsResponse.get(0).phoneNumber);

                return contacts;
            }
        });
    }
}
