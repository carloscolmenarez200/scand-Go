package com.cencosud.scanandgo.recover_password.presentation.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;

import com.cencosud.scan_commons.utils.Validator;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentReestablishPasswordBinding;
import com.cencosud.scanandgo.recover_password.di.component.DaggerReestablishPasswordComponent;
import com.cencosud.scanandgo.recover_password.presentation.contract.ReestablishPasswordContract;
import com.core.presentation.fragment.BaseStackFragment;

import javax.inject.Inject;

/**
 * Created by joseamaro on 18-06-18.
 */

public class FragmentReestablishPassword extends BaseStackFragment<FragmentReestablishPasswordBinding> implements ReestablishPasswordContract.View, TextWatcher, View.OnFocusChangeListener {

    @Inject
    FragmentEnterToken fragmentEnterToken;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reestablish_password;
    }

    @Override
    protected void initView() {

        binder.edtRecoverPassword.addTextChangedListener(this);
        binder.edtRecoverPassword.setOnFocusChangeListener(this);
        binder.edtConfirmPassword.addTextChangedListener(this);
        binder.edtConfirmPassword.setOnFocusChangeListener(this);

        binder.edtRecoverPassword.setTransformationMethod(new PasswordTransformationMethod());
        binder.edtConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());

        binder.flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        binder.btnReestablish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyField()) {
                    if (!binder.edtRecoverPassword.getText().toString().equals(binder.edtConfirmPassword.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_match_password));
                    } else if (!Validator.validatePasswordFormat(
                            true,
                            true,
                            true,
                            true,
                            false,
                            binder.edtRecoverPassword.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_password_format));
                    } else {
                        Bundle bundle = getArguments();
                        bundle.putString("password", binder.edtRecoverPassword.getText().toString());
                        fragmentEnterToken.setArguments(bundle);
                        next();
                    }
                } else {
                    showMessage(getResources().getString(R.string.validation_empty_fields));
                }
            }
        });
    }

    @Override
    protected void injectDependencies() {

        DaggerReestablishPasswordComponent.builder().build().inject(this);

    }

    @Override
    protected int getNavigationContainer() {
        return R.id.fragmentContainer;
    }

    private boolean validatePasswordFormat() {

        int GREEN = getResources().getColor(R.color.colorAccent);
        int WHITE = getResources().getColor(R.color.white);
        boolean isPasswordOk;

        // valida largo minimo de la contraseña
        if (binder.edtRecoverPassword.getText().length() > 7) {
            binder.viewPasswordFormat.ivCheckCount.setColorFilter(GREEN);

        } else {
            binder.viewPasswordFormat.ivCheckCount.setColorFilter(WHITE);
        }

        // valida la existencia de una mayuscula
        if (Validator.validatePasswordFormat(
                true,
                false,
                false,
                true,
                false,
                binder.edtRecoverPassword.getText().toString())
                && binder.edtRecoverPassword.getText().length() > 0) {
            binder.viewPasswordFormat.ivCheckUppercase.setColorFilter(GREEN);
        } else {
            binder.viewPasswordFormat.ivCheckUppercase.setColorFilter(WHITE);
        }

        // valida la existencia de una minuscula
        if (Validator.validatePasswordFormat(
                false,
                true,
                false,
                true,
                false,
                binder.edtRecoverPassword.getText().toString())
                && binder.edtRecoverPassword.getText().length() > 0) {
            binder.viewPasswordFormat.ivCheckLowercase.setColorFilter(GREEN);
        } else {
            binder.viewPasswordFormat.ivCheckLowercase.setColorFilter(WHITE);
        }
        // valida que exista al menos dos caracteres 1 numero y 1 mayuscula o 1 minuscula
        if (Validator.validatePasswordFormat(
                false,
                false,
                true,
                false,
                false,
                binder.edtRecoverPassword.getText().toString()) &&

                (Validator.validatePasswordFormat(
                        false,
                        true,
                        false,
                        false,
                        false,
                        binder.edtRecoverPassword.getText().toString()) ||
                        Validator.validatePasswordFormat(
                                true,
                                false,
                                false,
                                false,
                                false,
                                binder.edtRecoverPassword.getText().toString()))

                && binder.edtRecoverPassword.getText().length() > 1) {
            binder.viewPasswordFormat.ivCheckAlphaNumeric.setColorFilter(GREEN);
        } else {
            binder.viewPasswordFormat.ivCheckAlphaNumeric.setColorFilter(WHITE);
        }
        return true;
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(CONTEXT, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        validatePasswordFormat();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private boolean emptyField() {
        return binder.edtRecoverPassword.getText().toString().isEmpty() &&
                binder.edtConfirmPassword.getText().toString().isEmpty();
    }

    @Override
    public void next() {
        addFragmentToStack(fragmentEnterToken);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.edt_recover_password) {
            if (binder.edtRecoverPassword.hasFocus()) {
                binder.viewPasswordFormat.rlMainGroup.setVisibility(View.VISIBLE);
            } else {
                binder.viewPasswordFormat.rlMainGroup.setVisibility(View.GONE);
            }
        }
    }
}
