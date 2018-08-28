package com.cencosud.scanandgo.login_register.di.module;

import com.cencosud.scanandgo.login_register.presentation.fragment.RegisterTokenFragment;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 14-06-18.
 */

@Module
public class RegisterTokenActivityModule {

    @Provides
    RegisterTokenFragment provideFragment(){
        return new RegisterTokenFragment();
    }

    @Provides
    AddCardFragment provideFragmentAddCard(){
        return new AddCardFragment();
    }
}
