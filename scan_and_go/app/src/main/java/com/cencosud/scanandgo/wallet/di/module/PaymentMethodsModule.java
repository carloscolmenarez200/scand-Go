package com.cencosud.scanandgo.wallet.di.module;

import android.content.Context;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.wallet.domain.usecase.DeleteCardUseCase;
import com.cencosud.scanandgo.wallet.domain.usecase.GetCardsUseCase;
import com.cencosud.scanandgo.wallet.domain.usecase.SetDefaultCardUseCase;
import com.cencosud.scanandgo.wallet.presentation.contract.PaymentMethodsContract;
import com.cencosud.scanandgo.wallet.presentation.adapter.CardAdapter;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;
import com.cencosud.scanandgo.wallet.presentation.presenter.PaymentMethodsPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 28-03-18.
 */

@Module
public class PaymentMethodsModule {

    @Provides AddCardFragment provideFragment(){
        return new AddCardFragment();
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides PaymentMethodsContract.Presenter providesPresenter(GetCardsUseCase getCardsUseCase, DeleteCardUseCase deleteCardUseCase, SetDefaultCardUseCase setDefaultCardUseCase, GetUserUseCase getUserUseCase, Analytic analytic){
        return new PaymentMethodsPresenter(getCardsUseCase,deleteCardUseCase,setDefaultCardUseCase,getUserUseCase, analytic);
    }

    @Provides CardAdapter providesAdapter(){return new CardAdapter();}

}
