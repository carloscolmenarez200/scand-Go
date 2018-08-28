package com.cencosud.scanandgo.terms_and_conditions.di.module;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetTermAndConditionsUseCase;
import com.cencosud.scanandgo.terms_and_conditions.presentation.contract.TermsAndConditionsContract;
import com.cencosud.scanandgo.terms_and_conditions.presentation.presenter.TermsAndConditionsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 14-06-18.
 */

@Module
public class TermsAndConditionsFragmentModule {
    @Provides
    TermsAndConditionsContract.Presenter providePresenter(GetTermAndConditionsUseCase getTermAndConditionsUseCase, GetUserUseCase getUserUseCase, UserPreferences userPreferences, Analytic analytic) {
        return new TermsAndConditionsPresenter(getTermAndConditionsUseCase, getUserUseCase, userPreferences, analytic);
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }
}
