package com.cencosud.scanandgo.shopping_list.presentation.fragment;

import android.content.DialogInterface;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentShoppingListBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.shopping_list.di.DaggerShoppingListFragmentComponent;
import com.cencosud.scanandgo.shopping_list.presentation.OnDeleteShoppingList;
import com.cencosud.scanandgo.shopping_list.presentation.ShoppingListContract;
import com.core.presentation.fragment.BaseFragment;
import com.core.util.AndroidUtils;
import com.core.util.DialogHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by carlos on 12-07-18.
 */

public class ShoppingListFragment extends BaseFragment<FragmentShoppingListBinding> implements ShoppingListContract.View, OnDeleteShoppingList {

    @Inject
    ShoppingListContract.Presenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_list;
    }

    @Override
    protected void initView() {
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        presenter.initialize(this);

        if (getActivity() instanceof DrawerMenuActivity) {
            DrawerMenuActivity drawerMenuActivity = (DrawerMenuActivity) getActivity();
            drawerMenuActivity.setOnDeleteShoppingList(this);
        }

        binder.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binder.tagsEditText.getTags().isEmpty()) {
                    Toast.makeText(getContext(), "Agregue un producto a la lista", Toast.LENGTH_LONG).show();
                } else
                    presenter.saveList(binder.tagsEditText.getTags(), String.valueOf(binder.edtTitle.getText()));
            }
        });
        binder.tagsEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                    emptyShoppingList(false);
                } else if (binder.tagsEditText.getTags().isEmpty()) {
                    emptyShoppingList(true);
                }
            }

        });
    }

    @Override
    protected void injectDependencies() {
        DaggerShoppingListFragmentComponent.builder().build().inject(this);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void displayShoppingList(ShoppingList shoppingList) {

        if (shoppingList != null && shoppingList.tags != null) {

            List<String> value = new ArrayList<>();
            for (TagsShopping tagsShopping : shoppingList.tags) {
                value.add(tagsShopping.name);
            }

            binder.tagsEditText.setTags(value.toArray(new String[value.size()]));
        }
    }

    @Override
    public void emptyShoppingList(boolean show) {

        binder.tvListOfProducts.setVisibility(show ? View.VISIBLE : View.GONE);
        binder.btnSave.setVisibility(show ? View.GONE : View.VISIBLE);
        if (!binder.tagsEditText.getTags().isEmpty()) {
            binder.btnSave.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void goToScanner() {
        if (getActivity() instanceof DrawerMenuActivity) {
            DrawerMenuActivity drawerMenuActivity = (DrawerMenuActivity) getActivity();
            drawerMenuActivity.setScannerFragment();
            AndroidUtils.hideKeyboard(getActivity());
        }
    }

    @Override
    public void onDeleteShoppingList() {

        if (binder.tagsEditText.getTags().size() > 0) {

            new DialogHelper().attachContext(getContext()).showConfirmationDialog(
                    R.string.delete_text_list,
                    R.string.delete_list_confirmation,
                    R.string.delete_list,
                    R.string.cancel_list,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.deleteAll();
                            binder.tagsEditText.setTags(new String[0]);
                        }
                    });
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
