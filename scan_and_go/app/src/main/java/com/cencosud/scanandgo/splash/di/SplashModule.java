package com.cencosud.scanandgo.splash.di;

import com.cencosud.scan_commons.security.domain.EncryptUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.store.domain.usecase.GetStoreUseCase;
import com.cencosud.scan_commons.store.domain.usecase.SetStoreJumboLocalUseCase;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.domain.usecase.UpdateTransactionUseCase;
import com.cencosud.scanandgo.splash.presentation.SplashContract;
import com.cencosud.scanandgo.splash.presentation.presenter.SplashPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 26-03-18.
 */

@Module
public class SplashModule {

    @Provides
    SplashContract.Presenter providePresenter(GetUserUseCase getUserUseCase, UserPreferences preferences, CheckoutPreferences checkoutPreferences,
                                              UpdateTransactionUseCase updateTransactionUseCase, GetStoreUseCase getStoreUseCase, StorePreferences storePreferences,
                                              SetStoreJumboLocalUseCase setStoreJumboLocalUseCase, EncryptUseCase encryptUseCase) {
        return new SplashPresenter(getUserUseCase,preferences,checkoutPreferences, updateTransactionUseCase, getStoreUseCase, storePreferences, setStoreJumboLocalUseCase, encryptUseCase);
    }

}
