package com.cencosud.scanandgo.car.presentation.presenter;




import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.product.domain.usecase.DeleteProductUseCase;
import com.cencosud.scan_commons.product.domain.usecase.DeleteProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.SetProductsUseCase;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.car.presentation.contract.CarContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joseamaro on 03-05-18.
 */

public class CarPresenter implements CarContract.Presenter {

    private final GetProductsUseCase getProductsUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final DeleteProductsUseCase deleteProductsUseCase;
    private final SetProductsUseCase setProductsUseCase;
    private final GetUserUseCase getUserUseCase;
    private final Analytic analytic;

    private User user;
    private CarContract.View view;

    @Inject
    public CarPresenter(GetProductsUseCase getProductsUseCase, DeleteProductUseCase deleteProductUseCase, DeleteProductsUseCase deleteProductsUseCase, SetProductsUseCase setProductsUseCase, GetUserUseCase getUserUseCase, Analytic analytic) {
        this.getProductsUseCase = getProductsUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.deleteProductsUseCase = deleteProductsUseCase;
        this.setProductsUseCase = setProductsUseCase;
        this.getUserUseCase = getUserUseCase;
        this.analytic = analytic;
    }

    @Override
    public void initialize(CarContract.View view) {
        this.view = view;
        analytic.sendStartCar(getProductsUseCase.getProducts());
        getProducts();
        getUserLocal();
    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            this.user = user;
            analytic.sendPageView("Mi Carrito",  user.userProfileId);
        }
    }

    private void getProducts() {

        view.displayProducts(getProductsUseCase.getProducts());
    }

    @Override
    public void deleteProduct(final int position, Product item) {

        if (deleteProductUseCase.setData(item).deleteProduct()) {
            view.deleteProduct(position);
            analytic.sendRemoveToCar(item.ean, item.name, item.brandName, item.amount, item.productQuantity);
        }
    }

    @Override
    public void deleteProducts() {
        if (deleteProductsUseCase.deleteProducts()) {
            view.deleteProducts();
            sendActionCar("Eliminar");
        }
    }

    @Override
    public void setProducts(List<Product> products) {
        setProductsUseCase.setData(products).setProducts();
    }

    @Override
    public void sendActionCar(String label) {
        analytic.sendAction("Scan And Go", "Carrito", user.userProfileId, label);
    }

}
