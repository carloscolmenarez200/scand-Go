package com.cencosud.scanandgo.terms_and_conditions.domain.usecase;

import com.cencosud.scanandgo.terms_and_conditions.domain.repository.CmsRepository;
import com.core.domain.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 14-06-18.
 */

public class GetContactsUseCase extends UseCase<List<String>> {

    private CmsRepository repository;

    @Inject
    public GetContactsUseCase(CmsRepository repository) {
        this.repository=repository;
    }
    @Override
    protected Observable<List<String>> createObservableUseCase() {
        return repository.getContacts();
    }
}
