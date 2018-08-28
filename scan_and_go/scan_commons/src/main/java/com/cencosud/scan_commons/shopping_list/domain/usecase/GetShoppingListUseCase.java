package com.cencosud.scan_commons.shopping_list.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.shopping_list.data.local.ShoppingListLocal;
import com.cencosud.scan_commons.shopping_list.data.repository.ShoppingListLocalRepository;
import com.cencosud.scan_commons.shopping_list.data.repository.mapper.ShoppingListLocalToDomainMapper;
import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import javax.inject.Inject;

/**
 * Created by carlos on 13-07-18.
 */

public class GetShoppingListUseCase {

    private final ShoppingListLocalToDomainMapper mapper;
    private ShoppingListLocalRepository localRepository;

    @Inject
    public GetShoppingListUseCase(@NonNull ShoppingListLocalToDomainMapper mapper, @NonNull ShoppingListLocalRepository localRepository) {
        this.mapper = mapper;
        this.localRepository = localRepository;
    }

    public ShoppingList getShoppingList() {

        ShoppingListLocal shoppingListLocal = localRepository.getShoppingList();
        if(shoppingListLocal!=null){
            return mapper.map(localRepository.getShoppingList());
        }
        return null;
    }
}
