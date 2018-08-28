package com.cencosud.scanandgo.menu.presentation;


import com.cencosud.scan_commons.user.domain.model.User;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by carlos on 28-05-18.
 */

public interface DrawerMenuContract {

    interface View extends IProgressView {
        void displayCountCart(int count);
        void goToLogin();
        void displayUser(User user);
        void showContacts(List<String> contacts);
        void showToolTips();

    }

    interface Presenter extends BaseViewPresenter<DrawerMenuContract.View> {
        void getCountCart();
        void logout();
        void toolTips();
        void analyticSedAction(String action, String label);
        void sendActionView();

    }
}
