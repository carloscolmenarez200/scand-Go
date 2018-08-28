package com.cencosud.scanandgo.checkout.di.component;

import com.cencosud.scanandgo.checkout.di.module.CheckoutActivityModule;
import com.cencosud.scanandgo.checkout.presentation.activity.CheckoutActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by carlos on 01-06-18.
 */

@Singleton
@Component(modules = {CheckoutActivityModule.class})
public interface CheckoutActivityComponent extends ActivityComponent<CheckoutActivity>{}
