package com.cencosud.scanandgo.store.di.module;

import com.cencosud.scanandgo.store.presentation.fragment.StoreFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 27-06-18.
 */

@Module
public class StoreActivityModule {

    @Provides
    StoreFragment provideFragment(){
        return StoreFragment.newInstance();
    }
}
