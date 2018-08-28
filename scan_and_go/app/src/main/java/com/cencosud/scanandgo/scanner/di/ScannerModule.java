package com.cencosud.scanandgo.scanner.di;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.product.domain.usecase.GetProductRemoteUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.SetProductUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.GetShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.SetShoppingListUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.store.domain.usecase.GetStoresJumboLocalUseCase;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.scanner.presentation.contract.ScannerContract;
import com.cencosud.scanandgo.scanner.presentation.dialog.CodeNotFoundDialog;
import com.cencosud.scanandgo.scanner.presentation.dialog.StoreNotAvailableDialog;
import com.cencosud.scanandgo.scanner.presentation.presenter.ScannerPresenter;
import com.cencosud.scanandgo.shopping_list.adapter.ShoppingListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 24-05-18.
 */

@Module
public class ScannerModule {

    @Provides
    ScannerContract.Presenter providePresenter(GetProductUseCase getProductUseCase, GetProductsUseCase getProductsUseCase, GetProductRemoteUseCase getProductRemoteUseCase, SetProductUseCase setProductUseCase, Analytic analytic, GetUserUseCase getUserUseCase, StorePreferences storePreferences, GetShoppingListUseCase getShoppingListUseCase, SetShoppingListUseCase setShoppingListUseCase,GetStoresJumboLocalUseCase getStoresJumboLocalUseCase) {
        return new ScannerPresenter(getProductUseCase,getProductsUseCase,getProductRemoteUseCase,setProductUseCase,analytic,getUserUseCase,storePreferences,getShoppingListUseCase,setShoppingListUseCase,getStoresJumboLocalUseCase);
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides
    CodeNotFoundDialog provideCodeNotFoundDialog() {
        return new CodeNotFoundDialog();
    }

    @Provides
    StoreNotAvailableDialog provideStoreNotAvailableDialog() {
        return new StoreNotAvailableDialog();
    }

    @Provides
    ShoppingListAdapter provideShoppingListAdapter(){
        return new ShoppingListAdapter();
    }

}
