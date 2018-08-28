package com.cencosud.scan_commons.user.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.product.data.local.ProductLocal;
import com.cencosud.scan_commons.user.data.local.UserLocal;
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

public class GetUserUseCase {
    private final UserLocalToDomainMapper mapper;
    private UserLocalRepository repository;

    @Inject
    public GetUserUseCase(@NonNull UserLocalRepository repository,
                          @NonNull UserLocalToDomainMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public User getLoggedUser() {

        UserLocal userLocal = repository.getLoggedUser();
        if (userLocal != null) {
            return mapper.map(userLocal);
        } else return null;

    }
}