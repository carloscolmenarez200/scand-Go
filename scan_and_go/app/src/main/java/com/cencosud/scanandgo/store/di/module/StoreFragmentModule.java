package com.cencosud.scanandgo.store.di.module;

import com.cencosud.scan_commons.store.domain.usecase.GetStoreJumboLocalUseCase;
import com.cencosud.scan_commons.store.domain.usecase.GetStoreUseCase;
import com.cencosud.scanandgo.store.presentation.adapter.StoreListAdapter;
import com.cencosud.scanandgo.store.presentation.contract.StoreContract;
import com.cencosud.scanandgo.store.presentation.presenter.StorePresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 27-06-18.
 */

@Module
public class StoreFragmentModule {

    @Provides
    StoreListAdapter provideAdapter(){
        return new StoreListAdapter();
    }

    @Provides
    StoreContract.Presenter providePresenter(GetStoreUseCase getStoreUseCase){
        return new StorePresenter(getStoreUseCase);
    }
}
