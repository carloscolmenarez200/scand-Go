package com.cencosud.scanandgo.wallet.presentation.contract;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by carlos on 30-05-18.
 */

public interface CardsContract {

    interface View extends IProgressView {
        void setCards(List<Card> value);
        void openCreateCard();
        void showEmptyState(boolean show);
        boolean hasNoCards();
        void showError();
    }

    interface Presenter extends BaseViewPresenter<CardsContract.View> {
        void refreshCards();
        void deleteCard(Card item);
        void setCardAsDefault(Card item);
        void analyticSendAction(String label);
    }
}
