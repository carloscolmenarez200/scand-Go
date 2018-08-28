package com.cencosud.scanandgo.recover_password.domain.usecase;

import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.core.domain.usecase.UseCase;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by joseamaro on 18-06-18.
 */

public class GenerateTokenUseCase extends UseCase<Boolean> {

    private final UserRepository repository;
    private String email;

    @Inject
    public GenerateTokenUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public GenerateTokenUseCase setData(String email) {

        this.email = email;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.generateToken(email);
    }
}
