package com.cencosud.scanandgo.menu.di;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.product.domain.usecase.DeleteProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.ClearShoppingListUseCase;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.usecase.ClearUserUseCase;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.menu.presentation.DrawerMenuContract;
import com.cencosud.scanandgo.menu.presentation.presenter.DrawerMenuPresenter;
import com.cencosud.scanandgo.order_history.presentation.fragment.OrderHistoryFragment;
import com.cencosud.scanandgo.scanner.presentation.fragment.ScannerFragment;
import com.cencosud.scanandgo.shopping_list.presentation.fragment.ShoppingListFragment;
import com.cencosud.scanandgo.store.presentation.fragment.StoreFragment;
import com.cencosud.scanandgo.terms_and_conditions.domain.usecase.GetContactsUseCase;
import com.cencosud.scanandgo.terms_and_conditions.presentation.fragment.TermsAndConditionsFragment;
import com.cencosud.scanandgo.wallet.presentation.fragment.CardsFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 28-05-18.
 */

@Module
public class DrawerMenuModule {

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides
    DrawerMenuContract.Presenter providePresenter(GetProductsUseCase getProductsUseCase, UserPreferences preferences, ClearUserUseCase clearUserUseCase, GetUserUseCase getUserUseCase, CheckoutPreferences checkoutPreferences, DeleteProductsUseCase deleteProductsUseCase, GetContactsUseCase getContactsUseCase, Analytic analytic, ClearShoppingListUseCase clearShoppingListUseCase){
        return new DrawerMenuPresenter(getProductsUseCase,preferences,clearUserUseCase,getUserUseCase,checkoutPreferences,deleteProductsUseCase,getContactsUseCase, analytic, clearShoppingListUseCase);
    }

    @Provides
    CardsFragment provideFragmentCards(){
        return CardsFragment.newInstance(false);
    }

    @Provides
    ScannerFragment provideFragmentScanner(){
        return ScannerFragment.newInstance();
    }

    @Provides
    TermsAndConditionsFragment provideFragmentTermsConditions(){
        return TermsAndConditionsFragment.newInstance();
    }

    @Provides
    StoreFragment provideFragmentStores(){
        return new StoreFragment();
    }

    @Provides
    OrderHistoryFragment provideFragmentHistory(){
        return new OrderHistoryFragment();
    }

    @Provides
    ShoppingListFragment provideFragmentShoppingList(){
        return new ShoppingListFragment();
    }

}
