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
 * Created by carlos on 14-06-18.
 */

public class ValidateUserUseCase extends UseCase<Boolean> {

    private final UserRepository repository;
    private UserEntityToDomainMapper mapper;
    private User data;

    @Inject
    public ValidateUserUseCase(UserEntityToDomainMapper mapper, UserRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public ValidateUserUseCase setData(User data) {
        this.data = data;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {

        JsonObject objectEncrypt = new JsonObject();
        objectEncrypt.addProperty("password", data.password);

        return repository.encrypt(objectEncrypt).flatMap(new Function<String, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> apply(String s) throws Exception {
                data.password = s;
                return repository.validateUser(mapper.reverseMap(data));
            }
        });
    }
}
