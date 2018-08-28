package com.cencosud.scanandgo.wallet.di.module;

import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.wallet.presentation.contract.AddCardContract;
import com.cencosud.scanandgo.wallet.presentation.presenter.AddCardPresenter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 24-05-18.
 */

@Module
public class AddCardModule {

    @Provides
    AddCardContract.Presenter providePresenter(GetUserUseCase getUserUseCase) {
        return new AddCardPresenter(getUserUseCase);
    }
}
