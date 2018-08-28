package com.cencosud.scan_commons.user.domain.usecase;

import android.support.annotation.NonNull;
import com.cencosud.scan_commons.user.data.repository.UserLocalRepository;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.realm.Realm;

/**
 * Created by carlos on 26-03-18.
 */

public class ClearUserUseCase {

    private UserLocalRepository repository;

    @Inject
    public ClearUserUseCase(@NonNull UserLocalRepository repository) {
        this.repository = repository;
    }

    public boolean clearUSer() {
        return repository.clearAll();
    }
}
