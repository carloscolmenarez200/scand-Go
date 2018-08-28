package com.cencosud.scanandgo.terms_and_conditions.di.component;

import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.terms_and_conditions.di.module.CmsRepositoryModule;
import com.cencosud.scanandgo.terms_and_conditions.di.module.TermsAndConditionsFragmentModule;
import com.cencosud.scanandgo.terms_and_conditions.presentation.fragment.TermsAndConditionsFragment;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 14-06-18.
 */

@Singleton
@Component(modules = {TermsAndConditionsFragmentModule.class, CmsRepositoryModule.class, AnalyticModule.class})
public interface TermsAndConditionsFragmentComponent extends FragmentComponent<TermsAndConditionsFragment>{
}
