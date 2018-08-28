package com.cencosud.scanandgo.wallet.presentation.fragment;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentCardsBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.onboarding.presentation.widget.ZoomOutPageTransformer;
import com.cencosud.scanandgo.wallet.di.component.DaggerCardsComponent;
import com.cencosud.scanandgo.wallet.di.module.CardsModule;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.presentation.contract.CardsContract;
import com.cencosud.scanandgo.wallet.presentation.adapter.MyPagerAdapter;
import com.cencosud.scanandgo.wallet.utils.DoneListener;
import com.core.presentation.fragment.BaseStackFragment;
import com.core.util.DialogHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by carlos on 18-05-18.
 */

public class CardsFragment extends BaseStackFragment<FragmentCardsBinding> implements CardsContract.View {


    @Inject
    CardsContract.Presenter presenter;

    @Inject
    AddCardFragment addCardFragment;

    @Inject
    MyPagerAdapter adapter;

    private boolean isSelectionMode;
    private CardFragment.CardSelectionListener cardSelectionListener;

    @Override
    protected void initView() {

        adapter.setActionsListener(new MyPagerAdapter.ActionsListener() {
            @Override
            public void onDeleteCardClicked(Card item) {
                showDeleteCardConfirmationDialog(item);
            }

            @Override
            public void onSetAsDefaultClicked(Card item) {
                presenter.setCardAsDefault(item);
            }
        });

        adapter.setSelectionMode(isSelectionMode);
        adapter.setSelectionListener(new MyPagerAdapter.CardSelectionListener() {
            @Override
            public void onCardSelected(Card item) {
                cardSelectionListener.onCardSelected(item);
                popBackStack();
            }
        });

        binder.pager.setPageTransformer(true, new ZoomOutPageTransformer());
        binder.pager.setAdapter(adapter);
        binder.tabDots.setupWithViewPager(binder.pager, true);

        binder.fabAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateCard();
                presenter.analyticSendAction("Agregar medio de pago");
            }
        });

        binder.tvAddPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateCard();
                presenter.analyticSendAction("Agregar medio de pago");
            }
        });

        presenter.initialize(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cards;
    }

    @Override
    protected int getNavigationContainer() {
        return R.id.fragmentContainer;
    }

    private void showDeleteCardConfirmationDialog(final Card item) {
        new DialogHelper().attachContext(getContext()).showConfirmationDialog(
                R.string.delete_card,
                R.string.delete_card_confirmation,
                R.string.delete,
                R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteCard(item);

                    }
                });
    }


    @Override
    public void setCards(List<Card> value) {
        adapter.setCards(value);
        binder.pager.setCurrentItem(0);
    }

    @Override
    public void openCreateCard() {
        addCardFragment.setDoneListener(new DoneListener() {
            @Override
            public void onDone() {
                if(getActivity() instanceof DrawerMenuActivity){
                    DrawerMenuActivity drawerMenuActivity = (DrawerMenuActivity) getActivity();
                    drawerMenuActivity.setIconToggleMenu(getString(R.string.tv_title_cards));
                }
                presenter.refreshCards();
            }
        });
        addFragmentToStack(addCardFragment);
    }

    @Override
    public void showEmptyState(boolean show) {
        binder.llPaymentMethod.setVisibility(show ? View.VISIBLE : View.GONE);
        binder.tvTextInfo.setVisibility(show ? View.GONE : View.VISIBLE);
        binder.pager.setVisibility(show ? View.GONE : View.VISIBLE);
        binder.fabAddCard.setVisibility(show ? View.GONE : View.VISIBLE);
        binder.tabDots.setVisibility(show ? View.GONE : View.VISIBLE);

    }


    @Override
    public boolean hasNoCards() {
        return false;
    }

    @Override
    public void showError() {
        showProgress(false);
        showMessage(getString(R.string.there_has_been_a_problem));
    }

    @Override
    protected void injectDependencies() {
        DaggerCardsComponent.builder().cardsModule(new CardsModule(getActivity())).build().inject(this);
    }

    @Override
    public void showProgress(boolean show) {
        binder.layoutProgress.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static CardsFragment newInstance(boolean selectionMode) {
        CardsFragment fragment = new CardsFragment();
        fragment.setSelectionMode(selectionMode);
        return fragment;
    }

    private void setSelectionMode(boolean selectionMode) {
        this.isSelectionMode = selectionMode;
    }

    public void setCardSelectionListener(CardFragment.CardSelectionListener cardSelectionListener) {
        this.cardSelectionListener = cardSelectionListener;
    }

}
