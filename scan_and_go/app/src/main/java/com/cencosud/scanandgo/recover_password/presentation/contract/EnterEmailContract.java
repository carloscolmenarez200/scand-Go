package com.cencosud.scanandgo.recover_password.presentation.contract;

import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by joseamaro on 18-06-18.
 */

public interface EnterEmailContract  {

    interface View extends IProgressView {
        void next();


    }

    interface Presenter extends BaseViewPresenter<EnterEmailContract.View> {
       void enterEmail(String email);
    }
}
