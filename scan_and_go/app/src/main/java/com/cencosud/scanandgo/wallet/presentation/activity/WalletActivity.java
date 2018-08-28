package com.cencosud.scanandgo.wallet.presentation.activity;

import android.widget.Toast;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.ActivityWalletBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.wallet.di.component.DaggerWalletComponent;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.presentation.adapter.CardAdapter;
import com.cencosud.scanandgo.wallet.presentation.fragment.CardFragment;
import com.cencosud.scanandgo.wallet.presentation.fragment.CardsFragment;
import com.cencosud.scanandgo.wallet.presentation.fragment.PaymentMethodsFragment;
import com.core.presentation.activity.BaseFragmentActivity;
import javax.inject.Inject;

public class WalletActivity extends BaseFragmentActivity<ActivityWalletBinding> implements CardFragment.CardSelectionListener {

    @Inject
    PaymentMethodsFragment fragment;

    @Inject
    CardsFragment cardsFragment;

    @Override protected int getLayoutId() {return R.layout.activity_wallet;}

    @Override protected void injectDependencies() {
        DaggerWalletComponent.builder().build().inject(this);
    }

    @Override protected void initView() {
        cardsFragment.setCardSelectionListener(this);
        setFragment(cardsFragment);
    }

    @Override protected int getFragmentContainerId() {return R.id.fragmentContainer;}

    @Override public void onCardSelected(Card item) {
        Toast.makeText(this,"Tarjeta "+item.cardNumber+" seleccionada",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        startActivity(DrawerMenuActivity.class);
        finish();
    }
}
