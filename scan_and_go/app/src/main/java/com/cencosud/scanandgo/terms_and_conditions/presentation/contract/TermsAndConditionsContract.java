package com.cencosud.scanandgo.terms_and_conditions.presentation.contract;

import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by joseamaro on 14-06-18.
 */

public interface TermsAndConditionsContract {

    interface View extends IProgressView {

        void showTermsAndConditions(String terms);
        void checkTerms();

    }

    interface Presenter extends BaseViewPresenter<TermsAndConditionsContract.View> {
        void checkTerms();
    }
}
