package com.cencosud.scanandgo.nps.presentation.presenter;

import com.cencosud.scan_commons.product.domain.usecase.DeleteProductsUseCase;
import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.cencosud.scan_commons.shopping_list.domain.usecase.GetShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.SetShoppingListUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.nps.domain.usecase.NpsUseCase;
import com.cencosud.scanandgo.nps.presentation.contract.NpsContract;
import com.core.domain.usecase.UseCaseObserver;

/**
 * Created by joseamaro on 12-06-18.
 */

public class NpsPresenter implements NpsContract.Presenter {

    private final GetUserUseCase getUserUseCase;
    private final NpsUseCase npsUseCase;
    private final CheckoutPreferences checkoutPreferences;
    private final StorePreferences storePreferences;
    private final Analytic analytic;
    private User user;
    private NpsContract.View view;

    private final DeleteProductsUseCase deleteProductsUseCase;
    private final SetShoppingListUseCase setShoppingListUseCase;
    private final GetShoppingListUseCase getShoppingListUseCase;

    public NpsPresenter(GetUserUseCase getUserUseCase, NpsUseCase npsUseCase, CheckoutPreferences checkoutPreferences, StorePreferences storePreferences, DeleteProductsUseCase deleteProductsUseCase, SetShoppingListUseCase setShoppingListUseCase, GetShoppingListUseCase getShoppingListUseCase, Analytic analytic) {
        this.getUserUseCase = getUserUseCase;
        this.npsUseCase = npsUseCase;
        this.checkoutPreferences = checkoutPreferences;
        this.storePreferences = storePreferences;
        this.deleteProductsUseCase = deleteProductsUseCase;
        this.setShoppingListUseCase = setShoppingListUseCase;
        this.getShoppingListUseCase = getShoppingListUseCase;
        this.analytic = analytic;
    }

    @Override
    public void initialize(NpsContract.View view) {
        this.view = view;
        getUserLocal();
    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            this.user = user;
            analytic.sendPageView("Encuesta", user.userProfileId);
        }
    }

    @Override
    public void sendNps(double score, String justification, String queue) {
        view.showProgress(true);
        npsUseCase.setData(user.fullName, user.email, storePreferences.getStoreID(), user.phoneNumber, storePreferences.getStoreName(), checkoutPreferences.getTotalPayment(), checkoutPreferences.getTypeCard(), queue, score, justification).execute(new UseCaseObserver<Boolean>() {
            @Override
            public void onNext(Boolean value) {
                view.showProgress(false);
                if (value) {
                    cleanCache();
                    view.goToScanner();
                }

            }

            @Override
            public void onError(Throwable e) {
                analytic.sendErrorView("Error al enviar la informacion", user.userProfileId);
                view.showProgress(false);
                super.onError(e);
            }
        });

    }

    @Override
    public void cleanCache() {
        checkoutPreferences.saveCardId("");
        checkoutPreferences.saveTotalProducts(0);
        checkoutPreferences.saveTotalPayment("");
        checkoutPreferences.saveReferenceNumber("");
        checkoutPreferences.savePaymentAuthorization("");
        checkoutPreferences.saveTransactionStatus("");
        checkoutPreferences.saveTransactionId("");
        deleteProductsUseCase.deleteProducts();
        storePreferences.saveNearbyStore(false);
        storePreferences.saveStoreId("");
        storePreferences.saveStoreName("");
        resetShoppingList();
    }

    private void resetShoppingList() {
        ShoppingList shoppingLists = getShoppingListUseCase.getShoppingList();

        if (shoppingLists != null && shoppingLists.tags != null) {

            for (TagsShopping tag : shoppingLists.tags) {
                tag.checked = false;
            }
            setShoppingListUseCase.setData(shoppingLists).setShoppingList();
        }
    }
}