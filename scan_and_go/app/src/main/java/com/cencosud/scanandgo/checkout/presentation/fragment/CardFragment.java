package com.cencosud.scanandgo.checkout.presentation.fragment;

import android.os.Bundle;
import android.view.View;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ItemCardBigBinding;
import com.cencosud.scanandgo.databinding.ItemCardCheckoutBinding;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.core.presentation.fragment.BaseFragment;
import com.google.gson.Gson;

/**
 * Created by carlos on 21-05-18.
 */

public class CardFragment extends BaseFragment<ItemCardCheckoutBinding> {

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
        return R.layout.item_card_checkout;
    }

    @Override
    protected void initView() {

        String jsonCard = getArguments().getString("card");
        card = new Gson().fromJson(jsonCard, Card.class);

        binder.imgBackgroundCard.setImageResource(card.getCardBackground());
        binder.tvNumber.setText(card.cardNumber);
        binder.ivType.setImageResource(card.getCardTypeImage());
        binder.tvCardHolder.setText(card.cardHolderName);
        binder.tvExpireDate.setText(card.expirationDate.replace("-", "/"));
        binder.ivDefault.setVisibility(card.defaultCard ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void injectDependencies() {

    }
}
