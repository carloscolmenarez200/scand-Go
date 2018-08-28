package com.cencosud.scanandgo.store.di.component;

import com.cencosud.scanandgo.store.di.module.StoreFragmentModule;
import com.cencosud.scanandgo.store.di.module.StoreRepositoryModule;
import com.cencosud.scanandgo.store.presentation.fragment.StoreFragment;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 27-06-18.
 */

@Singleton
@Component(modules = {StoreFragmentModule.class, StoreRepositoryModule.class})
public interface StoreFragmentComponent extends FragmentComponent<StoreFragment> {
}
