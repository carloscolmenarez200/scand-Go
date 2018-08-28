package com.cencosud.scanandgo.terms_and_conditions.presentation.activity;


import android.databinding.DataBindingUtil;
import android.view.View;

import com.cencosud.scan_commons.databinding.ToolbarBinding;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityTermsAndConditionsBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.terms_and_conditions.di.component.DaggerTermsAndConditionsActivityComponent;
import com.cencosud.scanandgo.terms_and_conditions.presentation.contract.TermsAndConditionsContract;
import com.cencosud.scanandgo.terms_and_conditions.presentation.fragment.TermsAndConditionsFragment;
import com.core.presentation.activity.BaseFragmentActivity;

import javax.inject.Inject;

/**
 * Created by consultor on 15-05-18.
 */

public class TermsAndConditionsActivity extends BaseFragmentActivity<ActivityTermsAndConditionsBinding>{

    @Inject
    TermsAndConditionsFragment fragment;

    @Override
    protected void initView() {
        ToolbarBinding toolbarBinding = DataBindingUtil.bind(binder.toolbar.toolbar);

        setFragment(fragment);
        toolbarBinding.tvName.setText(R.string.tv_title_terms_and_conditions);
        toolbarBinding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void injectDependencies() {

        DaggerTermsAndConditionsActivityComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_terms_and_conditions;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fy_container;
    }


}
