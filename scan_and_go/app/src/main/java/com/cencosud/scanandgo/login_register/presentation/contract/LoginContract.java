package com.cencosud.scanandgo.login_register.presentation.contract;

import com.cencosud.scanandgo.fingerprint.presentation.activity.Fingerprint;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by carlos on 22-03-18.
 */

public interface LoginContract {

    interface View extends IProgressView {
        void goToDashboard();
        void goToOnBoarding();
        void showRutDialog(boolean showRut, boolean showFirstName, boolean showLastName);
        void showDialogNotWhiteList();
        void showDialogTermsAndConditions(String terms);
        void showFingerPrintDialog();
        Fingerprint initFingerprint();
    }

    interface Presenter extends BaseViewPresenter<LoginContract.View> {
        void login(String userName, String password);
        void updateUser(String rut,String firstName, String lastName);
        void analyticRecoverPassword();
        void updateTermsAndConditions();
        void updateFingerprintPreference(String user, String password);
        void updateNotDialogFingerprintPreference(boolean notDialog);
        void saveUser();
        void loginFingerprint();
    }
}
