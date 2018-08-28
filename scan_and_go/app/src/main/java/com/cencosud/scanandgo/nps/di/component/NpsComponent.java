package com.cencosud.scanandgo.nps.di.component;

/**
 * Created by joseamaro on 08-06-18.
 */

import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.nps.di.module.NpsModule;
import com.cencosud.scanandgo.nps.di.module.NpsRepositoryModule;
import com.cencosud.scanandgo.nps.domain.repository.NpsRepository;
import com.cencosud.scanandgo.nps.presentation.activity.NpsActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NpsModule.class, NpsRepositoryModule.class, AnalyticModule.class})
public interface NpsComponent extends ActivityComponent<NpsActivity> {
}
