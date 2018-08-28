package com.cencosud.scanandgo.profile.presentation.fragment;

import android.view.View;

import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.checkout.utils.FormatUtils;
import com.cencosud.scanandgo.databinding.FragmentProfileBinding;
import com.cencosud.scanandgo.profile.di.component.DaggerProfileFragmentComponent;
import com.cencosud.scanandgo.profile.presentation.ProfileContract;
import com.core.presentation.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by carlos on 18-06-18.
 */

public class ProfileFragment extends BaseFragment<FragmentProfileBinding> implements ProfileContract.View{


    @Inject
    ProfileContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void initView() {
        getActivity().setTitle("");
        presenter.initialize(this);
        binder.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        binder.scroll.setEnabled(false);
        binder.appBarLayout.setEnabled(false);//cambiar cuando quieras expandir
        /*binder.appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if(state==State.COLLAPSED){
                    binder.ivBack.setImageResource(R.drawable.icon_back_item_car);
                    binder.tvName.setTextColor(Color.BLACK);
                }else {
                    binder.ivBack.setImageResource(R.drawable.ic_back_white);
                    binder.tvName.setTextColor(Color.WHITE);
                }
            }
        });*/
    }

    @Override
    protected void injectDependencies() {
        DaggerProfileFragmentComponent.builder().build().inject(this);
    }

    @Override
    public void showProgress(boolean show) {
        binder.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showProfile(User user) {

        binder.tvRut.setText(FormatUtils.FormatRUT(user.document));
        binder.tvFirstName.setText(user.firstName);
        binder.tvLastName.setText(user.lastName);
        binder.tvMail.setText(user.email);
        binder.tvPhone.setText(user.phoneNumber);

    }



}
