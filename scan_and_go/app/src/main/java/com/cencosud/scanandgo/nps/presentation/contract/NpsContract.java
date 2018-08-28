package com.cencosud.scanandgo.nps.presentation.contract;

import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by joseamaro on 12-06-18.
 */

public interface NpsContract {

    interface View extends IProgressView {
        void goToScanner();
    }

    interface Presenter extends BaseViewPresenter<NpsContract.View> {
        void sendNps(double score, String justification,String queue);
        void cleanCache();
    }
}
