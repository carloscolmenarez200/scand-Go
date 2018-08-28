package com.cencosud.scanandgo.recover_password.di.module;

import com.cencosud.scanandgo.recover_password.presentation.fragment.FragmentEnterToken;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 18-06-18.
 */
@Module
public class ReestablishPasswordModule {

    @Provides
    FragmentEnterToken provideFragmentEnter(){
        return new FragmentEnterToken();
    }
}
