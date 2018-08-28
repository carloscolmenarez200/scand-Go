package com.cencosud.scanandgo.order_history.di.component;

import com.cencosud.scanandgo.order_history.di.module.OrderDetailsFragmentModule;
import com.cencosud.scanandgo.order_history.presentation.fragment.OrderDetailsFragment;
import com.core.di.component.FragmentComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by joseamaro on 12-07-18.
 */

@Singleton
@Component(modules = {OrderDetailsFragmentModule.class})
public interface OrderDetailsFragmentComponent extends FragmentComponent<OrderDetailsFragment>{
}
