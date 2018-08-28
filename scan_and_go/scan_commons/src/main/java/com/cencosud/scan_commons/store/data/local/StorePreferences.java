package com.cencosud.scan_commons.store.data.local;

import android.content.Context;
import com.core.data.local.preferences.Preferences;
import javax.inject.Inject;

/**
 * Created by carlos on 03-07-18.
 */

public class StorePreferences extends Preferences {

    enum Key {nearbyStore, storeId, storeName}

    @Inject
    public StorePreferences(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "StorePreferences";
    }


    public boolean isNearbyStore() {
        return getBool(Key.nearbyStore);
    }

    public void saveNearbyStore(boolean value) {
        save(Key.nearbyStore, value);
    }

    public String getStoreID() {
        return getString(Key.storeId);
    }

    public void saveStoreId(String value) {
        save(Key.storeId, value);
    }

    public String getStoreName() {
        return getString(Key.storeName);
    }

    public void saveStoreName(String value) {
        save(Key.storeName, value);
    }
}
