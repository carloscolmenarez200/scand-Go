package com.cencosud.scanandgo.login_register.domain.usecase;

import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.cencosud.scan_commons.user.data.repository.mapper.UserEntityToDomainMapper;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.domain.usecase.UseCase;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by fbarrios80 on 16-04-18.
 */

public class RegisterUserUseCase extends UseCase<User> {

    private final UserRepository repository;
    private UserEntityToDomainMapper mapper;
    private User data;

    @Inject
    public RegisterUserUseCase(UserEntityToDomainMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public RegisterUserUseCase setData(User data) {
        this.data = data;
        return this;
    }

    @Override
    protected Observable<User> createObservableUseCase() {
        return repository.register(mapper.reverseMap(data));
    }
}
