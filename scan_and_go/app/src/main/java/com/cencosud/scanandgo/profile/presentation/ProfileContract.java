package com.cencosud.scanandgo.profile.presentation;

import com.cencosud.scan_commons.user.domain.model.User;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by carlos on 18-06-18.
 */

public interface ProfileContract {

    interface View extends IProgressView {
        void showProfile(User user);
    }

    interface Presenter extends BaseViewPresenter<ProfileContract.View> {
       void updateUser(String firstName, String lastName, String phoneNumber);
    }
}
