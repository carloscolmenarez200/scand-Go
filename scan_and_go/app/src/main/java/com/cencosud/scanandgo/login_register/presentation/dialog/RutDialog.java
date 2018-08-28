package com.cencosud.scanandgo.login_register.presentation.dialog;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cencosud.scan_commons.utils.RutMaskEditText;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.DialogRutBinding;
import com.cencosud.scanandgo.databinding.MenuHeaderBinding;

/**
 * Created by carlos on 14-06-18.
 */

public class RutDialog extends DialogFragment {

    private RutDialog.NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Bundle bundle = getArguments();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.dialog_rut, null);
        builder.setView(mView);

        final DialogRutBinding dialogRutBinding = DataBindingUtil.bind(mView);

        if (!bundle.getBoolean("showRut")) {
            dialogRutBinding.textInputRut.setVisibility(View.GONE);
        }else{
            dialogRutBinding.textInputRut.addTextChangedListener(RutMaskEditText.insert(dialogRutBinding.textInputRut));
        }

        if (!bundle.getBoolean("showFirstName")) {
            dialogRutBinding.textInputFirstName.setVisibility(View.GONE);
        }

        if (!bundle.getBoolean("showLastName")) {
            dialogRutBinding.textInputLastName.setVisibility(View.GONE);
        }


        dialogRutBinding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRutDialogPositiveClick(dialogRutBinding.textInputRut.getText().toString(),
                        dialogRutBinding.textInputFirstName.getText().toString(), dialogRutBinding.textInputLastName.getText().toString(),bundle.getBoolean("showRut"),bundle.getBoolean("showFirstName"),bundle.getBoolean("showLastName"));
            }
        });

        return builder.create();
    }

    public void setmListener(RutDialog.NoticeDialogListener mListener) {
        this.mListener = mListener;
    }

    public interface NoticeDialogListener {
        void onRutDialogPositiveClick(String rut, String firstName, String lastName, boolean showRut, boolean showFirstName, boolean showLastName);
    }
}
