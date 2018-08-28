package com.cencosud.scanandgo.checkout.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.cencosud.scanandgo.checkout.presentation.fragment.CardFragment;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 21-05-18.
 */

public class CardPageAdapter extends FragmentStatePagerAdapter {

    public List<Card> cards;

    public CardPageAdapter(FragmentManager fm) {
        super(fm);
        cards = new ArrayList<>();
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return CardFragment.newInstance(cards.get(position));
    }

    @Override
    public int getCount() {
        return cards.size();
    }

}
