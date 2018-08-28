package com.cencosud.scanandgo.recover_password.domain.usecase;

import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.core.domain.usecase.UseCase;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by joseamaro on 18-06-18.
 */

public class ChangePasswordUseCase extends UseCase<Boolean> {

    private final UserRepository repository;
    private String password;
    private String token;
    private String email;

    @Inject
    public ChangePasswordUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public ChangePasswordUseCase setData(String email, String password, String token) {

        this.email = email;
        this.password = password;
        this.token = token;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {

        JsonObject objectEncrypt = new JsonObject();
        objectEncrypt.addProperty("password", password);

        return repository.encrypt(objectEncrypt).flatMap(new Function<String, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> apply(String passwordEncrypt) throws Exception {
                return repository.changePassword(token,email,passwordEncrypt);
            }
        });
    }
}