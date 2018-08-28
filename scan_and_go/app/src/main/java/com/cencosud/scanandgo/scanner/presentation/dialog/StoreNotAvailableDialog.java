package com.cencosud.scanandgo.scanner.presentation.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import com.cencosud.scanandgo.R;

/**
 * Created by carlos on 04-07-18.
 */

public class StoreNotAvailableDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomDialog);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mView = inflater.inflate(R.layout.store_not_scan_and_go, null);
        builder.setView(mView);

        return builder.create();
    }
}
