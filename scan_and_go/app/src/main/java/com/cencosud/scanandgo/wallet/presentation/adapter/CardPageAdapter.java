package com.cencosud.scanandgo.wallet.presentation.adapter;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.presentation.fragment.CardFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 18-05-18.
 */

public class CardPageAdapter extends FragmentStatePagerAdapter implements CardFragment.CardSelectionListener, CardFragment.ActionsListener {

    private List<Card> cards;

    private CardFragment.CardSelectionListener cardSelectionListener;
    private CardFragment.ActionsListener actionsListener;
    private boolean isSelectionMode;

    public CardPageAdapter(FragmentManager fm) {
        super(fm);
        this.cards = new ArrayList<>();
    }

    public void setCards(List<Card> cards){
        this.cards = cards;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {

        Card card = cards.get(position);
        CardFragment fragment = CardFragment.newInstance(card);
        fragment.setActionsListener(this);
        fragment.setSelectionListener(this);
        fragment.setSelectionMode(isSelectionMode);
        return fragment;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public void onDeleteCardClicked(Card card) {
        actionsListener.onDeleteCardClicked(card);
    }

    @Override
    public void onSetAsDefaultClicked(Card card) {
        actionsListener.onSetAsDefaultClicked(card);
    }

    @Override
    public void onCardSelected(Card card) {
        cardSelectionListener.onCardSelected(card);
    }

    public void setSelectionListener(CardFragment.CardSelectionListener cardSelectionListener) {
        this.cardSelectionListener = cardSelectionListener;
    }

    public void setActionsListener(CardFragment.ActionsListener actionsListener) {
        this.actionsListener = actionsListener;
    }

    public void setSelectionMode(boolean selectionMode) {
        isSelectionMode = selectionMode;
    }


}
