package com.cencosud.scanandgo.wallet.di.component;

import com.cencosud.scanandgo.wallet.di.module.AddCardModule;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;
import com.core.di.component.FragmentComponent;
import dagger.Component;

/**
 * Created by carlos on 24-05-18.
 */

@Component(modules = {AddCardModule.class})
public interface AddCardComponent extends FragmentComponent<AddCardFragment> {}
