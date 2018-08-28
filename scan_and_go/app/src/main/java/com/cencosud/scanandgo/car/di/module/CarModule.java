package com.cencosud.scanandgo.car.di.module;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.product.domain.usecase.DeleteProductUseCase;
import com.cencosud.scan_commons.product.domain.usecase.DeleteProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.product.domain.usecase.SetProductsUseCase;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.car.presentation.adapter.CarAdapter;
import com.cencosud.scanandgo.car.presentation.contract.CarContract;
import com.cencosud.scanandgo.car.presentation.presenter.CarPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 08-05-18.
 */

@Module
public class CarModule {

    @Provides
    CarAdapter provideAdapter() {
        return new CarAdapter();
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides
    CarContract.Presenter providePresenter(GetProductsUseCase getProductsUseCase, DeleteProductUseCase deleteProductUseCase,
                                           DeleteProductsUseCase deleteProductsUseCase, SetProductsUseCase setProductsUseCase, GetUserUseCase getUserUseCase, Analytic analytic) {
        return new CarPresenter(getProductsUseCase, deleteProductUseCase, deleteProductsUseCase, setProductsUseCase, getUserUseCase, analytic);
    }
}
