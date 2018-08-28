package com.core.presentation.fragment;

import android.os.Bundle;
import android.view.View;

import com.core.presentation.contract.IProgressView;
import com.core.util.DialogHelper;

import javax.inject.Inject;

/**
 * Created by jhonnybarrios on 10/23/17.
 */

//TODO: buscar manera de eliminar esta base, puesto que no se puede condicionar a una funcionalidad una base
public abstract class BaseLoadingFragment extends BaseFragment implements IProgressView {
    @Inject protected LoadingDialogFragment loadingDialogFragment;
    @Inject protected DialogHelper dialogManager;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(dialogManager!=null)
            dialogManager.attachContext(CONTEXT);
    }

    @Override
    public void showProgress(boolean show) {
        if (!show)
            loadingDialogFragment.dismiss();
        else if (!loadingDialogFragment.isVisible() && getActivity()!=null)
            loadingDialogFragment.show(getActivity().getSupportFragmentManager(), LoadingDialogFragment.TAG);
    }

    /*@Override
    public void showMessageError(String message) {
        dialogManager.showErrorDialog(message);
    }*/

    @Override
    public void showMessage(String message) {
        dialogManager.showMessageDialog(message);
    }
}
