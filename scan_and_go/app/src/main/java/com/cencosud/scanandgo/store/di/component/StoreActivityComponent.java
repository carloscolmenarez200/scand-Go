package com.cencosud.scanandgo.store.di.component;

import com.cencosud.scanandgo.store.di.module.StoreActivityModule;
import com.cencosud.scanandgo.store.presentation.activity.StoreActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 27-06-18.
 */

@Singleton
@Component(modules = {StoreActivityModule.class})
public interface StoreActivityComponent extends ActivityComponent<StoreActivity> {
}
