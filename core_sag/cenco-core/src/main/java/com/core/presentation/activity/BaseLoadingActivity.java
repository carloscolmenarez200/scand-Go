package com.core.presentation.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import com.core.presentation.contract.IProgressView;
import com.core.presentation.fragment.LoadingDialogFragment;
import com.core.util.DialogHelper;
import javax.inject.Inject;

/**
 * Created by jhonnybarrios on 10/23/17.
 */

public abstract class BaseLoadingActivity<BINDER extends ViewDataBinding> extends BaseActivity<BINDER> implements IProgressView {

    @Inject protected LoadingDialogFragment loadingDialogFragment;
    @Inject protected DialogHelper dialogManager;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(dialogManager!=null)
            dialogManager.attachContext(this);
    }

    @Override public void showProgress(boolean show) {
        if (!show)
            loadingDialogFragment.dismiss();
        else if (!loadingDialogFragment.isVisible())
            loadingDialogFragment.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
    }

    /*
    @Override public void showMessageError(String message) {
        dialogManager.showErrorDialog(message);
    }*/

    @Override public void showMessage(String message) {
        dialogManager.showMessageDialog(message);
    }
}
