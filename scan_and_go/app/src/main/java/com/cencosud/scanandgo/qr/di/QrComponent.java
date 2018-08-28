package com.cencosud.scanandgo.qr.di;

import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.checkout.di.module.CheckoutRepositoryModule;
import com.cencosud.scanandgo.qr.presentation.activity.QrActivity;
import com.core.di.component.ActivityComponent;
import dagger.Component;

/**
 * Created by carlos on 05-06-18.
 */

@Component(modules = {QrModule.class, CheckoutRepositoryModule.class, AnalyticModule.class})
public interface QrComponent extends ActivityComponent<QrActivity> {}