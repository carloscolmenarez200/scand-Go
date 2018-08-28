package com.cencosud.scanandgo.login_register.presentation.activity;


import android.content.Context;
import android.content.Intent;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityRegisterTokenBinding;
import com.cencosud.scanandgo.login_register.di.component.DaggerRegisterTokenActivityComponent;
import com.cencosud.scanandgo.login_register.presentation.OnRegisterSuccess;
import com.cencosud.scanandgo.login_register.presentation.fragment.RegisterTokenFragment;
import com.cencosud.scanandgo.onboarding.presentation.activity.OnBoardingActivity;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;
import com.cencosud.scanandgo.wallet.utils.DoneListener;
import com.core.presentation.activity.BaseFragmentActivity;

import javax.inject.Inject;

/**
 * Created by carlos on 14-06-18.
 */

public class RegisterTokenActivity extends BaseFragmentActivity<ActivityRegisterTokenBinding> implements OnRegisterSuccess {

    @Inject
    RegisterTokenFragment fragment;

    @Inject
    AddCardFragment addCardFragment;


    @Override
    protected int getFragmentContainerId() {
        return R.id.fragmentContainer;
    }

    @Override
    protected void initView() {
        fragment.setOnRegisterSuccess(this);
        fragment.setArguments(getIntent().getExtras());
        setFragment(fragment);
    }

    @Override
    protected void injectDependencies() {
        DaggerRegisterTokenActivityComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_token;
    }

    public void openCreateCard() {
        addCardFragment.setDoneListener(new DoneListener() {
            @Override
            public void onDone() {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().show();
                }

                Intent intent = new Intent(getBaseContext(), OnBoardingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        setFragment(addCardFragment);

    }

    @Override
    public void onRegisterSuccess() {
        openCreateCard();
    }
}
