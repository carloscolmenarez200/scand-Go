package com.cencosud.scanandgo.terms_and_conditions.domain.usecase;

import com.cencosud.scanandgo.terms_and_conditions.domain.repository.CmsRepository;
import com.cencosud.scanandgo.wallet.domain.repository.CardRepository;
import com.core.domain.usecase.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 14-06-18.
 */

public class GetTermAndConditionsUseCase extends UseCase<String> {

    private CmsRepository repository;

    @Inject
    public GetTermAndConditionsUseCase(CmsRepository repository) {
        this.repository=repository;
    }

    @Override
    protected Observable<String> createObservableUseCase() {
       return repository.getTermsAndConditions();
    }
}
