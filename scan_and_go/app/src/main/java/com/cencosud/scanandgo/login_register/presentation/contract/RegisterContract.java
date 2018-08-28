package com.cencosud.scanandgo.login_register.presentation.contract;

import com.cencosud.scan_commons.user.domain.model.User;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by carlos on 22-03-18.
 */

public interface RegisterContract {

    interface View extends IProgressView {
        void goToRegisterToken(User user);
        void showDialogTermsAndConditions(String terms);
        void dialogTermsAndConditionsDismiss();
    }

    interface Presenter extends BaseViewPresenter<RegisterContract.View> {
        void register(User user);
        void getTermsAndConditions();
    }
}