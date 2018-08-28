package com.cencosud.scanandgo.store.presentation.activity;


import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityLocalBinding;
import com.core.presentation.activity.BaseFragmentActivity;

public class StoreActivity extends BaseFragmentActivity<ActivityLocalBinding> {


    @Override
    protected void initView() {

    }

    @Override
    protected void injectDependencies() {

        //DaggerLocaleActivityComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_local;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragmentContainer;
    }

}
