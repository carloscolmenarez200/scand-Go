package com.cencosud.scanandgo.analytic.di.module;

import android.content.Context;

import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.analytic.data.repository.AnalyticImp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by carlos on 20-06-18.
 */

@Module
public class AnalyticModule {

    @Provides
    Analytic provideAnalytic(Context context){
       return new AnalyticImp(context);
    }
}
