package com.cencosud.scanandgo.login_register.di.component;

import com.cencosud.scan_commons.user.di.UserModule;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.login_register.di.module.LoginModule;
import com.cencosud.scanandgo.login_register.presentation.activity.LoginRegisterActivity;
import com.cencosud.scanandgo.login_register.presentation.fragment.LoginFragment;
import com.cencosud.scanandgo.terms_and_conditions.di.module.CmsRepositoryModule;
import com.core.di.component.ActivityComponent;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by carlos on 26-03-18.
 */

@Singleton
@Component(modules = {UserModule.class, CmsRepositoryModule.class, LoginModule.class, AnalyticModule.class})
public interface LoginComponent extends FragmentComponent<LoginFragment> {
}
