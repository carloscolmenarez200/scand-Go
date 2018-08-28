package com.cencosud.scanandgo.shopping_list.presentation.presenter;


import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.cencosud.scan_commons.shopping_list.domain.usecase.ClearShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.GetShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.SetShoppingListUseCase;
import com.cencosud.scanandgo.shopping_list.presentation.ShoppingListContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by carlos on 13-07-18.
 */

public class ShoppingListPresenter implements ShoppingListContract.Presenter {

    private ShoppingListContract.View view;
    private final SetShoppingListUseCase setShoppingListUseCase;
    private final ClearShoppingListUseCase clearShoppingListUseCase;
    private final GetShoppingListUseCase getShoppingListUseCase;

    public ShoppingListPresenter(SetShoppingListUseCase setShoppingListUseCase, ClearShoppingListUseCase clearShoppingListUseCase, GetShoppingListUseCase getShoppingListUseCase) {
        this.setShoppingListUseCase = setShoppingListUseCase;
        this.clearShoppingListUseCase = clearShoppingListUseCase;
        this.getShoppingListUseCase = getShoppingListUseCase;
    }

    @Override
    public void initialize(ShoppingListContract.View view) {
        this.view = view;
        ShoppingList shoppingList = getShoppingListUseCase.getShoppingList();

        if (shoppingList != null && shoppingList.tags != null && !shoppingList.tags.isEmpty()) {
            view.emptyShoppingList(false);
            view.displayShoppingList(shoppingList);
        } else
            view.emptyShoppingList(true);
    }

    @Override
    public void saveList(List<String> shoppingList, String title) {

        ShoppingList value = new ShoppingList();
        value.tags = new ArrayList<>();

        for (String tag : shoppingList) {
            TagsShopping tagsShopping = new TagsShopping();
            tagsShopping.name = tag;
            tagsShopping.checked = false;
            value.tags.add(tagsShopping);
        }

        value.title = title;
        if (setShoppingListUseCase.setData(value).setShoppingList())
            view.goToScanner();

    }

    @Override
    public void deleteAll() {
        if (clearShoppingListUseCase.clearAll()) ;
        view.goToScanner();
    }

}
