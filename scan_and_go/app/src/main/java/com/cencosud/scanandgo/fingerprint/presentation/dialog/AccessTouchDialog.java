package com.cencosud.scanandgo.fingerprint.presentation.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cencosud.scanandgo.R;

/**
 * Created by joseamaro on 14-08-18.
 */

public class AccessTouchDialog extends DialogFragment {

    private AccessTouchDialog.NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View mView = inflater.inflate(R.layout.dialog_fingerprinter_reader, null);
        builder.setView(mView);

        Button btnAccept = (Button) mView.findViewById(R.id.btn_accept_touch);
        TextView text = (TextView) mView.findViewById(R.id.tv_not_thanks);
        final CheckBox checkBox = (CheckBox) mView.findViewById(R.id.ch_not_dialog);
        LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.ly_not_dialog);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
                mListener.onNotShowDialogClick(checkBox.isChecked());
            }
        });/*
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
                mListener.onNotShowDialogClick(checkBox.isChecked());
            }
        });
        */
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAccessTouchDialogNegativeClick();
            }
        });
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onAccessTouchDialogPositiveClick();
            }
        });

        return builder.create();
    }

    public void setmListener(NoticeDialogListener mListener) {
        this.mListener = mListener;
    }


    public interface NoticeDialogListener {
        void onAccessTouchDialogPositiveClick();
        void onNotShowDialogClick(boolean notDialog);
        void onAccessTouchDialogNegativeClick();
    }
}
