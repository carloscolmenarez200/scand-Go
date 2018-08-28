package com.cencosud.scanandgo.shopping_list.di;

import com.cencosud.scanandgo.shopping_list.presentation.fragment.ShoppingListFragment;
import com.core.di.component.FragmentComponent;
import javax.inject.Singleton;
import dagger.Component;

/**
 * Created by carlos on 13-07-18.
 */

@Singleton
@Component(modules = {ShoppingListFragmentModule.class})
public interface ShoppingListFragmentComponent  extends FragmentComponent<ShoppingListFragment> {}