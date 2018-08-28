package com.cencosud.scanandgo.qr.di;

import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.domain.usecase.GetTransactionStatusUseCase;
import com.cencosud.scanandgo.qr.presentation.QrContract;
import com.cencosud.scanandgo.qr.presentation.presenter.QrPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 05-06-18.
 */
@Module
public class QrModule {

    @Provides
    QrContract.Presenter providePresenter(CheckoutPreferences checkoutPreferences, GetTransactionStatusUseCase getTransactionStatusUseCase, GetUserUseCase getUserUseCase, Analytic analytic, StorePreferences storePreferences){
        return new QrPresenter(checkoutPreferences,getTransactionStatusUseCase, getUserUseCase, analytic, storePreferences);
    }
}
