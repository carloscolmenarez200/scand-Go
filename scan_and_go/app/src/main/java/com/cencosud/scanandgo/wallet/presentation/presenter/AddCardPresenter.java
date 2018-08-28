package com.cencosud.scanandgo.wallet.presentation.presenter;

import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.wallet.presentation.contract.AddCardContract;

/**
 * Created by carlos on 24-05-18.
 */

public class AddCardPresenter implements AddCardContract.Presenter{

    private final GetUserUseCase getUserUseCase;
    private AddCardContract.View view;

    public AddCardPresenter(GetUserUseCase getUserUseCase){
        this.getUserUseCase = getUserUseCase;
    }

    @Override
    public void initialize(AddCardContract.View view) {
        this.view = view;
        getUserLocal();
    }

    private void getUserLocal() {
        User user = getUserUseCase.getLoggedUser();
        if(user!=null){
            view.displayWebView(user);
        }
    }

}
