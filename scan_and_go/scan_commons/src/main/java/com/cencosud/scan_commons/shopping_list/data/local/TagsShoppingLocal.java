package com.cencosud.scan_commons.shopping_list.data.local;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by carlos on 19-07-18.
 */

public class TagsShoppingLocal extends RealmObject {

    @PrimaryKey
    public String name;
    public boolean checked;
}
