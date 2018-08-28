package com.cencosud.scan_commons.shopping_list.data.repository.mapper;


import com.cencosud.scan_commons.shopping_list.data.local.ShoppingListLocal;
import com.cencosud.scan_commons.shopping_list.data.local.TagsShoppingLocal;
import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.core.data.repository.mapper.Mapper;
import javax.inject.Inject;
import io.realm.RealmList;

/**
 * Created by carlos on 13-07-18.
 */

public class ShoppingListLocalToDomainMapper  extends Mapper<ShoppingListLocal, ShoppingList> {

    private final TagsShoppingLocalToDomainMapper mapper;

    @Inject
    public ShoppingListLocalToDomainMapper(TagsShoppingLocalToDomainMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public ShoppingList map(ShoppingListLocal value) {
        ShoppingList shoppingList = new ShoppingList();
        if(value.tags!=null){
            shoppingList.tags = mapper.map(value.tags);
        }
        shoppingList.title = value.title;
        return shoppingList;
    }

    @Override
    public ShoppingListLocal reverseMap(ShoppingList value) {
        ShoppingListLocal shoppingListLocal = new ShoppingListLocal();
        if(value.tags!=null){
            shoppingListLocal.tags = new RealmList<>(mapper.reverseMap(value.tags).toArray(new TagsShoppingLocal[value.tags.size()]));
        }
        shoppingListLocal.title = value.title;
        return shoppingListLocal;
    }
}
