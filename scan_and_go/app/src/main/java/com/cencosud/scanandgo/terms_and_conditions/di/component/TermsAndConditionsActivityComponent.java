package com.cencosud.scanandgo.terms_and_conditions.di.component;

import com.cencosud.scanandgo.terms_and_conditions.di.module.TermsAndConditionActivityModule;
import com.cencosud.scanandgo.terms_and_conditions.presentation.activity.TermsAndConditionsActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 14-06-18.
 */

@Singleton
@Component(modules = {TermsAndConditionActivityModule.class})
public interface TermsAndConditionsActivityComponent extends ActivityComponent<TermsAndConditionsActivity> {
}
