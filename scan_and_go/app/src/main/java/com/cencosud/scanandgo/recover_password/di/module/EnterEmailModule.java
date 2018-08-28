package com.cencosud.scanandgo.recover_password.di.module;

import com.cencosud.scanandgo.recover_password.domain.usecase.GenerateTokenUseCase;
import com.cencosud.scanandgo.recover_password.presentation.contract.EnterEmailContract;
import com.cencosud.scanandgo.recover_password.presentation.fragment.FragmentReestablishPassword;
import com.cencosud.scanandgo.recover_password.presentation.presenter.EnterEmailPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 18-06-18.
 */

@Module
public class EnterEmailModule {

    @Provides
    FragmentReestablishPassword provideFragmentEnter(){
        return new FragmentReestablishPassword();
    }

    @Provides
    EnterEmailContract.Presenter providePresenter(GenerateTokenUseCase generateTokenUseCase) {
        return new EnterEmailPresenter(generateTokenUseCase);
    }
}
