package com.cencosud.scanandgo.login_register.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cencosud.scanandgo.login_register.presentation.OnRegisterSuccess;
import com.cencosud.scanandgo.login_register.presentation.fragment.LoginFragment;
import com.cencosud.scanandgo.login_register.presentation.fragment.RegisterFragment;

/**
 * Created by fbarrios80 on 16-04-18.
 */

public class LoginRegisterPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_PAGES = 2;

    public LoginRegisterPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new LoginFragment();
            case 1:
                RegisterFragment registerFragment = new RegisterFragment();
                return registerFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}
