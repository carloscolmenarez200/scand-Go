package com.cencosud.scanandgo.menu.presentation.presenter;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.product.domain.usecase.DeleteProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.ClearShoppingListUseCase;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.ClearUserUseCase;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.menu.presentation.DrawerMenuContract;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetContactsUseCase;
import com.core.domain.usecase.UseCaseObserver;

import java.util.List;

/**
 * Created by carlos on 28-05-18.
 */

public class DrawerMenuPresenter implements DrawerMenuContract.Presenter{

    private DrawerMenuContract.View view;
    private final GetProductsUseCase getProductsUseCase;
    private final UserPreferences preferences;
    private final ClearUserUseCase clearUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final CheckoutPreferences checkoutPreferences;
    private final DeleteProductsUseCase deleteProductsUseCase;
    private final GetContactsUseCase getContactsUseCase;
    private final ClearShoppingListUseCase clearShoppingListUseCase;
    private final Analytic analytic;

    private User user;

    public DrawerMenuPresenter(GetProductsUseCase getProductsUseCase, UserPreferences preferences, ClearUserUseCase clearUserUseCase, GetUserUseCase getUserUseCase, CheckoutPreferences checkoutPreferences, DeleteProductsUseCase deleteProductsUseCase, GetContactsUseCase getContactsUseCase, Analytic analytic,ClearShoppingListUseCase clearShoppingListUseCase){
        this.getProductsUseCase = getProductsUseCase;
        this.preferences = preferences;
        this.clearUserUseCase = clearUserUseCase;
        this.getUserUseCase =  getUserUseCase;
        this.checkoutPreferences = checkoutPreferences;
        this.deleteProductsUseCase = deleteProductsUseCase;
        this.getContactsUseCase = getContactsUseCase;
        this.clearShoppingListUseCase = clearShoppingListUseCase;
        this.analytic = analytic;
    }

    @Override
    public void initialize(DrawerMenuContract.View view) {
        this.view = view;
        getCountCart();
        getUserLocal();
        getContacts();

    }

    @Override
    public void getCountCart() {

       List<Product> products = getProductsUseCase.getProducts();
       if(products!=null){
           view.displayCountCart(totalCart(products));
       }

    }

    @Override
    public void logout() {
        if(clearUserUseCase.clearUSer()){
            cleanCache();
            view.goToLogin();
        }
    }

    @Override
    public void toolTips() {
        List<Product> products = getProductsUseCase.getProducts();
        if(products!=null){
            int total = totalCart(products);
            if(total ==1 || total == 29){
                view.showToolTips();
            }
        }
    }

    @Override
    public void analyticSedAction(String action, String label) {
        analytic.sendAction("Scan And Go", action, user.userProfileId, label);
    }

    @Override
    public void sendActionView() {
        analytic.sendPageView("Menu", user.userProfileId);

    }

    private void getContacts() {
        getContactsUseCase.execute(new UseCaseObserver<List<String>>() {
            @Override
            public void onNext(List<String> value) {
               view.showContacts(value);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    private int totalCart(List<Product> products){
        int total = 0;
        for (Product product: products){
            total = total + product.productQuantity;
        }
        return total;
    }

    private void getUserLocal() {

        user = getUserUseCase.getLoggedUser();

        if(user!=null){
            view.displayUser(user);
        }else view.goToLogin();

    }

    private void cleanCache(){

        preferences.saveJwtToken("");

        checkoutPreferences.saveCardId("");
        checkoutPreferences.saveTotalProducts(0);
        checkoutPreferences.saveTotalPayment("");
        checkoutPreferences.saveReferenceNumber("");
        checkoutPreferences.savePaymentAuthorization("");
        deleteProductsUseCase.deleteProducts();
        checkoutPreferences.saveTransactionStatus("");
        checkoutPreferences.saveTransactionId("");

        clearShoppingListUseCase.clearAll();
    }


}
