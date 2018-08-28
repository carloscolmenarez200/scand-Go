package com.cencosud.scanandgo.profile.di.module;

import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scan_commons.user.domain.usecase.SetUserUseCase;
import com.cencosud.scanandgo.login_register.domain.usecase.UpdateUserUseCase;
import com.cencosud.scanandgo.profile.presentation.ProfileContract;
import com.cencosud.scanandgo.profile.presentation.presenter.ProfilePresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 18-06-18.
 */

@Module
public class ProfileFragmentModule {

    @Provides
    ProfileContract.Presenter providePresenter(UpdateUserUseCase updateUserUseCase, GetUserUseCase getUserUseCase, SetUserUseCase setUserUseCase){
        return new ProfilePresenter(updateUserUseCase,getUserUseCase,setUserUseCase);
    }
}
