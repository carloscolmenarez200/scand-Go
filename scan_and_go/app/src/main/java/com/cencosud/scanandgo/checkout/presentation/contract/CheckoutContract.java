package com.cencosud.scanandgo.checkout.presentation.contract;


import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.cybersource.SoapResponse;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by joseamaro on 15-05-18.
 */

public interface CheckoutContract {

    interface View extends IProgressView {
        void displayProducts(List<ProductDiscount> items);
        void setCards(List<Card> value);
        void showEmptyState(boolean show);
        void showTotals(double subTotal, double totalPayment, int totalProducts);
        void showDialogSuccess();
        void showProgressDialog(boolean show, String title);
        void showErrorPromotion();
    }

    interface Presenter extends BaseViewPresenter<View> {
        void refreshCards();
        void updateTenderCode(Card card);
        void setListenerCyberSource(SoapResponse soapResponse);
        void antiFraudCyberSource(Card card);
        void paymentCyberSource(Card card, String referenceNumber);
        void updateTransaction(Card card,String paymentAuthorizationId, String referenceNumber);
        void sendActionError();
    }
}
