package com.cencosud.scanandgo.order_history.di.module;

import com.cencosud.scanandgo.order_history.presentation.adapter.OrderDetailsAdapter;
import dagger.Module;
import dagger.Provides;

/**
 * Created by joseamaro on 12-07-18.
 */

@Module
public class OrderDetailsFragmentModule {

    @Provides
    OrderDetailsAdapter provideAdapter() {
        return new OrderDetailsAdapter();
    }
}
