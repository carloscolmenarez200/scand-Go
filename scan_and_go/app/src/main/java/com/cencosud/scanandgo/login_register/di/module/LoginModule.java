package com.cencosud.scanandgo.login_register.di.module;

import com.cencosud.scan_commons.security.domain.DecryptUseCase;
import com.cencosud.scan_commons.security.domain.EncryptUseCase;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.cybersource.decision_manager.domain.usecase.DecisionManagerUseCase;
import com.cencosud.scanandgo.fingerprint.presentation.dialog.AccessTouchDialog;
import com.cencosud.scanandgo.fingerprint.presentation.dialog.SuccessFingerprintDialog;
import com.cencosud.scanandgo.login_register.domain.usecase.GetLoginUseCase;
import com.cencosud.scan_commons.user.domain.usecase.SetUserUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.UpdateUserUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.ValidateWhiteListUseCase;
import com.cencosud.scanandgo.login_register.presentation.contract.LoginContract;
import com.cencosud.scanandgo.login_register.presentation.dialog.RutDialog;
import com.cencosud.scanandgo.login_register.presentation.presenter.LoginPresenter;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetTermAndConditionsUseCase;
import com.cencosud.scanandgo.terms_and_conditions.presentation.dialog.TermsAndConditionsDialog;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 26-03-18.
 */

@Module
public class LoginModule {

    @Provides
    LoginContract.Presenter providePresenter(GetLoginUseCase loginUseCase, SetUserUseCase setUserUseCase,
                                             UserPreferences preferences, DecisionManagerUseCase decisionManagerUseCase, UpdateUserUseCase updateUserUseCase, ValidateWhiteListUseCase whiteListUseCase, GetTermAndConditionsUseCase getTermAndConditionsUseCase, Analytic analytic, EncryptUseCase encryptUseCase, DecryptUseCase decryptUseCase) {
        return new LoginPresenter(loginUseCase ,setUserUseCase, preferences, decisionManagerUseCase, updateUserUseCase,whiteListUseCase,getTermAndConditionsUseCase,analytic, encryptUseCase, decryptUseCase);
    }

    @Provides
    RutDialog provideDialogRut() {
        return new RutDialog();
    }

    @Provides
    AccessTouchDialog provideDialogTouch() {
        return new AccessTouchDialog();
    }

    @Provides
    SuccessFingerprintDialog provideDialogSuccessFingerprint() {
        return new SuccessFingerprintDialog();
    }

    @Provides
    TermsAndConditionsDialog provideTermsAndConditionsDialog(){
        return new TermsAndConditionsDialog();
    }
}
