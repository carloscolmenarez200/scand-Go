package com.cencosud.scanandgo.profile.di.component;

import com.cencosud.scan_commons.user.di.UserModule;
import com.cencosud.scanandgo.profile.di.module.ProfileFragmentModule;
import com.cencosud.scanandgo.profile.presentation.fragment.ProfileFragment;
import com.core.di.component.FragmentComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by carlos on 18-06-18.
 */

@Singleton
@Component(modules = {UserModule.class, ProfileFragmentModule.class})
public interface ProfileFragmentComponent extends FragmentComponent<ProfileFragment> {}
