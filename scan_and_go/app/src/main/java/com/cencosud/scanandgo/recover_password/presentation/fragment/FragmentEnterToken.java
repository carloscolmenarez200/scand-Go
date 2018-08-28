package com.cencosud.scanandgo.recover_password.presentation.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentEnterTokenBinding;
import com.cencosud.scanandgo.login_register.presentation.activity.LoginRegisterActivity;
import com.cencosud.scanandgo.recover_password.di.component.DaggerEnterPasswordComponent;
import com.cencosud.scanandgo.recover_password.presentation.contract.EnterPasswordContract;
import com.core.presentation.fragment.BaseFragment;

import javax.inject.Inject;


/**
 * Created by joseamaro on 18-06-18.
 */

public class FragmentEnterToken extends BaseFragment<FragmentEnterTokenBinding> implements EnterPasswordContract.View{

    @Inject
    EnterPasswordContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enter_token;
    }

    @Override
    protected void initView() {

        presenter.initialize(this);
        final Bundle bundle = getArguments();
        binder.tvEmail.setText(bundle.getString("email"));
        binder.flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        binder.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.changePassword(binder.tvEmail.getText().toString(), bundle.getString("password"),binder.edtToken.getText().toString());
            }
        });

    }

    @Override
    protected void injectDependencies() {

        DaggerEnterPasswordComponent.builder().build().inject(this);

    }

    @Override
    public void showProgress(boolean show) {
        binder.progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(CONTEXT, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void goToLogin() {
        startActivity(LoginRegisterActivity.class);
        getActivity().finishAffinity();
    }
}
