package com.core.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import com.core.R;

/**
 * Created by jhonnybarrios on 10/23/17.
 */

public class DialogHelper {
    protected Context context;
    protected AlertDialog dialog;

    public DialogHelper attachContext(Context context) {
        this.context = context;
        return this;
    }

    public void showMessageDialog(int idString) {
        showMessageDialog(context.getString(idString));
    }

    public void showMessageDialog(String text) {
        showMessageDialog(text,R.string.default_title,null);
    }

    public void showMessageDialog(String text,DialogInterface.OnClickListener positiveClickListener) {
        showMessageDialog(text,R.string.default_title,positiveClickListener);
    }

    public void showMessageDialogWitchTitle(String title,String text,DialogInterface.OnClickListener positiveClickListener) {
        showMessageDialog(text,title,positiveClickListener);
    }

    public void showErrorDialog(int idString) {
        showErrorDialog(context.getString(idString));
    }

    public void showErrorDialog(String text) {
        showMessageDialog(text, R.string.ups,null);
    }

    public void showMessageDialog(String text, String title, final DialogInterface.OnClickListener positiveClickListener) {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
            dialog = null;
        }
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            dialog = builder.setCancelable(false)
                    .setTitle(title)
                    .setMessage(text)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(positiveClickListener!=null){
                                positiveClickListener.onClick(dialog,0);
                            }
                            dialog.cancel();
                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessageDialog(String text, @StringRes final int title, final DialogInterface.OnClickListener positiveClickListener) {
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
            dialog = null;
        }
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            dialog = builder.setCancelable(false)
                    .setTitle(title)
                    .setMessage(text)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(positiveClickListener!=null){
                                positiveClickListener.onClick(dialog,title);
                            }
                            dialog.cancel();
                        }
                    }).create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showConfirmationDialog(int title, int message, int positiveButtonTitle, int negativeButtonTitle, DialogInterface.OnClickListener positiveClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        AlertDialog dialog = builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveButtonTitle,positiveClickListener)
                .setNegativeButton(negativeButtonTitle, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }
}