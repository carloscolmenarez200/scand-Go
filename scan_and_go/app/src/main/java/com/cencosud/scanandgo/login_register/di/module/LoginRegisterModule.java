package com.cencosud.scanandgo.login_register.di.module;

import android.support.v7.app.AppCompatActivity;

import com.cencosud.scanandgo.login_register.presentation.adapter.LoginRegisterPagerAdapter;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fbarrios80 on 17-04-18.
 */


@Module
public class LoginRegisterModule {

    AppCompatActivity activity;

    public LoginRegisterModule(AppCompatActivity AppCompatActivity){
        this.activity = AppCompatActivity;
    }

    @Provides
    LoginRegisterPagerAdapter provideAdapter() {
        return new LoginRegisterPagerAdapter(activity.getSupportFragmentManager());
    }
}
