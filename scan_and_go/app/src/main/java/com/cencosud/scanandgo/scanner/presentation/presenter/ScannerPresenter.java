package com.cencosud.scanandgo.scanner.presentation.presenter;

import android.location.Location;
import android.util.Log;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.product.domain.usecase.GetProductRemoteUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.SetProductUseCase;
import com.cencosud.scan_commons.shopping_list.domain.model.ShoppingList;
import com.cencosud.scan_commons.shopping_list.domain.model.TagsShopping;
import com.cencosud.scan_commons.shopping_list.domain.usecase.GetShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.SetShoppingListUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.cencosud.scan_commons.store.domain.usecase.GetStoresJumboLocalUseCase;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scan_commons.utils.MyLocationManager;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.scanner.presentation.contract.ScannerContract;
import com.core.domain.usecase.UseCaseObserver;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by joseamaro on 24-05-18.
 */

public class ScannerPresenter implements ScannerContract.Presenter {


    private final GetProductsUseCase getProductsUseCase;
    private final GetProductUseCase getProductUseCase;
    private final GetProductRemoteUseCase getProductRemoteUseCase;
    private final SetProductUseCase setProductUseCase;
    private final GetUserUseCase getUserUseCase;
    private final StorePreferences storePreferences;
    private final GetShoppingListUseCase getShoppingListUseCase;
    private final SetShoppingListUseCase setShoppingListUseCase;
    private final GetStoresJumboLocalUseCase getStoresJumboLocalUseCase;
    private final Analytic analytic;
    private ScannerContract.View view;
    private User user;
    private ShoppingList shoppingList;
    private static float RADIUS = 200;


    public ScannerPresenter(GetProductUseCase getProductUseCase, GetProductsUseCase getProductsUseCase, GetProductRemoteUseCase getProductRemoteUseCase, SetProductUseCase setProductUseCase, Analytic analytic, GetUserUseCase getUserUseCase, StorePreferences storePreferences, GetShoppingListUseCase getShoppingListUseCase, SetShoppingListUseCase setShoppingListUseCase, GetStoresJumboLocalUseCase getStoresJumboLocalUseCase) {
        this.getProductUseCase = getProductUseCase;
        this.getProductsUseCase = getProductsUseCase;
        this.getProductRemoteUseCase = getProductRemoteUseCase;
        this.setProductUseCase = setProductUseCase;
        this.analytic = analytic;
        this.getUserUseCase = getUserUseCase;
        this.storePreferences = storePreferences;
        this.getShoppingListUseCase = getShoppingListUseCase;
        this.setShoppingListUseCase = setShoppingListUseCase;
        this.getStoresJumboLocalUseCase = getStoresJumboLocalUseCase;
    }

    @Override
    public void initialize(ScannerContract.View view) {
        this.view = view;
        getUserLocal();
        if(!storePreferences.isNearbyStore())
            view.showStoreNotAvailable(true);

    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            getShoppingList();
            this.user = user;
            analytic.sendPageView("Escaneo de productos", user.userProfileId);
        }
    }

    private void getShoppingList(){
        shoppingList = getShoppingListUseCase.getShoppingList();
        if(shoppingList!=null && shoppingList.tags!= null && !shoppingList.tags.isEmpty()){
            view.displayShoppingList(shoppingList);
        }else view.notShoppingList();
    }

    @Override
    public void getProduct(final String ean) {

        Product product = getProductUseCase.setData(ean).getProduct();

        if (product == null) {

            try {
                getProductRemoteUseCase.setData(ean, storePreferences.getStoreID()).execute(new UseCaseObserver<Product>() {
                    @Override
                    public void onNext(Product value) {
                        view.displayProduct(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(e.getMessage().equals("500")){
                            view.showProductNotFount();
                            analytic.sendErrorView("Producto no encontrado: "+ ean, user.userProfileId);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            view.displayProduct(product);
        }

    }

    @Override
    public double totalPrice() {
        List<Product> products = getProductsUseCase.getProducts();
        double priceTotal = 0;
        for (Product item : products) {
                priceTotal = priceTotal + item.amount;
        }
        return priceTotal;
    }

    @Override
    public int totalQuantity() {
        List<Product> products = getProductsUseCase.getProducts();
        int total = 0;
        for (Product item : products) {
            total = total + item.productQuantity;
        }
        return total;
    }

    @Override
    public void analyticSendAction(String label) {
        analytic.sendAction("Scan And Go", "Scanner", user.userProfileId, label);
    }

    @Override
    public void saveTagShopping(int position, TagsShopping tagsShopping) {
        shoppingList.tags.set(position,tagsShopping);
        setShoppingListUseCase.setData(shoppingList).setShoppingList();
    }

    @Override
    public void calculateLocation(Location location) {

        if(getStoresJumboLocalUseCase.getStore()!=null && location!=null){

            List<Store> sortedStoreList = getStoresScanAndGo(getStoresJumboLocalUseCase.getStore(), location);
            if (sortedStoreList.size() > 0){

                if((sortedStoreList.get(0).available && sortedStoreList.get(0).distance <= RADIUS && sortedStoreList.get(0).distance!=0) || true){
                    storePreferences.saveNearbyStore(true);
                 //   storePreferences.saveStoreId(sortedStoreList.get(0).local);
                    storePreferences.saveStoreId("1502");
                   // storePreferences.saveStoreName(sortedStoreList.get(0).name);
                    storePreferences.saveStoreName("Jumbo Kennedy");
                    view.showStoreNotAvailable(false);
                }else{
                    view.showStoreNotAvailable(true);
                    storePreferences.saveNearbyStore(false);
                }

                Log.d("getStores local cercano",sortedStoreList.get(0).name);
            }else view.showStoreNotAvailable(true);
        }else view.showStoreNotAvailable(true);
    }

    private List<Store> getStoresScanAndGo(List<Store> stores, Location location) {

        List<Store> StoresScanAndGo = new ArrayList<>();
        for (Store store : stores) {
            if (store.available) {
                store.distance = MyLocationManager.calculateDistance(new LatLng(location.getLatitude(), location.getLongitude()),new LatLng(Double.parseDouble(store.latitude), Double.parseDouble(store.longitude)));
                StoresScanAndGo.add(store);
            }
        }
        Collections.sort(StoresScanAndGo);
        return StoresScanAndGo;
    }

    @Override
    public void setProduct(Product product) {
        if (setProductUseCase.setData(product).setProduct()) {
            analytic.sendAddToCar(product.ean, product.name, product.brandName, product.amount, product.productQuantity);
            view.onSaveProduct();
        }
    }


}
