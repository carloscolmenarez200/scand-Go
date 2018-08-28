package com.cencosud.scanandgo.recover_password.presentation.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cencosud.scan_commons.utils.Validator;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentEnterEmailBinding;
import com.cencosud.scanandgo.recover_password.di.component.DaggerEnterEmailComponent;
import com.cencosud.scanandgo.recover_password.presentation.contract.EnterEmailContract;
import com.core.presentation.fragment.BaseStackFragment;

import javax.inject.Inject;

/**
 * Created by joseamaro on 18-06-18.
 */

public class FragmentEnterEmail extends BaseStackFragment<FragmentEnterEmailBinding> implements EnterEmailContract.View {


    @Inject
    FragmentReestablishPassword fragmentReestablishPassword;

    @Inject
    EnterEmailContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_enter_email;
    }

    @Override
    protected void initView() {

        presenter.initialize(this);
        binder.flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity();
            }
        });
        binder.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyField()) {
                    if (!Validator.validateEmail(binder.edtAddEmail.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_email));
                    } else {
                        presenter.enterEmail(binder.edtAddEmail.getText().toString());
                    }
                } else {
                    showMessage(getResources().getString(R.string.validation_empty_fields));
                }
            }
        });
    }

    @Override
    protected void injectDependencies() {
        DaggerEnterEmailComponent.builder().build().inject(this);
    }

    @Override
    protected int getNavigationContainer() {
        return R.id.fragmentContainer;
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
    public void next() {
        Bundle bundle = new Bundle();
        bundle.putString("email",binder.edtAddEmail.getText().toString());
        fragmentReestablishPassword.setArguments(bundle);
        addFragmentToStack(fragmentReestablishPassword);
    }
    private boolean emptyField() {
        return binder.edtAddEmail.getText().toString().isEmpty();
    }
}
