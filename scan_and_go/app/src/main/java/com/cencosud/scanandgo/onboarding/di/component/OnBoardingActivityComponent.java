package com.cencosud.scanandgo.onboarding.di.component;

import com.cencosud.scanandgo.onboarding.di.module.OnBoardingActivityModule;
import com.cencosud.scanandgo.onboarding.presentation.activity.OnBoardingActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by carlos on 28-05-18.
 */

@Singleton
@Component(modules = {OnBoardingActivityModule.class})
public interface OnBoardingActivityComponent extends ActivityComponent<OnBoardingActivity>{
}
