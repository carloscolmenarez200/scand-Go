package com.cencosud.scanandgo.scanner.presentation.dialog;

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

public class CodeNotFoundDialog extends DialogFragment {

    private CodeNotFoundDialog.NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.dialog_product_not_found, null);
        builder.setView(mView);

        Button btnAccept = (Button) mView.findViewById(R.id.btn_go_scan);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCodeNotFoundDialogPositiveClick();
            }
        });

        return builder.create();
    }

    public void setListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }


    public interface NoticeDialogListener {
        void onCodeNotFoundDialogPositiveClick();
    }

}
