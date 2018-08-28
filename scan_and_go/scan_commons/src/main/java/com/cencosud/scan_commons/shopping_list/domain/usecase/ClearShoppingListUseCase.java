package com.cencosud.scan_commons.shopping_list.domain.usecase;

import com.cencosud.scan_commons.shopping_list.data.repository.ShoppingListLocalRepository;
import javax.inject.Inject;

/**
 * Created by carlos on 13-07-18.
 */

public class ClearShoppingListUseCase {

    private ShoppingListLocalRepository repositoryLocal;

    @Inject
    public ClearShoppingListUseCase(ShoppingListLocalRepository repositoryLocal) {
        this.repositoryLocal = repositoryLocal;
    }

    public boolean clearAll() {
        return repositoryLocal.clearAll();
    }
}
