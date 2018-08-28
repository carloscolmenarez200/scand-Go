package com.cencosud.scanandgo.checkout.di.component;

import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.checkout.di.module.CheckoutFragmentModule;
import com.cencosud.scanandgo.checkout.di.module.CheckoutRepositoryModule;
import com.cencosud.scanandgo.checkout.presentation.fragment.CheckoutFragment;
import com.cencosud.scanandgo.wallet.di.module.RepositoryModule;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 15-05-18.
 */

@Singleton
@Component(modules = {RepositoryModule.class,CheckoutFragmentModule.class, CheckoutRepositoryModule.class, AnalyticModule.class})
public interface CheckoutFragmentComponent extends FragmentComponent<CheckoutFragment> {
}

