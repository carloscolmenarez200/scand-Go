package com.cencosud.scanandgo.qr.presentation;

import android.graphics.Bitmap;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

/**
 * Created by carlos on 05-06-18.
 */

public interface QrContract {

    interface View extends IProgressView {
        void setQrBitmap(Bitmap bitmap);
        void goToNps();
        void showTotals(String totalPayment, int totalProducts);
    }

    interface Presenter extends BaseViewPresenter<QrContract.View> {
        void getTransactionStatus();
        void getTotals();
    }
}
