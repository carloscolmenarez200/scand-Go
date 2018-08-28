package com.cencosud.scan_commons.product.data.local;
import android.content.Context;
import com.core.data.local.preferences.Preferences;
import javax.inject.Inject;

/**
 * Created by carlos on 28-05-18.
 */

public class ProductPreferences extends Preferences {

    enum Key {countCart}

    @Inject
    public ProductPreferences(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "ProductPreferences";
    }

    public int getCountCart() {
        return getInt(ProductPreferences.Key.countCart);
    }

    public void saveCountCart(int value) {
        save(ProductPreferences.Key.countCart, value);
    }

}
