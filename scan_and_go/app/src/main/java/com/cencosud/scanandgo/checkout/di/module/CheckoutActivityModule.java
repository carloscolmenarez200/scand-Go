package com.cencosud.scanandgo.checkout.di.module;

import com.cencosud.scanandgo.checkout.presentation.fragment.CheckoutFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 01-06-18.
 */

@Module
public class CheckoutActivityModule {

    @Provides
    CheckoutFragment provideFragment(){
        return CheckoutFragment.newInstance();
    }
}
