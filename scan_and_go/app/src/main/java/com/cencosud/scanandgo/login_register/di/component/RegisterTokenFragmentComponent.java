package com.cencosud.scanandgo.login_register.di.component;

import com.cencosud.scan_commons.user.di.UserModule;
import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.login_register.di.module.RegisterTokenFragmentModule;
import com.cencosud.scanandgo.login_register.presentation.fragment.RegisterTokenFragment;
import com.core.di.component.FragmentComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by carlos on 14-06-18.
 */

@Singleton
@Component(modules = {UserModule.class, RegisterTokenFragmentModule.class, AnalyticModule.class})
public interface RegisterTokenFragmentComponent extends FragmentComponent<RegisterTokenFragment> {
}
