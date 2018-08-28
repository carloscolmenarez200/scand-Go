package com.cencosud.scanandgo.recover_password.di.component;

import com.cencosud.scan_commons.user.di.UserModule;
import com.cencosud.scanandgo.recover_password.di.module.EnterEmailModule;
import com.cencosud.scanandgo.recover_password.presentation.fragment.FragmentEnterEmail;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 18-06-18.
 */

@Singleton
@Component(modules = {EnterEmailModule.class, UserModule.class})
public interface EnterEmailComponent extends FragmentComponent<FragmentEnterEmail> {
}
