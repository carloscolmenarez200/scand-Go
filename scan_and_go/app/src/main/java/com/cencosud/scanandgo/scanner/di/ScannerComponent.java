package com.cencosud.scanandgo.scanner.di;

import com.cencosud.scan_commons.product.di.ProductRepositoryModule;
import com.cencosud.scanandgo.analytic.di.module.AnalyticModule;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.scanner.presentation.fragment.ScannerFragment;
import com.core.di.component.ActivityComponent;
import com.core.di.component.FragmentComponent;

import dagger.Component;

/**
 * Created by carlos on 24-05-18.
 */

@Component(modules = {ProductRepositoryModule.class,ScannerModule.class, AnalyticModule.class})
public interface ScannerComponent extends FragmentComponent<ScannerFragment> {}
