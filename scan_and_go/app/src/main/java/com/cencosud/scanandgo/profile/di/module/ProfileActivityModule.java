package com.cencosud.scanandgo.profile.di.module;

import com.cencosud.scanandgo.profile.presentation.fragment.ProfileFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 18-06-18.
 */

@Module
public class ProfileActivityModule {

    @Provides ProfileFragment provideFragment(){
        return new ProfileFragment();
    }
}
