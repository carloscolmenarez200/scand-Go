package com.cencosud.scanandgo.terms_and_conditions.di.module;

import com.cencosud.scanandgo.terms_and_conditions.presentation.fragment.TermsAndConditionsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 14-06-18.
 */

@Module
public class TermsAndConditionActivityModule {

    @Provides
    TermsAndConditionsFragment provideFragmentTermsConditions(){
        return TermsAndConditionsFragment.newInstance();
    }
}
