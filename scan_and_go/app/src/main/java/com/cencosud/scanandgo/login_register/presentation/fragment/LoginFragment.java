package com.cencosud.scanandgo.login_register.presentation.fragment;


import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Toast;
import com.cencosud.scan_commons.utils.Validator;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentLoginBinding;
import com.cencosud.scanandgo.fingerprint.presentation.OnFingerprintListener;
import com.cencosud.scanandgo.fingerprint.presentation.activity.Fingerprint;
import com.cencosud.scanandgo.fingerprint.presentation.dialog.AccessTouchDialog;
import com.cencosud.scanandgo.fingerprint.presentation.dialog.SuccessFingerprintDialog;
import com.cencosud.scanandgo.login_register.di.component.DaggerLoginComponent;
import com.cencosud.scanandgo.login_register.presentation.contract.LoginContract;
import com.cencosud.scanandgo.login_register.presentation.dialog.RutDialog;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.onboarding.presentation.activity.OnBoardingActivity;
import com.cencosud.scanandgo.recover_password.presentation.activity.RecoverPasswordActivity;
import com.cencosud.scanandgo.terms_and_conditions.presentation.dialog.TermsAndConditionsDialog;
import com.core.presentation.fragment.BaseFragment;
import com.core.util.DialogHelper;

import javax.inject.Inject;

public class LoginFragment extends BaseFragment<FragmentLoginBinding> implements LoginContract.View, RutDialog.NoticeDialogListener, AccessTouchDialog.NoticeDialogListener, SuccessFingerprintDialog.NoticeDialogListener, TermsAndConditionsDialog.NoticeDialogListener, OnFingerprintListener {

    @Inject
    LoginContract.Presenter presenter;

    @Inject
    RutDialog rutDialog;

    @Inject
    AccessTouchDialog touchDialog;

    @Inject
    SuccessFingerprintDialog successFingerprintDialog;

    @Inject
    TermsAndConditionsDialog termsAndConditionsDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initView() {

        rutDialog.setCancelable(false);
        touchDialog.setCancelable(false);
        rutDialog.setmListener(this);
        touchDialog.setmListener(this);
        successFingerprintDialog.setmListener(this);
        successFingerprintDialog.setCancelable(false);
        termsAndConditionsDialog.setCancelable(false);
        termsAndConditionsDialog.setmListener(this);
        binder.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emptyField()) {
                    if (!Validator.validateEmail(binder.edtLoginEmail.getText().toString())) {
                        showMessage(getResources().getString(R.string.validation_email));
                    } else {
                        presenter.login(binder.edtLoginEmail.getText().toString(), binder.edtLoginPassword.getText().toString());
                    }
                } else {
                    showMessage(getResources().getString(R.string.validation_empty_fields));
                }
            }
        });

        binder.edtLoginPassword.setTransformationMethod(new PasswordTransformationMethod());
     /*
        binder.tvTermAndConditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TermsAndConditionsActivity.class);
            }
        }); */

        binder.tvRecoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.analyticRecoverPassword();
                startActivity(RecoverPasswordActivity.class);
            }
        });

        presenter.initialize(this);
    }

    private boolean emptyField() {
        return binder.edtLoginEmail.getText().toString().isEmpty() &&
                binder.edtLoginPassword.getText().toString().isEmpty();
    }

    @Override
    protected void injectDependencies() {
        DaggerLoginComponent.builder().build().inject(this);
    }


    @Override
    public void showProgress(boolean show) {
        binder.progress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(CONTEXT, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToDashboard() {
        startActivity(DrawerMenuActivity.class);
        finishActivity();
    }

    @Override
    public void goToOnBoarding() {
        startActivity(OnBoardingActivity.class);
        finishActivity();
    }

    @Override
    public void showRutDialog(boolean showRut, boolean showFirstName, boolean showLastName) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("showRut",showRut);
        bundle.putBoolean("showFirstName", showFirstName);
        bundle.putBoolean("showLastName", showLastName);
        rutDialog.setArguments(bundle);
        rutDialog.show(getFragmentManager(), "dialogRut");
    }

    @Override
    public void onRutDialogPositiveClick(String rut,String firstName, String lastName,boolean showRut, boolean showFirstName, boolean showLastName) {
        if (showRut && !Validator.validateRut(rut)) {
            showMessage(getResources().getString(R.string.validation_rut));
        } else if (showFirstName && firstName.isEmpty()) {
            showMessage(getResources().getString(R.string.validation_first_name));
        }else if(showLastName && lastName.isEmpty()) {
            showMessage(getResources().getString(R.string.validation_last_name));
        }else{
            rutDialog.dismiss();
            presenter.updateUser(rut,firstName,lastName);
        }
    }

    @Override
    public void showDialogNotWhiteList() {
        new DialogHelper().attachContext(getContext()).showMessageDialogWitchTitle("Scan&Go","Por el momento la APP solo esta disponible para colaboradores de JUMBO.\n" +
                "Cuando la APP este disponible para todo público te avisaremos vía correo.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onAcceptTermsAndConditions() {
        presenter.updateTermsAndConditions();
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

    public void showFingerPrintDialog(){

        touchDialog.show(getFragmentManager(), "dialogTouch");
    }

    @Override
    public Fingerprint initFingerprint() {
        return new Fingerprint(getContext(), this);
    }

    @Override
    public void onAccessTouchDialogPositiveClick() {
        presenter.updateFingerprintPreference(binder.edtLoginEmail.getText().toString(), binder.edtLoginPassword.getText().toString());
        touchDialog.dismiss();
        successFingerprintDialog.show(getFragmentManager(), "dialogFingerprintSuccess");
    }

    @Override
    public void onNotShowDialogClick(boolean notDialog) {
        presenter.updateNotDialogFingerprintPreference(notDialog);
    }

    @Override
    public void onAccessTouchDialogNegativeClick() {
        touchDialog.dismiss();
        presenter.saveUser();
    }

    @Override
    public void onSuccessFingerprintDialogPositiveClick() {
        successFingerprintDialog.dismiss();
        presenter.saveUser();
    }

    @Override
    public void onSuccessFingerprint() {
        presenter.loginFingerprint();
    }

    @Override
    public void onErrorFingerprint() {

    }
}
