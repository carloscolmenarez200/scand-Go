package com.cencosud.scanandgo.login_register.presentation.presenter;

import android.os.Build;

import com.cencosud.scan_commons.security.domain.DecryptUseCase;
import com.cencosud.scan_commons.security.domain.EncryptUseCase;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.SetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.cybersource.decision_manager.domain.usecase.DecisionManagerUseCase;
import com.cencosud.scanandgo.fingerprint.presentation.activity.Fingerprint;
import com.cencosud.scanandgo.login_register.domain.usecase.GetLoginUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.UpdateUserUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.ValidateWhiteListUseCase;
import com.cencosud.scanandgo.login_register.presentation.contract.LoginContract;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetTermAndConditionsUseCase;
import com.core.domain.usecase.UseCaseObserver;

/**
 * Created by carlos on 26-03-18.
 */

public class LoginPresenter implements LoginContract.Presenter {

    private static final String INIT_SESSION_ERROR_MESSAGE = "No se pudo iniciar sesión, por favor verifique que los datos sean los correctos";
    private final GetLoginUseCase loginUseCase;
    private final SetUserUseCase setUserUseCase;
    private final DecisionManagerUseCase decisionManagerUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ValidateWhiteListUseCase whiteListUseCase;
    private final EncryptUseCase encryptUseCase;
    private final DecryptUseCase decryptUseCase;
    private final Analytic analytic;
    private UserPreferences preferences;
    private LoginContract.View view;
    private GetTermAndConditionsUseCase getTermAndConditionsUseCase;

    private User user;
    private Fingerprint fingerprint;

    public LoginPresenter(GetLoginUseCase loginUseCase, SetUserUseCase setUserUseCase, UserPreferences preferences, DecisionManagerUseCase decisionManagerUseCase, UpdateUserUseCase updateUserUseCase, ValidateWhiteListUseCase whiteListUseCase, GetTermAndConditionsUseCase getTermAndConditionsUseCase, Analytic analytic, EncryptUseCase encryptUseCase, DecryptUseCase decryptUseCase) {
        this.loginUseCase = loginUseCase;
        this.setUserUseCase = setUserUseCase;
        this.preferences = preferences;
        this.decisionManagerUseCase = decisionManagerUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.whiteListUseCase = whiteListUseCase;
        this.getTermAndConditionsUseCase = getTermAndConditionsUseCase;
        this.encryptUseCase = encryptUseCase;
        this.decryptUseCase = decryptUseCase;
        this.analytic = analytic;
    }

    @Override
    public void initialize(LoginContract.View view) {
        this.view = view;
        analytic.sendPageView("Login", "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprint = view.initFingerprint();
        }

        initializeFingerPrint();
    }

    @Override
    public void login(final String userName, final String password) {
        analytic.sendAction("ScanAndGo", "Login", "", "Login");
        view.showProgress(true);

        whiteListUseCase.setData(userName).execute(new UseCaseObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {

                if (value) {

                    try {
                        loginUseCase.setData(userName, password).execute(new UseCaseObserver<User>() {
                            @Override
                            public void onNext(User value) {
                                user = value;

                                if (!user.termsSyg) {
                                    getTermsAndConditions();
                                    return;
                                }
                                validateRutDialog();
                            }

                            @Override
                            public void onError(Throwable e) {
                                view.showProgress(false);
                                view.showMessage(INIT_SESSION_ERROR_MESSAGE);
                            }
                        });
                    } catch (Exception e) {
                        view.showProgress(false);
                        view.showMessage(INIT_SESSION_ERROR_MESSAGE);
                        e.printStackTrace();
                    }

                } else {
                    view.showProgress(false);
                    view.showDialogNotWhiteList();
                    //view.showMessage("No autorizado para el uso del aplicativo");
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                view.showMessage("Ha ocurrido un error intente nuevamente");
            }
        });
    }

    private void getTermsAndConditions() {

        view.showProgress(true);
        getTermAndConditionsUseCase.execute(new UseCaseObserver<String>() {
            @Override
            public void onNext(String value) {
                view.showProgress(false);
                view.showDialogTermsAndConditions(value);
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                super.onError(e);
            }
        });
    }

    private void validateRutDialog() {

        boolean showRut = user.document == null || user.document.isEmpty();
        boolean showFirstName = user.firstName == null || user.firstName.isEmpty();
        boolean showLastName = user.lastName == null || user.lastName.isEmpty();
        if (!showRut && !showFirstName && !showLastName) {
            validateFingerPrintDialog();
            //saveUser(user);
        } else {
            view.showRutDialog(showRut, showFirstName, showLastName);
        }
    }


    @Override
    public void updateUser(String rut, String firstName, String lastName) {
        view.showProgress(true);
        if (rut != null && !rut.isEmpty()) {
            user.document = rut;
        }
        if (firstName != null && !firstName.isEmpty()) {
            user.firstName = firstName;
        }
        if (lastName != null && !lastName.isEmpty()) {
            user.lastName = lastName;
        }

        updateUserUseCase.setData(user).execute(new UseCaseObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                if (value) {
                    validateRutDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                view.showMessage("Ocurrio un error actualizando los datos");
            }
        });
    }

    @Override
    public void updateTermsAndConditions() {
        view.showProgress(true);
        user.termsSyg = true;
        updateUserUseCase.setData(user).execute(new UseCaseObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                if (value) {
                    validateRutDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                view.showProgress(false);
                view.showMessage("Ocurrio un error actualizando los datos");
            }
        });
    }

    @Override
    public void updateFingerprintPreference(String user, String password) {
        preferences.saveFingerprint(true);
        encryptUseCase.setData("user", user).encryptData();
        encryptUseCase.setData("password", password).encryptData();
    }

    @Override
    public void updateNotDialogFingerprintPreference(boolean notDialog) {
        preferences.saveNoDialogFingerprint(notDialog);
    }

    @Override
    public void analyticRecoverPassword() {
        analytic.sendAction("ScanAndGo", "Login", "", "Olvidar contraseña");
    }

    @Override
    public void saveUser() {

        if (setUserUseCase.setData(user).setUser()) {
            decisionManagerUseCase.generateIdSession();

            if (preferences.isOnBoarding()) {
                view.goToDashboard();
            } else {
                view.goToOnBoarding();
            }
            analytic.sendUseId(user.userProfileId);
        } else {
            view.showMessage("No se pudo guardar el usuario");
            view.showProgress(false);
        }

    }

    @Override
    public void loginFingerprint() {
        login(decryptUseCase.setData("user").decryptData(), decryptUseCase.setData("password").decryptData());
    }

    private void validateFingerPrintDialog() {

        if (fingerprint == null || !fingerprint.isHardwareDetected()) {
            saveUser();
            return;
        }

        if (!preferences.isNoDialogFingerprint() && !preferences.isFingerprint()) {
            view.showFingerPrintDialog();
        } else saveUser();
    }

    private void initializeFingerPrint() {
        if (preferences.isFingerprint()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                fingerprint.init();
            }
            return;
        }
    }


}