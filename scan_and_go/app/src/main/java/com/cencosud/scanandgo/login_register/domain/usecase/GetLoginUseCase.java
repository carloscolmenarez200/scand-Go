package com.cencosud.scanandgo.login_register.domain.usecase;

import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.domain.usecase.UseCase;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 26-03-18.
 */

public class GetLoginUseCase extends UseCase<User> {

    private final UserRepository repository;
    private String userName;
    private String password;

    @Inject
    public GetLoginUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public GetLoginUseCase setData(String username, String password) throws Exception {
        this.userName = username;
        this.password = password;
        return this;
    }

    @Override
    protected Observable<User> createObservableUseCase() {

        JsonObject objectEncrypt = new JsonObject();
        objectEncrypt.addProperty("password", password);

        return repository.encrypt(objectEncrypt).flatMap(new Function<String, Observable<User>>() {
            @Override
            public Observable<User> apply(String s) throws Exception {

                JsonObject objectLogin = new JsonObject();
                objectLogin.addProperty("userName", userName);
                objectLogin.addProperty("password", s);

                return repository.getLogin(objectLogin);
            }
        });

    }
}