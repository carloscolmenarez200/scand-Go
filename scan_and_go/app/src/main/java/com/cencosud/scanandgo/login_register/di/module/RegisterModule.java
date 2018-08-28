package com.cencosud.scanandgo.login_register.di.module;


import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.login_register.domain.usecase.ValidateUserUseCase;
import com.cencosud.scanandgo.login_register.presentation.contract.RegisterContract;
import com.cencosud.scanandgo.terms_and_conditions.presentation.dialog.TermsAndConditionsDialog;
import com.cencosud.scanandgo.login_register.presentation.presenter.RegisterPresenter;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetTermAndConditionsUseCase;

import dagger.Module;
import dagger.Provides;


/**
 * Created by fbarrios80 on 11-04-18.
 */

@Module
public class RegisterModule {

    @Provides
    RegisterContract.Presenter providePresenter(ValidateUserUseCase validateUserUseCase, GetTermAndConditionsUseCase getTermAndConditionsUseCase, Analytic analytic) {
        return new RegisterPresenter(validateUserUseCase, getTermAndConditionsUseCase, analytic);
    }

    @Provides
    TermsAndConditionsDialog provideTermsAndConditionsDialog(){
        return new TermsAndConditionsDialog();
    }
}
