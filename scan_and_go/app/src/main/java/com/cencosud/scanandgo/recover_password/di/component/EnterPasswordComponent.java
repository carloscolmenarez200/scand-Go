package com.cencosud.scanandgo.recover_password.di.component;

import com.cencosud.scan_commons.user.di.UserModule;
import com.cencosud.scanandgo.recover_password.di.module.EnterPasswordModule;
import com.cencosud.scanandgo.recover_password.presentation.fragment.FragmentEnterToken;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 18-06-18.
 */

@Singleton
@Component(modules = {EnterPasswordModule.class, UserModule.class})
public interface EnterPasswordComponent extends FragmentComponent<FragmentEnterToken> {
}
