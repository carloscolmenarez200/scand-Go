package com.cencosud.scan_commons.user.domain.usecase;

import com.cencosud.scan_commons.user.data.repository.UserRepository;
import com.cencosud.scan_commons.user.domain.model.User;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by carlos on 26-03-18.
 */

public class RefreshUserUseCase extends UseCase<Boolean> {
    private final UserRepository repository;
    private SetUserUseCase setUserUseCase;

    @Inject
    public RefreshUserUseCase(UserRepository repository, SetUserUseCase setUserUseCase) {
        this.repository=repository;
        this.setUserUseCase=setUserUseCase;
    }

    @Override protected Observable<Boolean> createObservableUseCase() {
        return repository.getLastJwtToken().flatMap(new Function<String, Observable<Boolean>>() {
            @Override public Observable<Boolean> apply(String token) throws Exception {
                return repository.refreshUser(token).map(new Function<User, Boolean>() {
                    @Override public Boolean apply(User user) throws Exception {
                        return setUserUseCase.setData(user).setUser();
                    }
                });
            }
        });
    }
}