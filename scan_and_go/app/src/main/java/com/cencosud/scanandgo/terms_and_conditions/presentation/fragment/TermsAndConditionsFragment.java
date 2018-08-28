package com.cencosud.scanandgo.terms_and_conditions.presentation.fragment;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.view.View;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentTermsAndConditionsBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.terms_and_conditions.di.component.DaggerTermsAndConditionsFragmentComponent;
import com.cencosud.scanandgo.terms_and_conditions.presentation.contract.TermsAndConditionsContract;
import com.core.presentation.fragment.BaseFragment;

import javax.inject.Inject;

/**
 * Created by consultor on 15-05-18.
 */

public class TermsAndConditionsFragment extends BaseFragment<FragmentTermsAndConditionsBinding> implements TermsAndConditionsContract.View {


    @Inject
    TermsAndConditionsContract.Presenter presenter;


    @Override
    protected void initView() {

        presenter.initialize(this);
        binder.progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorDivider), android.graphics.PorterDuff.Mode.MULTIPLY);
        binder.btnUnderstood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.checkTerms();
                if (getActivity() instanceof DrawerMenuActivity) {
                    ((DrawerMenuActivity) getActivity()).setScannerFragment();
                } else finishActivity();

            }
        });
    }

    @Override
    protected void injectDependencies() {

        DaggerTermsAndConditionsFragmentComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_terms_and_conditions;
    }


    public static TermsAndConditionsFragment newInstance() {
        TermsAndConditionsFragment instance = new TermsAndConditionsFragment();
        return instance;
    }

    @Override
    public void showProgress(boolean show) {

        binder.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showTermsAndConditions(String terms) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binder.viewTermsAndConditions.tvTermAndConditionsContent.setText(Html.fromHtml(terms, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binder.viewTermsAndConditions.tvTermAndConditionsContent.setText(Html.fromHtml(terms));
        }
    }

    @Override
    public void checkTerms() {
        binder.btnUnderstood.setBackground(getResources().getDrawable(R.drawable.shape_button_checkout));
        binder.btnUnderstood.setTextColor(getResources().getColor(R.color.white));
        binder.btnUnderstood.setPadding(60, 0, 80, 0);
        binder.btnUnderstood.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_white, 0);
    }

}
