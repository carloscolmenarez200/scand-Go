package com.cencosud.scanandgo.wallet.presentation.fragment;

import android.os.Bundle;
import android.view.View;

import com.cencosud.scanandgo.R;

import com.cencosud.scanandgo.databinding.ItemPaymentMethodsBinding;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.presentation.fragment.BaseFragment;
import com.google.gson.Gson;

/**
 * Created by carlos on 18-05-18.
 */

public class CardFragment extends BaseFragment<ItemPaymentMethodsBinding> {

    private CardFragment.ActionsListener actionsListener;
    private CardFragment.CardSelectionListener selectionListener;
    private boolean isSelectionMode;
    private Card card;


    public static CardFragment newInstance(Card card) {

        CardFragment f = new CardFragment();
        Bundle b = new Bundle();
        b.putString("card", new Gson().toJson(card));
        f.setArguments(b);
        return f;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_payment_methods;
    }

    @Override
    protected void initView() {

        String jsonCard = getArguments().getString("card");
        card = new Gson().fromJson(jsonCard, Card.class);
        binder.front.tvNumber.setText(card.cardNumber);
        binder.front.ivType.setImageResource(card.getCardTypeImage());
        binder.front.tvCardHolder.setText(card.cardHolderName);
        binder.front.ivDefault.setVisibility(card.defaultCard ? View.VISIBLE : View.GONE);

        if (!isSelectionMode) {

            binder.flipView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binder.flipView.flipTheView();
                }
            });
            /*if(itemPositionCentered!=position && binder.flipView.isBackSide())
                binder.flipView.flipTheView();*/
            binder.back.tvDeleteCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionsListener.onDeleteCardClicked(card);
                }
            });
            binder.back.btnSetAsDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binder.flipView.flipTheView();
                    actionsListener.onSetAsDefaultClicked(card);
                }
            });
        } else {
            binder.flipView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    binder.front.ivDefault.setVisibility(View.VISIBLE);
                    selectionListener.onCardSelected(card);
                }
            });
        }
    }

    @Override
    protected void injectDependencies() {

    }

    public void setSelectionListener(CardFragment.CardSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    public void setActionsListener(CardFragment.ActionsListener actionsListener) {
        this.actionsListener = actionsListener;
    }

    public void setSelectionMode(boolean selectionMode) {
        this.isSelectionMode = selectionMode;
    }


    public interface ActionsListener {

        void onDeleteCardClicked(Card card);

        void onSetAsDefaultClicked(Card card);
    }

    public interface CardSelectionListener {
        void onCardSelected(Card card);
    }

}
