package com.cencosud.scanandgo.terms_and_conditions.presentation.dialog;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.DialogTermsConditionsBinding;

/**
 * Created by carlos on 29-06-18.
 */

public class TermsAndConditionsDialog extends DialogFragment {

    private TermsAndConditionsDialog.NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Bundle bundle = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialogTerms);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.dialog_terms_conditions, null);
        builder.setView(mView);

        final DialogTermsConditionsBinding binder = DataBindingUtil.bind(mView);

        String terms = bundle.getString("terms");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binder.tvTermAndConditionsContent.setText(Html.fromHtml(terms, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binder.tvTermAndConditionsContent.setText(Html.fromHtml(terms));
        }

        binder.ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermsAndConditionsDialog.this.dismiss();
            }
        });

        binder.btnUnderstood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAcceptTermsAndConditions();
            }
        });

        return builder.create();
    }

    public void setmListener(TermsAndConditionsDialog.NoticeDialogListener mListener) {
        this.mListener = mListener;
    }

    public interface NoticeDialogListener {
        void onAcceptTermsAndConditions();
    }
}