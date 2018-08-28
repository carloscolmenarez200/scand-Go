package com.cencosud.scanandgo.store.presentation.contract;

import android.location.Location;

import com.cencosud.scan_commons.store.domain.model.Store;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;
import java.util.List;

/**
 * Created by joseamaro on 03-07-18.
 */

public interface StoreContract {

    interface View extends IProgressView {
        void displayStores(List<Store> stores);
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getStore(final Location location);
    }
}
