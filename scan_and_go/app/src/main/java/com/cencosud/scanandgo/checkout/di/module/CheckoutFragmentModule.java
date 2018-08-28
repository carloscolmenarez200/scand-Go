package com.cencosud.scanandgo.checkout.di.module;


import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.domain.usecase.GetPromotionUseCase;
import com.cencosud.scanandgo.checkout.domain.usecase.UpdateTransactionUseCase;
import com.cencosud.scanandgo.checkout.presentation.adapter.CardPageAdapter;
import com.cencosud.scanandgo.checkout.presentation.adapter.CheckoutAdapter;
import com.cencosud.scanandgo.checkout.presentation.contract.CheckoutContract;
import com.cencosud.scanandgo.checkout.presentation.dialog.CheckoutSuccessDialog;
import com.cencosud.scanandgo.checkout.presentation.dialog.FirstCheckoutDialog;
import com.cencosud.scanandgo.checkout.presentation.presenter.CheckoutPresenter;
import com.cencosud.scanandgo.cybersource.anti_fraud.AntiFraudUseCase;
import com.cencosud.scanandgo.cybersource.decision_manager.data.local.DeviceFingerprintIdPreferences;
import com.cencosud.scanandgo.cybersource.payment.PaymentUseCase;
import com.cencosud.scanandgo.wallet.domain.usecase.GetCardsUseCase;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 15-05-18.
 */

@Module
public class CheckoutFragmentModule {

    FragmentActivity activity;

    public CheckoutFragmentModule(FragmentActivity activity){
        this.activity=activity;
    }

    @Provides
    CheckoutAdapter provideAdapter() {
        return new CheckoutAdapter();
    }

    @Provides
    FirstCheckoutDialog provideDialogFirst() {

        return new FirstCheckoutDialog();
    }

    @Provides
    CheckoutSuccessDialog provideDialogSuccess() {

        return new CheckoutSuccessDialog();
    }

    @Provides
    AddCardFragment provideFragment(){
        return new AddCardFragment();
    }

    @Provides
    CheckoutContract.Presenter providePresenter(GetProductsUseCase getProductsUseCase, GetCardsUseCase getCardsUseCase, GetUserUseCase getUserUseCase, GetPromotionUseCase getPromotionUseCase, CheckoutPreferences checkoutPreferences, AntiFraudUseCase antiFraudUseCase, PaymentUseCase paymentUseCase, DeviceFingerprintIdPreferences deviceFingerprintIdPreferences, UpdateTransactionUseCase updateTransactionUseCase, StorePreferences storePreferences, Analytic analytic) {
        return new CheckoutPresenter(getProductsUseCase,getCardsUseCase,getUserUseCase, getPromotionUseCase, checkoutPreferences, antiFraudUseCase, paymentUseCase, deviceFingerprintIdPreferences, updateTransactionUseCase, storePreferences, analytic);
    }

    @Provides
    CardPageAdapter providesPageAdapter(){return new CardPageAdapter(activity.getSupportFragmentManager());}
}
