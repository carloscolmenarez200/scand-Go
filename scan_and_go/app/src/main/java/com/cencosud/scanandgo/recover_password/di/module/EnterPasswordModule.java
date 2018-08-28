package com.cencosud.scanandgo.recover_password.di.module;

import com.cencosud.scanandgo.recover_password.domain.usecase.ChangePasswordUseCase;
import com.cencosud.scanandgo.recover_password.presentation.contract.EnterPasswordContract;
import com.cencosud.scanandgo.recover_password.presentation.presenter.EnterPasswordPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 18-06-18.
 */
@Module
public class EnterPasswordModule {

    @Provides
    EnterPasswordContract.Presenter providePresenter(ChangePasswordUseCase changePasswordUseCase) {
        return new EnterPasswordPresenter(changePasswordUseCase);
    }
}
