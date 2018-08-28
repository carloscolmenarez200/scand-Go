package com.cencosud.scanandgo.checkout.presentation.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.cencosud.scanandgo.R;

/**
 * Created by joseamaro on 30-05-18.
 */

public class CheckoutSuccessDialog extends DialogFragment {

    private CheckoutSuccessDialog.NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.dialog_success_qr, null);
        builder.setView(mView);

        Button btnAccept = (Button) mView.findViewById(R.id.btn_checkout_sucess);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSuccessCheckoutDialogPositiveClick();
            }
        });

        return builder.create();
    }

    public void setmListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }


    public interface NoticeDialogListener {
        void onSuccessCheckoutDialogPositiveClick();
    }

}