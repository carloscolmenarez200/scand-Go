package com.cencosud.scanandgo.login_register.presentation.presenter;

import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.login_register.domain.usecase.ValidateUserUseCase;
import com.cencosud.scanandgo.login_register.presentation.contract.RegisterContract;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetTermAndConditionsUseCase;
import com.core.domain.usecase.UseCaseObserver;

/**
 * Created by fbarrios80 on 11-04-18.
 */

public class RegisterPresenter implements RegisterContract.Presenter {


    private final ValidateUserUseCase validateUserUseCase;
    private final GetTermAndConditionsUseCase getTermAndConditionsUseCase;
    private final Analytic analytic;
    private RegisterContract.View view;


    public RegisterPresenter(ValidateUserUseCase validateUserUseCase, GetTermAndConditionsUseCase getTermAndConditionsUseCase, Analytic analytic) {
        this.validateUserUseCase = validateUserUseCase;
        this.getTermAndConditionsUseCase = getTermAndConditionsUseCase;
        this.analytic = analytic;
    }

    @Override
    public void initialize(RegisterContract.View view) {
        this.view = view;
        analytic.sendPageView("Registro", "");
    }

    @Override
    public void register(final User user) {
        view.showProgress(true);
        try {
            analytic.sendAction("ScanAndGo", "Register", "", "Registrate");
            validateUserUseCase.setData(user).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    view.showProgress(false);

                    if (value!=null) {
                        view.goToRegisterToken(user);
                    }else{
                        view.dialogTermsAndConditionsDismiss();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (e.getMessage().equals("500")) {
                        view.showMessage("Usuario existente");
                        view.dialogTermsAndConditionsDismiss();
                    }
                    view.showProgress(false);
                }
            });


        } catch (Exception e) {
            view.showProgress(false);
            e.printStackTrace();
        }
    }

    @Override
    public void getTermsAndConditions() {

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

}
