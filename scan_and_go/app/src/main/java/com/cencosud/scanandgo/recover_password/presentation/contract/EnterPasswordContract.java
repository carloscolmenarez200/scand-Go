package com.cencosud.scanandgo.recover_password.presentation.contract;

import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by joseamaro on 18-06-18.
 */

public interface EnterPasswordContract {

    interface View extends IProgressView {

        void goToLogin();
    }

    interface Presenter extends BaseViewPresenter<EnterPasswordContract.View> {

        void changePassword(String email, String password, String token);
    }

}
