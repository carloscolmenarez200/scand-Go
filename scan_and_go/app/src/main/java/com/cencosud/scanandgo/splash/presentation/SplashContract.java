package com.cencosud.scanandgo.splash.presentation;

import android.location.Location;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by carlos on 21-03-18.
 */

public interface SplashContract {

    interface View extends IProgressView {
        void goToDashboard();
        void goToOnBoarding();
        void goToLogin();
        void goToQr();
        void goToNps();
        void showDialogError();
        void showRooterDialogError();
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getStores(final Location location);
    }
}
