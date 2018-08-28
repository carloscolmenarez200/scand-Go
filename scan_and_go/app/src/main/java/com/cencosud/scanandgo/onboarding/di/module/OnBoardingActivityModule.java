package com.cencosud.scanandgo.onboarding.di.module;

import com.cencosud.scanandgo.onboarding.presentation.fragment.OnBoardingFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 28-05-18.
 */

@Module
public class OnBoardingActivityModule {

    @Provides
    OnBoardingFragment provideFragment(){
        return OnBoardingFragment.newInstance();
    }
}
