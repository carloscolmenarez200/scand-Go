package com.cencosud.scanandgo.menu.di;

import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.terms_and_conditions.di.module.CmsRepositoryModule;
import com.core.di.component.ActivityComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by carlos on 28-05-18.
 */

@Singleton
@Component(modules = {DrawerMenuModule.class, CmsRepositoryModule.class, AnalyticModule.class})
public interface DrawerMenuComponent extends ActivityComponent<DrawerMenuActivity> {
}
