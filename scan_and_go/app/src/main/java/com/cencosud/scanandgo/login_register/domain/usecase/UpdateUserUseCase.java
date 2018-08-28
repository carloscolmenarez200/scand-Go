package com.cencosud.scanandgo.login_register.domain.usecase;

import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.cencosud.scan_commons.user.data.repository.mapper.UserEntityToDomainMapper;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 26-03-18.
 */

public class UpdateUserUseCase extends UseCase<Boolean> {
    private final UserRepository repository;
    private UserEntityToDomainMapper mapper;
    private User data;

    @Inject
    public UpdateUserUseCase(UserRepository repository, UserEntityToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UpdateUserUseCase setData(User data) {
        this.data = data;
        return this;
    }

    @Override
    protected Observable<Boolean> createObservableUseCase() {
        return repository.getLastJwtToken().flatMap(new Function<String, Observable<Boolean>>() {
            @Override
            public Observable<Boolean> apply(String token) throws Exception {
                return repository.updateUser(token, mapper.reverseMap(data));
            }
        });
    }
}
