package com.cencosud.scanandgo.login_register.di.component;

import com.cencosud.scanandgo.login_register.di.module.LoginRegisterModule;
import com.cencosud.scanandgo.login_register.presentation.activity.LoginRegisterActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fbarrios80 on 17-04-18.
 */

@Singleton
@Component(modules = {LoginRegisterModule.class})
public interface LoginRegisterComponent extends ActivityComponent<LoginRegisterActivity> {
}
