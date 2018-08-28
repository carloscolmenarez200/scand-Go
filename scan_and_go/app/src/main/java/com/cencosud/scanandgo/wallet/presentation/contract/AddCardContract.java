package com.cencosud.scanandgo.wallet.presentation.contract;

import com.cencosud.scan_commons.user.domain.model.User;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by carlos on 24-05-18.
 */

public interface AddCardContract {
    interface View extends IProgressView {
        void displayWebView(User user);

    }

    interface Presenter extends BaseViewPresenter<AddCardContract.View> {

    }
}
