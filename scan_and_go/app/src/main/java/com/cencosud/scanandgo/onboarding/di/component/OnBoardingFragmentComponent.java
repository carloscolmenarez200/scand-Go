package com.cencosud.scanandgo.onboarding.di.component;

import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.onboarding.di.module.OnBoardingFragmentModule;
import com.cencosud.scanandgo.onboarding.presentation.fragment.OnBoardingFragment;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 25-04-18.
 */

@Singleton
@Component(modules = {OnBoardingFragmentModule.class, AnalyticModule.class})
public interface OnBoardingFragmentComponent extends FragmentComponent<OnBoardingFragment> {

}
