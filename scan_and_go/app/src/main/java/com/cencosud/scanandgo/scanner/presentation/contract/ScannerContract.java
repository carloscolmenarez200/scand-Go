package com.cencosud.scanandgo.scanner.presentation.contract;

import android.location.Location;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;

import java.util.List;

/**
 * Created by joseamaro on 24-05-18.
 */

public interface ScannerContract {

    interface View extends IProgressView {
      void displayProduct(Product product);
      void onSaveProduct();
      void showProductNotFount();
      void showStoreNotAvailable(boolean show);
      void displayShoppingList(ShoppingList tagsShopping);
      void notShoppingList();
    }

    interface Presenter extends BaseViewPresenter<View> {
        void getProduct(String ean);
        double totalPrice();
        void setProduct(Product product);
        int totalQuantity();
        void analyticSendAction(String label);
        void saveTagShopping(int position,TagsShopping tagsShopping);
        void calculateLocation(Location location);
    }
}
