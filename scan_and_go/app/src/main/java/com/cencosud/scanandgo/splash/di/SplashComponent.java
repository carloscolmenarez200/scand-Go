package com.cencosud.scanandgo.splash.di;


import com.cencosud.scanandgo.checkout.di.module.CheckoutRepositoryModule;
import com.cencosud.scanandgo.splash.presentation.activity.SplashActivity;
import com.cencosud.scanandgo.store.di.module.StoreRepositoryModule;
import com.core.di.component.ActivityComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by carlos on 26-03-18.
 */

@Singleton
@Component(modules = {SplashModule.class, CheckoutRepositoryModule.class, StoreRepositoryModule.class })
public interface SplashComponent extends ActivityComponent<SplashActivity> {}
