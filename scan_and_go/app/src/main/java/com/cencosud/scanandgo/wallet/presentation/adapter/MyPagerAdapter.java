package com.cencosud.scanandgo.wallet.presentation.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ItemPaymentMethodsBinding;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 30-05-18.
 */

public class MyPagerAdapter extends PagerAdapter {

    List<Card> cards;

    private MyPagerAdapter.ActionsListener actionsListener;
    private MyPagerAdapter.CardSelectionListener selectionListener;
    private boolean isSelectionMode;

    public MyPagerAdapter() {
        this.cards = new ArrayList<>();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void setCards(List<Card> cards){
        this.cards = cards;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        final ItemPaymentMethodsBinding binder = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.item_payment_methods, container, false);
        final Card card = cards.get(position);

        binder.front.imgBackgroundCard.setImageResource(card.getCardBackground());
        binder.back.imgBackgroundCardBack.setImageResource(card.getCardBackgroundBack());
        binder.back.btnSetAsDefault.setBackgroundResource(card.getButtonBackgroundBack());
        binder.back.btnSetAsDefault.setTextColor(card.tenderCode.equals("12")? Color.parseColor("#4a4a4a"):Color.WHITE);
        binder.back.tvDeleteCard.setTextColor(card.tenderCode.equals("12")? Color.parseColor("#4a4a4a"):Color.WHITE);


        binder.front.tvNumber.setText(card.cardNumber);
        binder.front.ivType.setImageResource(card.getCardTypeImage());
        binder.front.tvCardHolder.setText(card.cardHolderName);
        binder.front.ivDefault.setVisibility(card.defaultCard ? View.VISIBLE : View.GONE);
        binder.front.tvExpireDate.setText(card.expirationDate.replace("-", "/"));

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

        container.addView(binder.getRoot());
        return binder.getRoot();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == object);
    }


    public void setSelectionListener(MyPagerAdapter.CardSelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }

    public void setActionsListener(MyPagerAdapter.ActionsListener actionsListener) {
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
