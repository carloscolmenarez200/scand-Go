package com.cencosud.scanandgo.wallet.di.component;

import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.wallet.di.module.PaymentMethodsModule;
import com.cencosud.scanandgo.wallet.di.module.RepositoryModule;
import com.cencosud.scanandgo.wallet.presentation.fragment.PaymentMethodsFragment;
import com.core.di.component.FragmentComponent;
import dagger.Component;

/**
 * Created by carlos on 28-03-18.
 */

@Component(modules = {RepositoryModule.class, PaymentMethodsModule.class, AnalyticModule.class})
public interface PaymentMethodsComponent extends FragmentComponent<PaymentMethodsFragment> {}
