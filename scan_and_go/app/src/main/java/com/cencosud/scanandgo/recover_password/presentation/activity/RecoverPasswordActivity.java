package com.cencosud.scanandgo.recover_password.presentation.activity;


import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityRecoverPasswordBinding;
import com.cencosud.scanandgo.recover_password.di.component.DaggerRecoveryPasswordComponent;
import com.cencosud.scanandgo.recover_password.presentation.fragment.FragmentEnterEmail;
import com.core.presentation.activity.BaseFragmentActivity;

import javax.inject.Inject;

public class RecoverPasswordActivity extends BaseFragmentActivity<ActivityRecoverPasswordBinding> {


    @Inject
    FragmentEnterEmail fragmentEnterEmail;

    @Override
    protected void initView() {
        setFragment(fragmentEnterEmail);
    }

    @Override
    protected void injectDependencies() {

        DaggerRecoveryPasswordComponent.builder().build().inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recover_password;
    }

    @Override
    protected int getFragmentContainerId() {
         return R.id.fragmentContainer;
    }
}
