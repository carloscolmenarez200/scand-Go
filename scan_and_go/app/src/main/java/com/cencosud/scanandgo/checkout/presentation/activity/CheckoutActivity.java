package com.cencosud.scanandgo.checkout.presentation.activity;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.checkout.di.component.DaggerCheckoutActivityComponent;
import com.cencosud.scanandgo.checkout.presentation.fragment.CheckoutFragment;
import com.cencosud.scanandgo.databinding.ActivityCheckoutBinding;
import com.cencosud.scanandgo.databinding.ActivityWalletBinding;
import com.core.presentation.activity.BaseFragmentActivity;
import javax.inject.Inject;

/**
 * Created by carlos on 01-06-18.
 */

public class CheckoutActivity extends BaseFragmentActivity<ActivityCheckoutBinding> {

    @Inject
    CheckoutFragment fragment;

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
        DaggerCheckoutActivityComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_onboarding;
    }
}