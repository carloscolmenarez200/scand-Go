package com.cencosud.scan_commons.shopping_list.data.local;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by carlos on 13-07-18.
 */

public class ShoppingListLocal extends RealmObject {

    @PrimaryKey
    public String title;
    public RealmList<TagsShoppingLocal> tags;
}
