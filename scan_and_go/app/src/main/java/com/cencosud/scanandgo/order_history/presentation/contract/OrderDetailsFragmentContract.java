package com.cencosud.scanandgo.order_history.presentation.contract;

import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by joseamaro on 12-07-18.
 */

public interface OrderDetailsFragmentContract {

    interface View extends IProgressView {

    }

    interface Presenter extends BaseViewPresenter<OrderDetailsFragmentContract.View> {

    }
}
