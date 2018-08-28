package com.cencosud.scanandgo.profile.presentation.activity;


import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityWalletBinding;
import com.cencosud.scanandgo.profile.di.component.DaggerProfileActivityComponent;
import com.cencosud.scanandgo.profile.presentation.fragment.ProfileFragment;
import com.core.presentation.activity.BaseFragmentActivity;
import javax.inject.Inject;


public class ProfileActivity extends BaseFragmentActivity<ActivityWalletBinding> {


   @Inject
   ProfileFragment fragment;

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
        DaggerProfileActivityComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_profile;
    }

}