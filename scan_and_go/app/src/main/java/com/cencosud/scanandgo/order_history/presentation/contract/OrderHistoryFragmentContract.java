package com.cencosud.scanandgo.order_history.presentation.contract;

import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by joseamaro on 12-07-18.
 */

public interface OrderHistoryFragmentContract {

    interface View extends IProgressView {
        void displayOrder(List<Transaction> items);
    }

    interface Presenter extends BaseViewPresenter<View> {

    }
}
