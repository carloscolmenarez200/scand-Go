package com.cencosud.scanandgo.recover_password.presentation.presenter;

import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.recover_password.domain.usecase.GenerateTokenUseCase;
import com.cencosud.scanandgo.recover_password.presentation.contract.EnterEmailContract;
import com.core.domain.usecase.UseCaseObserver;

import javax.inject.Inject;

/**
 * Created by joseamaro on 18-06-18.
 */

public class EnterEmailPresenter implements EnterEmailContract.Presenter{

    private final GenerateTokenUseCase generateTokenUseCase;
    private EnterEmailContract.View view;


    public EnterEmailPresenter(GenerateTokenUseCase generateTokenUseCase) {
        this.generateTokenUseCase = generateTokenUseCase;
    }

    @Override
    public void initialize(EnterEmailContract.View view) {
        this.view = view;
    }

    @Override
    public void enterEmail(String email) {
        view.showProgress(true);
        try {
            generateTokenUseCase.setData(email).execute(new UseCaseObserver<Boolean>() {
                @Override
                public void onNext(Boolean value) {
                    if(value){
                        view.next();
                        view.showProgress(false);
                    }
                }
                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                }
            });
        } catch (Exception e) {
            view.showProgress(false);
            e.printStackTrace();
        }
    }
}
