package com.cencosud.scanandgo.onboarding.di.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.onboarding.presentation.adapter.OnBoardingPagerAdapter;
import com.cencosud.scanandgo.onboarding.presentation.contract.OnBoardingContract;
import com.cencosud.scanandgo.onboarding.presentation.presenter.OnBoardingPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 25-04-18.
 */

@Module
public class OnBoardingFragmentModule {

    FragmentActivity fragmentActivity;

    public OnBoardingFragmentModule(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides
    OnBoardingPagerAdapter provideAdapter() {
        return new OnBoardingPagerAdapter(fragmentActivity.getSupportFragmentManager());
    }

    @Provides
    OnBoardingContract.Presenter providePresenter(UserPreferences preferences, GetUserUseCase getUserUseCase, Analytic analytic) {
        return new OnBoardingPresenter(preferences, getUserUseCase, analytic);
    }
}
