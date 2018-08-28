package com.cencosud.scanandgo.login_register.presentation.presenter;

import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.SetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.login_register.domain.usecase.RegisterUserUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.ValidateWhiteListUseCase;
import com.cencosud.scanandgo.login_register.presentation.contract.RegisterTokenContract;
import com.core.domain.usecase.UseCaseObserver;

/**
 * Created by carlos on 14-06-18.
 */

public class RegisterTokenPresenter implements RegisterTokenContract.Presenter {

    private final SetUserUseCase setUserUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final ValidateWhiteListUseCase whiteListUseCase;
    private final Analytic analytic;

    private RegisterTokenContract.View view;

    public RegisterTokenPresenter(SetUserUseCase setUserUseCase, RegisterUserUseCase registerUserUseCase, ValidateWhiteListUseCase whiteListUseCase, Analytic analytic) {
        this.setUserUseCase = setUserUseCase;
        this.registerUserUseCase = registerUserUseCase;
        this.whiteListUseCase = whiteListUseCase;
        this.analytic = analytic;
    }

    @Override
    public void initialize(RegisterTokenContract.View view) {
        this.view = view;
    }

    @Override
    public void register(final User user) {
        view.showProgress(true);
        try {
            registerUserUseCase.setData(user).execute(new UseCaseObserver<User>() {
                @Override
                public void onNext(final User userValue) {

                    whiteListUseCase.setData(userValue.email).execute(new UseCaseObserver<Boolean>() {
                        @Override
                        public void onNext(Boolean value) {
                            if(value){
                                saveUser(userValue);
                            }else {
                                view.showDialogNotWhiteList();
                                view.showProgress(false);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });

                    analytic.sendUseId(userValue.userProfileId);
                }

                @Override
                public void onError(Throwable e) {
                    if (e.getMessage().equals("500")) {
                        view.showMessage("Ocurrio un error en el registro");
                        analytic.sendErrorView("Error 500", "");
                    }
                    view.showProgress(false);
                }
            });

        } catch (Exception e) {
            view.showProgress(false);
            e.printStackTrace();
        }
    }

    private void saveUser(User user) {

        if (setUserUseCase.setData(user).setUser()) {
            view.registerFinish();
        } else {
            view.showMessage("No se pudo guardar el usuario");
            view.showProgress(false);
        }
    }
}
