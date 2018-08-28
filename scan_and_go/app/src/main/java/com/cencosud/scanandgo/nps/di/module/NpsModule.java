package com.cencosud.scanandgo.nps.di.module;

import com.cencosud.scan_commons.product.domain.usecase.DeleteProductsUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.GetShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.SetShoppingListUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.nps.domain.usecase.NpsUseCase;
import com.cencosud.scanandgo.nps.presentation.contract.NpsContract;
import com.cencosud.scanandgo.nps.presentation.presenter.NpsPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 08-06-18.
 */

@Module
public class NpsModule {

    @Provides
    NpsContract.Presenter providePresenter(GetUserUseCase getUserUseCase, NpsUseCase npsUseCase, CheckoutPreferences checkoutPreferences, StorePreferences storePreferences, DeleteProductsUseCase deleteProductsUseCase, SetShoppingListUseCase setShoppingListUseCase, GetShoppingListUseCase getShoppingListUseCase,Analytic analytic) {
        return new NpsPresenter(getUserUseCase, npsUseCase, checkoutPreferences, storePreferences, deleteProductsUseCase, setShoppingListUseCase, getShoppingListUseCase, analytic);
    }
}
