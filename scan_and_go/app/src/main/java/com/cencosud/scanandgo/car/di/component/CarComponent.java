package com.cencosud.scanandgo.car.di.component;

import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.car.di.module.CarModule;
import com.cencosud.scanandgo.car.presentation.activity.CarActivity;
import com.core.di.component.ActivityComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 08-05-18.
 */

@Singleton
@Component(modules = {CarModule.class, AnalyticModule.class})
public interface CarComponent extends ActivityComponent<CarActivity> {
}
