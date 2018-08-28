package com.cencosud.scanandgo.wallet.di.component;

import com.cencosud.scanandgo.wallet.di.module.WalletModule;
import com.cencosud.scanandgo.wallet.presentation.activity.WalletActivity;
import com.core.di.component.ActivityComponent;

import dagger.Component;

/**
 * Created by carlos on 28-03-18.
 */

@Component(modules = WalletModule.class)
public interface WalletComponent extends ActivityComponent<WalletActivity> {}
