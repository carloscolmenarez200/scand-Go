package com.cencosud.scanandgo.login_register.presentation.activity;

import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityLoginRegisterBinding;
import com.cencosud.scanandgo.login_register.di.component.DaggerLoginRegisterComponent;
import com.cencosud.scanandgo.login_register.di.module.LoginRegisterModule;
import com.cencosud.scanandgo.login_register.presentation.OnRegisterSuccess;
import com.cencosud.scanandgo.login_register.presentation.adapter.LoginRegisterPagerAdapter;
import com.cencosud.scanandgo.login_register.presentation.widget.ZoomOutPageTransformer;
import com.cencosud.scanandgo.onboarding.presentation.activity.OnBoardingActivity;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;
import com.cencosud.scanandgo.wallet.utils.DoneListener;
import com.core.presentation.activity.BaseActivity;
import com.core.presentation.activity.BaseFragmentActivity;

import javax.inject.Inject;

public class LoginRegisterActivity extends BaseFragmentActivity<ActivityLoginRegisterBinding> implements ViewPager.OnPageChangeListener{

    @Inject
    LoginRegisterPagerAdapter adapter;

    @Override
    protected void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            );
        }

        binder.vpLoginRegister.setPageTransformer(true, new ZoomOutPageTransformer());
        binder.vpLoginRegister.setAdapter(adapter);
        binder.vpLoginRegister.addOnPageChangeListener(this);

        binder.tvLogin.setTextColor(getResources().getColor(R.color.white));
        binder.tvRegister.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tv_login_register_title_unselected));
        binder.tvRegister.setTextColor(getResources().getColor(R.color.white_99_alpha));

        binder.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder.vpLoginRegister != null && binder.vpLoginRegister.getCurrentItem() != 0)
                    binder.vpLoginRegister.setCurrentItem(binder.vpLoginRegister.getCurrentItem() - 1);
            }
        });

        binder.tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder.vpLoginRegister != null
                        && binder.vpLoginRegister.getCurrentItem() != binder.vpLoginRegister
                        .getAdapter().getCount() - 1)
                    binder.vpLoginRegister.setCurrentItem(binder.vpLoginRegister.getCurrentItem() + 1);
            }
        });

    }

    @Override
    protected void injectDependencies() {
        DaggerLoginRegisterComponent.builder().
                loginRegisterModule(new LoginRegisterModule(this))
                .build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_register;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            binder.tvLogin.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tv_login_register_title_selected));
            binder.tvLogin.setTextColor(getResources().getColor(R.color.white));

            binder.tvRegister.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tv_login_register_title_unselected));
            binder.tvRegister.setTextColor(getResources().getColor(R.color.white_99_alpha));
        } else {
            binder.tvRegister.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tv_login_register_title_selected));
            binder.tvRegister.setTextColor(getResources().getColor(R.color.white));

            binder.tvLogin.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.tv_login_register_title_unselected));
            binder.tvLogin.setTextColor(getResources().getColor(R.color.white_99_alpha));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragmentContainer;
    }


}
