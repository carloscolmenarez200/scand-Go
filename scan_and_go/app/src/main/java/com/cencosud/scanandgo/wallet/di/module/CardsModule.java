package com.cencosud.scanandgo.wallet.di.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.wallet.domain.usecase.DeleteCardUseCase;
import com.cencosud.scanandgo.wallet.domain.usecase.GetCardsUseCase;
import com.cencosud.scanandgo.wallet.domain.usecase.SetDefaultCardUseCase;
import com.cencosud.scanandgo.wallet.presentation.adapter.MyPagerAdapter;
import com.cencosud.scanandgo.wallet.presentation.contract.CardsContract;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;
import com.cencosud.scanandgo.wallet.presentation.presenter.CardsPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 18-05-18.
 */

@Module
public class CardsModule {

    FragmentActivity activity;

    public CardsModule(FragmentActivity AppCompatActivity) {
        this.activity = AppCompatActivity;
    }

    @Provides
    Context provideContext() {
        return App.getInstance();
    }

    @Provides
    AddCardFragment provideFragment(){
        return new AddCardFragment();
    }

    @Provides CardsContract.Presenter providesPresenter(GetCardsUseCase getCardsUseCase, DeleteCardUseCase deleteCardUseCase, SetDefaultCardUseCase setDefaultCardUseCase, GetUserUseCase getUserUseCase, Analytic analytic){
        return new CardsPresenter(getCardsUseCase,deleteCardUseCase,setDefaultCardUseCase,getUserUseCase, analytic);
    }

    @Provides
    MyPagerAdapter providesPageAdapter(){return new MyPagerAdapter();}

}
