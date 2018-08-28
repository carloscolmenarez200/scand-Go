package com.cencosud.scanandgo.onboarding.presentation.presenter;

import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.onboarding.presentation.contract.OnBoardingContract;

import javax.inject.Inject;

/**
 * Created by joseamaro on 25-04-18.
 */

public class OnBoardingPresenter implements OnBoardingContract.Presenter {

    private OnBoardingContract.View view;
    private UserPreferences preferences;
    private final GetUserUseCase getUserUseCase;
    private final Analytic analytic;

    private User user;

    @Inject
    public OnBoardingPresenter(UserPreferences preferences, GetUserUseCase getUserUseCase, Analytic analytic) {
        this.preferences = preferences;
        this.getUserUseCase = getUserUseCase;
        this.analytic = analytic;
    }

    @Override
    public void initialize(OnBoardingContract.View view) {
        this.view = view;
        getUserLocal();
    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            this.user = user;
            analytic.sendPageView("Escaneo", user.userProfileId);
        }
    }
    @Override
    public void saveOnBoarding() {
        preferences.saveOnBoarding(true);
        view.goToDashboard();
    }

    @Override
    public void analyticOnBoarding(String label) {
        analytic.sendAction("Scan And Go", "Abandono Onboarding", user.userProfileId, label);
    }
}
