package com.cencosud.scanandgo.login_register.presentation.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentRegisterTokenBinding;
import com.cencosud.scanandgo.login_register.di.component.DaggerRegisterTokenFragmentComponent;
import com.cencosud.scanandgo.login_register.presentation.OnRegisterSuccess;
import com.cencosud.scanandgo.login_register.presentation.activity.LoginRegisterActivity;
import com.cencosud.scanandgo.login_register.presentation.contract.RegisterTokenContract;
import com.cencosud.scanandgo.onboarding.presentation.activity.OnBoardingActivity;
import com.core.presentation.fragment.BaseFragment;
import com.core.util.DialogHelper;
import com.google.gson.Gson;
import javax.inject.Inject;

/**
 * Created by carlos on 14-06-18.
 */

public class RegisterTokenFragment extends BaseFragment<FragmentRegisterTokenBinding> implements RegisterTokenContract.View{

    private OnRegisterSuccess onRegisterSuccess;
    private User user;

    @Inject
    RegisterTokenContract.Presenter presenter;

    @Override
    protected void initView() {
        presenter.initialize(this);
        String userJson = getArguments().getString("user");
        user = new Gson().fromJson(userJson, User.class);

        binder.tvEmail.setText(user.email);

        binder.flBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });

        binder.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binder.edtToken.getText().toString().isEmpty()){
                    user.token = binder.edtToken.getText().toString();
                    presenter.register(user);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_token;
    }


    @Override
    protected void injectDependencies() {
        DaggerRegisterTokenFragmentComponent.builder().build().inject(this);

    }

    public void setOnRegisterSuccess(OnRegisterSuccess onRegisterSuccess) {
        this.onRegisterSuccess = onRegisterSuccess;
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
    public void registerFinish() {
        onRegisterSuccess.onRegisterSuccess();
    }

    @Override
    public void showDialogNotWhiteList() {
        new DialogHelper().attachContext(getContext()).showMessageDialogWitchTitle("Registro exitoso","Por el momento la APP solo esta disponible para colaboradores de JUMBO.\n" +
                "Cuando la APP este disponible para todo público te avisaremos vía correo.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getContext(), LoginRegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finishActivity();
            }
        });
    }
}
