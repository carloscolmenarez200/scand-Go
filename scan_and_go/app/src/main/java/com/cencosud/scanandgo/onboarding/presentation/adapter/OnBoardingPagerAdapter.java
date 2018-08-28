package com.cencosud.scanandgo.onboarding.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.onboarding.presentation.fragment.ItemOnBoardingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseamaro on 23-04-18.
 */

public class OnBoardingPagerAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 4;
    private List<Integer> images = new ArrayList<>();
    private List<Integer> texts = new ArrayList<>();
    private List<Integer> icons = new ArrayList<>();

    public void init() {
        images.add(R.drawable.onborarding_purple);
        images.add(R.drawable.onborarding_fucsia);
        images.add(R.drawable.onborarding_violet);
        images.add(R.drawable.onborarding_green);

        icons.add(R.drawable.icon_scanner);
        icons.add(0);
        icons.add(R.drawable.icon_bag_recycable);
        icons.add(R.drawable.icon_qr);

        texts.add(R.string.tv_hint_1);
        texts.add(R.string.tv_hint_2);
        texts.add(R.string.tv_hint_3);
        texts.add(R.string.tv_hint_4);
    }

    public OnBoardingPagerAdapter(FragmentManager fm) {
        super(fm);
        init();
    }

    @Override
    public Fragment getItem(int position) {
        return ItemOnBoardingFragment.newInstance(images.get(position), texts.get(position), icons.get(position));
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
