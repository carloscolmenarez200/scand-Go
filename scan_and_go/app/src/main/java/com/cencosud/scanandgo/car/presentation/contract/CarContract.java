package com.cencosud.scanandgo.car.presentation.contract;


import com.cencosud.scan_commons.product.domain.model.Product;
import com.core.presentation.contract.BaseViewPresenter;
import com.core.presentation.contract.IProgressView;
import java.util.List;

/**
 * Created by joseamaro on 03-05-18.
 */

public interface CarContract {
    interface View extends IProgressView {
        void displayProducts(List<Product> items);
        void deleteProduct(int position);
        void deleteProducts();
    }

    interface Presenter extends BaseViewPresenter<View> {
        void deleteProduct(int position, Product item);
        void deleteProducts();
        void setProducts(List<Product> products);
        void sendActionCar(String label);

    }
}
