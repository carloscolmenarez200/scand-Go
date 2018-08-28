package com.cencosud.scan_commons.shopping_list.domain.usecase;

import com.cencosud.scan_commons.shopping_list.data.repository.ShoppingListLocalRepository;
import com.cencosud.scan_commons.shopping_list.data.repository.mapper.ShoppingListLocalToDomainMapper;
import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import javax.inject.Inject;

/**
 * Created by carlos on 13-07-18.
 */

public class SetShoppingListUseCase {

    private ShoppingListLocalRepository repositoryLocal;
    private final ShoppingListLocalToDomainMapper mapper;
    private ShoppingList shoppingList;

    @Inject
    public SetShoppingListUseCase(ShoppingListLocalRepository repositoryLocal, ShoppingListLocalToDomainMapper mapper) {
        this.repositoryLocal = repositoryLocal;
        this.mapper = mapper;
    }

    public SetShoppingListUseCase setData(ShoppingList shoppingList){
        this.shoppingList= shoppingList;
        return this;
    }

    public boolean setShoppingList() {
        return repositoryLocal.addOrUpdateProduct(mapper.reverseMap(shoppingList));
    }
}
