package com.cencosud.scanandgo.login_register.di.component;

import com.cencosud.scan_commons.user.di.UserModule;
import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.login_register.di.module.RegisterModule;
import com.cencosud.scanandgo.login_register.presentation.fragment.RegisterFragment;
import com.cencosud.scanandgo.terms_and_conditions.di.module.CmsRepositoryModule;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fbarrios80 on 11-04-18.
 */

@Singleton
@Component(modules = {UserModule.class, CmsRepositoryModule.class, RegisterModule.class, AnalyticModule.class})
public interface RegisterComponent extends FragmentComponent<RegisterFragment> {
}
