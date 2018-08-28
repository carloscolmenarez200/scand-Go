package com.cencosud.scanandgo.onboarding.presentation.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.databinding.FragmentOnboardingBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.onboarding.di.component.DaggerOnBoardingFragmentComponent;
import com.cencosud.scanandgo.onboarding.di.module.OnBoardingFragmentModule;
import com.cencosud.scanandgo.onboarding.presentation.adapter.OnBoardingPagerAdapter;
import com.cencosud.scanandgo.onboarding.presentation.contract.OnBoardingContract;
import com.cencosud.scanandgo.onboarding.presentation.widget.ZoomOutPageTransformer;
import com.core.presentation.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by joseamaro on 23-04-18.
 */

public class OnBoardingFragment extends BaseFragment<FragmentOnboardingBinding> implements ViewPager.OnPageChangeListener, OnBoardingContract.View {

    @Inject
    OnBoardingPagerAdapter adapter;

    @Inject
    OnBoardingContract.Presenter presenter;

    @Override
    protected void initView() {
        presenter.initialize(this);
        binder.pager.setPageTransformer(true, new ZoomOutPageTransformer());
        binder.pager.setAdapter(adapter);
        binder.pager.addOnPageChangeListener(this);
        binder.tabDots.setupWithViewPager(binder.pager, true);
        binder.tvTextSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveOnBoarding();
                presenter.analyticOnBoarding("Escaneo");
            }
        });
    }

    @Override
    protected void injectDependencies() {
        DaggerOnBoardingFragmentComponent.builder().onBoardingFragmentModule(new OnBoardingFragmentModule(getActivity())).build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_onboarding;
    }

   /* @Override
    public void onBackPressed() {
        if (binder.pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            binder.pager.setCurrentItem(binder.pager.getCurrentItem() - 1);
        }
    }*/

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 3) {
            binder.tvTextSkip.setText(getResources().getString(R.string.i_get_it));
            binder.tvTextSkip.setTextColor(getResources().getColor(R.color.orange_pressed));
        } else {
            binder.tvTextSkip.setText(getResources().getString(R.string.skip));
            binder.tvTextSkip.setTextColor(getResources().getColor(R.color.colorDivider));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToDashboard() {
        startActivity(DrawerMenuActivity.class);
        finishActivity();
    }

    public static OnBoardingFragment newInstance() {
        OnBoardingFragment instance = new OnBoardingFragment();
        return instance;
    }
}
