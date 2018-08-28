package com.cencosud.scanandgo.wallet.presentation.fragment;

import android.content.DialogInterface;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.Toast;
import com.azoft.carousellayoutmanager.CarouselLayoutManager;
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener;
import com.azoft.carousellayoutmanager.CenterScrollListener;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentPaymentMethodsBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.wallet.di.component.DaggerPaymentMethodsComponent;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.presentation.contract.PaymentMethodsContract;
import com.cencosud.scanandgo.wallet.presentation.adapter.CardAdapter;
import com.cencosud.scanandgo.wallet.utils.DoneListener;
import com.core.presentation.fragment.BaseStackFragment;
import com.core.util.DialogHelper;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by jhonnybarrios on 12/11/17.
 */

public class PaymentMethodsFragment extends BaseStackFragment<FragmentPaymentMethodsBinding> implements PaymentMethodsContract.View {

    @Inject PaymentMethodsContract.Presenter presenter;
    @Inject CardAdapter adapter;
    @Inject AddCardFragment addCardFragment;
    private boolean isSelectionMode;
    private CardAdapter.CardSelectionListener cardSelectionListener;

    @Override protected int getLayoutId() {return R.layout.fragment_payment_methods;}

    @Override protected int getNavigationContainer() {return R.id.fragmentContainer;}

    @Override protected void initView() {

        binder.fabAddCard.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                openCreateCard();
            }
        });
        adapter.setActionsListener(new CardAdapter.ActionsListener() {
            @Override public void onDeleteCardClicked(Card item) {
                showDeleteCardConfirmationDialog(item);
            }

            @Override
            public void onSetAsDefaultClicked(Card item) {
                presenter.setCardAsDefault(item);
            }
        });
        presenter.initialize(this);
    }

    public void setCardSelectionListener(CardAdapter.CardSelectionListener cardSelectionListener) {
        this.cardSelectionListener = cardSelectionListener;
    }

    private void showDeleteCardConfirmationDialog(final Card item) {
        new DialogHelper().attachContext(getContext()).showConfirmationDialog(
                        R.string.delete_card,
                        R.string.delete_card_confirmation,
                        R.string.delete,
                        R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            @Override public void onClick(DialogInterface dialog, int which) {
                                presenter.deleteCard(item);
                            }
                        });
    }

    @Override protected void injectDependencies() {
        DaggerPaymentMethodsComponent.builder().build().inject(this);
    }

    @Override public void showProgress(boolean show) {
       binder.layoutProgress.setVisibility(show? View.VISIBLE:View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override public void setCards(List<Card> value) {
        adapter.setSelectionMode(isSelectionMode);
        adapter.setSelectionListener(new CardAdapter.CardSelectionListener() {
            @Override public void onCardSelected(Card item) {
                cardSelectionListener.onCardSelected(item);
                popBackStack();
            }
        });

        List<Card> cardList = new ArrayList<>();// Todo: la ultima posicion es la por defecto por ende la pasamos a la primera posicion
        cardList.add(value.remove(value.size()-1));
        cardList.addAll(value);
        adapter.setList(cardList);

        final CarouselLayoutManager layoutManager = new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL,false);
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        binder.rvCards.setLayoutManager(layoutManager);
        binder.rvCards.setHasFixedSize(true);
        adapter.setLayoutManager(layoutManager);
        binder.rvCards.setAdapter(adapter);
        ((SimpleItemAnimator) binder.rvCards.getItemAnimator()).setSupportsChangeAnimations(false);
        binder.rvCards.addOnScrollListener(new CenterScrollListener());
    }


    @Override public void openCreateCard() {
        addCardFragment.setDoneListener(new DoneListener() {
            @Override public void onDone() {
                presenter.refreshCards();
            }
        });
        addFragmentToStack(addCardFragment);
    }

    @Override public void showEmptyState(boolean show) {
        binder.layoutEmpty.base.setVisibility(show?View.VISIBLE:View.GONE);
    }

    @Override public void removeCard(Card item) {
        adapter.remove(item);
    }

    @Override public boolean hasNoCards() {
        return adapter.getItemCount()==0;
    }

    @Override
    public void showError() {
        showProgress(false);
        showMessage(getString(R.string.there_has_been_a_problem));
    }

    public static PaymentMethodsFragment newInstance(boolean selectionMode) {
        PaymentMethodsFragment fragment = new PaymentMethodsFragment();
        fragment.setSelectionMode(selectionMode);
        return fragment;
    }
    private void setSelectionMode(boolean selectionMode) {
        this.isSelectionMode=selectionMode;
    }

}