package com.cencosud.scanandgo.order_history.di.component;

import com.cencosud.scanandgo.order_history.di.module.OrderHistoryFragmentModule;
import com.cencosud.scanandgo.order_history.di.module.OrderHistoryRepositoryModule;
import com.cencosud.scanandgo.order_history.presentation.fragment.OrderHistoryFragment;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 12-07-18.
 */

@Singleton
@Component(modules = {OrderHistoryFragmentModule.class, OrderHistoryRepositoryModule.class})
public interface OrderHistoryFragmentComponent extends FragmentComponent<OrderHistoryFragment> {
}
