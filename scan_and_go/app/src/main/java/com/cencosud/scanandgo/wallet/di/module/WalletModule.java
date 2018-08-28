package com.cencosud.scanandgo.wallet.di.module;

import com.cencosud.scanandgo.wallet.presentation.fragment.CardsFragment;
import com.cencosud.scanandgo.wallet.presentation.fragment.PaymentMethodsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 28-03-18.
 */

@Module
public class WalletModule {

    @Provides PaymentMethodsFragment provideFragment(){
        return PaymentMethodsFragment.newInstance(false);
    }

    @Provides CardsFragment provideCardsFragment(){
        return CardsFragment.newInstance(false);
    }
}
