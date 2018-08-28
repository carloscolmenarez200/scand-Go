package com.cencosud.scanandgo.login_register.presentation.contract;

import com.cencosud.scan_commons.user.domain.model.User;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by carlos on 14-06-18.
 */

public interface RegisterTokenContract {

    interface View extends IProgressView {
        void registerFinish();
        void showDialogNotWhiteList();
    }

    interface Presenter extends BaseViewPresenter<View> {
        void register(User user);
    }
}
