package com.cencosud.scanandgo.wallet.di.component;

import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.wallet.di.module.CardsModule;
import com.cencosud.scanandgo.wallet.di.module.RepositoryModule;
import com.cencosud.scanandgo.wallet.presentation.fragment.CardsFragment;
import com.core.di.component.FragmentComponent;
import dagger.Component;

/**
 * Created by carlos on 18-05-18.
 */

@Component(modules = {RepositoryModule.class, CardsModule.class, AnalyticModule.class})
public interface CardsComponent extends FragmentComponent<CardsFragment> {}

