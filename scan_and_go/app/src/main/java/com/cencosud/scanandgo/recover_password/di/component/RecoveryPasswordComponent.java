package com.cencosud.scanandgo.recover_password.di.component;

import com.cencosud.scanandgo.recover_password.di.module.RecoveryPasswordModule;
import com.cencosud.scanandgo.recover_password.presentation.activity.RecoverPasswordActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 18-06-18.
 */

@Singleton
@Component(modules = {RecoveryPasswordModule.class})
public interface RecoveryPasswordComponent  extends ActivityComponent<RecoverPasswordActivity>{
}
