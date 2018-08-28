package com.cencosud.scanandgo.onboarding.presentation.activity;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityOnboardingBinding;
import com.cencosud.scanandgo.databinding.ActivityWalletBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.onboarding.di.component.DaggerOnBoardingActivityComponent;
import com.cencosud.scanandgo.onboarding.presentation.fragment.OnBoardingFragment;
import com.core.presentation.activity.BaseFragmentActivity;
import javax.inject.Inject;

/**
 * Created by carlos on 28-05-18.
 */

public class OnBoardingActivity extends BaseFragmentActivity<ActivityOnboardingBinding>{

    @Inject OnBoardingFragment fragment;


    @Override
    protected int getFragmentContainerId() {
        return R.id.fragmentContainer;
    }

    @Override
    protected void initView() {
        setFragment(fragment);
    }

    @Override
    protected void injectDependencies() {
        DaggerOnBoardingActivityComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_onboarding;
    }

    @Override
    public void onBackPressed() {
        startActivity(DrawerMenuActivity.class);
        finish();
    }
}
