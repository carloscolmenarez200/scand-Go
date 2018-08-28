package com.cencosud.scanandgo.login_register.di.module;

import com.cencosud.scan_commons.user.domain.usecase.SetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.login_register.domain.usecase.RegisterUserUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.ValidateWhiteListUseCase;
import com.cencosud.scanandgo.login_register.presentation.contract.RegisterTokenContract;
import com.cencosud.scanandgo.login_register.presentation.presenter.RegisterTokenPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 14-06-18.
 */

@Module
public class RegisterTokenFragmentModule {

    @Provides
    RegisterTokenContract.Presenter providePresenter(SetUserUseCase setUserUseCase, RegisterUserUseCase registerUserUseCase, ValidateWhiteListUseCase whiteListUseCase, Analytic analytic) {
        return new RegisterTokenPresenter(setUserUseCase, registerUserUseCase, whiteListUseCase, analytic);
    }

}
