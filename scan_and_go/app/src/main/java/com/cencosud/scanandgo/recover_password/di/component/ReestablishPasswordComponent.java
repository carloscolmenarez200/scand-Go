package com.cencosud.scanandgo.recover_password.di.component;

import com.cencosud.scanandgo.recover_password.di.module.ReestablishPasswordModule;
import com.cencosud.scanandgo.recover_password.presentation.fragment.FragmentReestablishPassword;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 18-06-18.
 */
@Singleton
@Component(modules = {ReestablishPasswordModule.class})
public interface ReestablishPasswordComponent extends FragmentComponent<FragmentReestablishPassword>{
}
