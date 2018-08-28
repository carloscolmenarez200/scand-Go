package com.cencosud.scanandgo.login_register.domain.usecase;

import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.core.domain.usecase.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by carlos on 29-06-18.
 */

public class ValidateWhiteListUseCase extends UseCase<Boolean>{

    private final UserRepository repository;
    private String email;

    @Inject
    public ValidateWhiteListUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public ValidateWhiteListUseCase setData(String email){
        this.email = email;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.whiteLists(email);
    }
}
