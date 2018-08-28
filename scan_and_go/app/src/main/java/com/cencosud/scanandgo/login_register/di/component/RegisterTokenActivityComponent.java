package com.cencosud.scanandgo.login_register.di.component;

import com.cencosud.scanandgo.login_register.di.module.RegisterTokenActivityModule;
import com.cencosud.scanandgo.login_register.presentation.activity.RegisterTokenActivity;
import com.core.di.component.ActivityComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by carlos on 14-06-18.
 */

@Singleton
@Component(modules = {RegisterTokenActivityModule.class})
public interface RegisterTokenActivityComponent extends ActivityComponent<RegisterTokenActivity> {
}
