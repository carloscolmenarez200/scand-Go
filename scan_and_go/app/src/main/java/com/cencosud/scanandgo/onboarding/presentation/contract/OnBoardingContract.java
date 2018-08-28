package com.cencosud.scanandgo.onboarding.presentation.contract;

import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by joseamaro on 25-04-18.
 */

public interface OnBoardingContract {

    interface View extends IProgressView {
        void goToDashboard();
    }

    interface Presenter extends BaseViewPresenter<OnBoardingContract.View> {
        void saveOnBoarding();
        void analyticOnBoarding(String label);
    }
}
