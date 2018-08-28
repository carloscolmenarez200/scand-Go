package com.cencosud.scanandgo.profile.di.component;

import com.cencosud.scanandgo.profile.di.module.ProfileActivityModule;
import com.cencosud.scanandgo.profile.presentation.activity.ProfileActivity;
import com.core.di.component.ActivityComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by carlos on 18-06-18.
 */

@Singleton
@Component(modules = {ProfileActivityModule.class})
public interface ProfileActivityComponent extends ActivityComponent<ProfileActivity>{}
