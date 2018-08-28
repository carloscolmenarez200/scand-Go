package com.cencosud.scanandgo.recover_password.presentation.presenter;

import com.cencosud.scanandgo.recover_password.domain.usecase.ChangePasswordUseCase;
import com.cencosud.scanandgo.recover_password.presentation.contract.EnterPasswordContract;
import com.core.domain.usecase.UseCaseObserver;

/**
 * Created by joseamaro on 18-06-18.
 */

public class EnterPasswordPresenter implements EnterPasswordContract.Presenter{

    private final ChangePasswordUseCase changePasswordUseCase;
    private EnterPasswordContract.View view;

    public EnterPasswordPresenter(ChangePasswordUseCase changePasswordUseCase) {
        this.changePasswordUseCase = changePasswordUseCase;
    }

    @Override
    public void initialize(EnterPasswordContract.View view) {
        this.view = view;
    }

    @Override
    public void changePassword(String email, String password, String token) {
        view.showProgress(true);
        try {
            changePasswordUseCase.setData(email, password, token).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    if(value){
                        view.goToLogin();
                        view.showProgress(false);
                    }
                }
                @Override
                public void onError(Throwable e) {
                    view.showMessage("Ha ocurrido un error al recibir el token");
                    view.showProgress(false);
                }
            });
        }catch (Exception e) {
            view.showProgress(false);
            e.printStackTrace();
        }
    }
}
