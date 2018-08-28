package com.cencosud.scanandgo.terms_and_conditions.presentation.presenter;

import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetTermAndConditionsUseCase;
import com.cencosud.scanandgo.terms_and_conditions.presentation.contract.TermsAndConditionsContract;
import com.core.domain.usecase.UseCaseObserver;

/**
 * Created by joseamaro on 14-06-18.
 */

public class TermsAndConditionsPresenter implements TermsAndConditionsContract.Presenter {

    private final GetTermAndConditionsUseCase getTermAndConditionsUseCase;
    private final GetUserUseCase getUserUseCase;
    private final  UserPreferences userPreferences;
    private TermsAndConditionsContract.View view;
    private final Analytic analytic;

    private User user;

    public TermsAndConditionsPresenter(GetTermAndConditionsUseCase getTermAndConditionsUseCase, GetUserUseCase getUserUseCase, UserPreferences userPreferences, Analytic analytic) {
        this.getTermAndConditionsUseCase = getTermAndConditionsUseCase;
        this.getUserUseCase = getUserUseCase;
        this.userPreferences = userPreferences;
        this.analytic = analytic;
    }

    @Override
    public void initialize(TermsAndConditionsContract.View view) {
        this.view = view;
        getTermsAndConditions();
        getUserLocal();
    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            this.user = user;
            if(this.user.termsSyg){
                view.checkTerms();
            }
            analytic.sendPageView("Escaneo de productos", user.userProfileId);
        }
    }

    private void getTermsAndConditions() {

        view.showProgress(true);
        getTermAndConditionsUseCase.execute(new UseCaseObserver<String>() {
            @Override
            public void onNext(String value) {
                view.showTermsAndConditions(value);
                view.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                super.onError(e);
            }
        });
    }

    @Override
    public void checkTerms() {
        userPreferences.saveTerms(true);
    }
}
