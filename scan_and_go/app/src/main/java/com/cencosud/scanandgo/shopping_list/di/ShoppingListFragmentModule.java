package com.cencosud.scanandgo.shopping_list.di;


import com.cencosud.scan_commons.shopping_list.domain.usecase.ClearShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.GetShoppingListUseCase;
import com.cencosud.scan_commons.shopping_list.domain.usecase.SetShoppingListUseCase;
import com.cencosud.scanandgo.shopping_list.presentation.ShoppingListContract;
import com.cencosud.scanandgo.shopping_list.presentation.presenter.ShoppingListPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 13-07-18.
 */

@Module
public class ShoppingListFragmentModule {

    @Provides
    ShoppingListContract.Presenter providePresenter(SetShoppingListUseCase setShoppingListUseCase, ClearShoppingListUseCase clearShoppingListUseCase, GetShoppingListUseCase getShoppingListUseCase){
        return new ShoppingListPresenter(setShoppingListUseCase, clearShoppingListUseCase, getShoppingListUseCase);
    }
}
