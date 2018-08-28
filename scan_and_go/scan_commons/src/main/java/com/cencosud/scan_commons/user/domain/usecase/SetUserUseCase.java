package com.cencosud.scan_commons.user.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.user.data.repository.UserLocalRepository;
import com.cencosud.scan_commons.user.data.repository.mapper.UserLocalToDomainMapper;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 26-03-18.
 */

public class SetUserUseCase  {

    private final UserLocalToDomainMapper mapper;
    private UserLocalRepository repository;
    private User data;

    @Inject
    public SetUserUseCase(@NonNull UserLocalRepository repository,
                          @NonNull UserLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SetUserUseCase setData(User data) {
        this.data = data;
        return this;
    }

    public boolean setUser() {
        repository.clearAll();
        return  repository.addOrUpdateUser(mapper.reverseMap(data));
    }
}