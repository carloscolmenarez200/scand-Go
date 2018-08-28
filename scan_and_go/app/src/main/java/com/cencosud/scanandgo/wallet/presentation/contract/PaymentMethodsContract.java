package com.cencosud.scanandgo.wallet.presentation.contract;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by carlos on 28-03-18.
 */

public interface PaymentMethodsContract {
    interface View extends IProgressView {
        void setCards(List<Card> value);
        void openCreateCard();
        void showEmptyState(boolean show);
        void removeCard(Card item);
        boolean hasNoCards();
        void showError();
    }

    interface Presenter extends BaseViewPresenter<View> {
        void refreshCards();
        void deleteCard(Card item);
        void setCardAsDefault(Card item);
    }
}
