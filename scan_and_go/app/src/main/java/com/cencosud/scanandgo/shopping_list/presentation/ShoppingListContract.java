package com.cencosud.scanandgo.shopping_list.presentation;

import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by carlos on 13-07-18.
 */

public interface ShoppingListContract {

    interface View extends IProgressView {
        void displayShoppingList(ShoppingList shoppingList);
        void emptyShoppingList(boolean show);
        void goToScanner();
    }

    interface Presenter extends BaseViewPresenter<View> {
        void saveList(List<String> shoppingList, String title);
        void deleteAll();
    }
}
