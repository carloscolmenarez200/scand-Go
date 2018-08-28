package com.cencosud.scanandgo.login_register.presentation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.utils.RutMaskEditText;
import com.cencosud.scan_commons.utils.Validator;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentRegisterBinding;
import com.cencosud.scanandgo.login_register.di.component.DaggerRegisterComponent;
import com.cencosud.scanandgo.login_register.presentation.activity.RegisterTokenActivity;
import com.cencosud.scanandgo.login_register.presentation.contract.RegisterContract;
import com.cencosud.scanandgo.terms_and_conditions.presentation.dialog.TermsAndConditionsDialog;
import com.core.presentation.fragment.BaseFragment;
import com.google.gson.Gson;
import javax.inject.Inject;

public class RegisterFragment extends BaseFragment<FragmentRegisterBinding> implements TextWatcher, RegisterContract.View, View.OnFocusChangeListener, TermsAndConditionsDialog.NoticeDialogListener {

    private static final String PREFIX = "569-";

    @Inject
    RegisterContract.Presenter presenter;

    @Inject
    TermsAndConditionsDialog termsAndConditionsDialog;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initView() {

        termsAndConditionsDialog.setCancelable(false);
        termsAndConditionsDialog.setmListener(this);

        binder.edtRegisterRut.addTextChangedListener(RutMaskEditText.insert(binder.edtRegisterRut));
        presenter.initialize(this);

        binder.edtRegisterRut.addTextChangedListener(this);
        binder.edtRegisterName.addTextChangedListener(this);
        binder.edtRegisterLastName.addTextChangedListener(this);
        binder.edtRegisterEmail.addTextChangedListener(this);
        binder.edtRegisterPhone.addTextChangedListener(this);
        binder.edtRegisterPassword.addTextChangedListener(this);
        binder.edtRegisterRePassword.addTextChangedListener(this);
        binder.edtRegisterPhone.setOnFocusChangeListener(this);
        binder.edtRegisterPassword.setOnFocusChangeListener(this);

        binder.edtRegisterPassword.setTransformationMethod(new PasswordTransformationMethod());
        binder.edtRegisterRePassword.setTransformationMethod(new PasswordTransformationMethod());

        binder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyField()) {
                    if (!Validator.validateRut(binder.edtRegisterRut.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_rut));
                    } else if (!Validator.validateEmail(binder.edtRegisterEmail.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_email));
                    } else if (!binder.edtRegisterPassword.getText().toString().equals(binder.edtRegisterRePassword.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_match_password));
                    } else if (!Validator.validatePasswordFormat(
                            true,
                            true,
                            true,
                            true,
                            false,
                            binder.edtRegisterPassword.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_password_format));
                    } else {
                        presenter.getTermsAndConditions();
                    }
                } else {
                    showMessage(getResources().getString(R.string.validation_empty_fields));
                }
            }
        });
    }

    @Override
    protected void injectDependencies() {
        DaggerRegisterComponent.builder().build().inject(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (binder.edtRegisterPhone.hasFocus()){

           if(binder.edtRegisterPhone.getText().length() < 4){
               setPhoneNumberPrefix();
           }else if(binder.edtRegisterPhone.getSelectionEnd()<5){
               binder.edtRegisterPhone.setText("");
               setPhoneNumberPrefix();
           }
        }

        validatePasswordFormat();
    }

    @Override
    public void afterTextChanged(Editable s) {

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
    public void goToRegisterToken(User user) {
        Intent intent = new Intent(getActivity(),RegisterTokenActivity.class);

        Bundle b = new Bundle();
        b.putString("user", new Gson().toJson(user));
        intent.putExtras(b);
        startActivity(intent);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        if (v.getId() == R.id.edt_register_phone) {
            if (binder.edtRegisterPhone.getText().length() < 4) {
                setPhoneNumberPrefix();
            } else if (!binder.edtRegisterPhone.hasFocus() && binder.edtRegisterPhone.getText().length() < 5) {
                binder.edtRegisterPhone.setText("");
            }
        }

        if (v.getId() == R.id.edt_register_password) {
            if (binder.edtRegisterPassword.hasFocus()) {
                binder.viewPasswordFormat.rlMainGroup.setVisibility(View.VISIBLE);
            } else {
                binder.viewPasswordFormat.rlMainGroup.setVisibility(View.GONE);
            }
        }


    }

    private void setPhoneNumberPrefix() {
        binder.edtRegisterPhone.removeTextChangedListener(this);
        binder.edtRegisterPhone.setText(PREFIX);
        binder.edtRegisterPhone.setSelection(PREFIX.length());
        binder.edtRegisterPhone.addTextChangedListener(this);
    }

    private User registerUser() {
        return new User(
                binder.edtRegisterName.getText().toString(),
                binder.edtRegisterLastName.getText().toString(),
                binder.edtRegisterPhone.getText().toString(),
                binder.edtRegisterRut.getText().toString(),
                binder.edtRegisterEmail.getText().toString(),
                binder.edtRegisterPassword.getText().toString());
    }

    private boolean emptyField() {
        return binder.edtRegisterRut.getText().toString().isEmpty() &&
                binder.edtRegisterName.getText().toString().isEmpty() &&
                binder.edtRegisterLastName.getText().toString().isEmpty() &&
                binder.edtRegisterEmail.getText().toString().isEmpty() &&
                binder.edtRegisterPhone.getText().toString().isEmpty() &&
                binder.edtRegisterPassword.getText().toString().isEmpty() &&
                binder.edtRegisterPassword.getText().toString().isEmpty();
    }

    private boolean validatePasswordFormat() {

        int GREEN = getResources().getColor(R.color.colorAccent);
        int WHITE = getResources().getColor(R.color.white);
        boolean isPasswordOk;

        // valida largo minimo de la contraseña
        if (binder.edtRegisterPassword.getText().length() > 7) {
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
                binder.edtRegisterPassword.getText().toString())
                && binder.edtRegisterPassword.getText().length() > 0) {
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
                binder.edtRegisterPassword.getText().toString())
                && binder.edtRegisterPassword.getText().length() > 0) {
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
                binder.edtRegisterPassword.getText().toString()) &&

                (Validator.validatePasswordFormat(
                        false,
                        true,
                        false,
                        false,
                        false,
                        binder.edtRegisterPassword.getText().toString()) ||
                        Validator.validatePasswordFormat(
                                true,
                                false,
                                false,
                                false,
                                false,
                                binder.edtRegisterPassword.getText().toString()))

                && binder.edtRegisterPassword.getText().length() > 1) {
            binder.viewPasswordFormat.ivCheckAlphaNumeric.setColorFilter(GREEN);
        } else {
            binder.viewPasswordFormat.ivCheckAlphaNumeric.setColorFilter(WHITE);
        }
        return true;
    }

    @Override
    public void showDialogTermsAndConditions(String terms){

        Bundle bundle = new Bundle();
        bundle.putString("terms",terms);
        termsAndConditionsDialog.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        if (termsAndConditionsDialog.isAdded()) {
            ft.show(termsAndConditionsDialog);
        } else {
            termsAndConditionsDialog.show(getFragmentManager(), "TermsAndConditionsDialogFragment");
        }
    }

    @Override
    public void dialogTermsAndConditionsDismiss() {
        termsAndConditionsDialog.dismiss();
    }


    @Override
    public void onAcceptTermsAndConditions() {
        presenter.register(registerUser());
    }
}
