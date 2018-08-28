package com.cencosud.scanandgo.order_history.presentation.fragment;

import android.os.Bundle;
import android.view.View;

import com.cencosud.scanandgo.order_history.di.component.DaggerOrderHistoryFragmentComponent;
import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.cencosud.scanandgo.order_history.presentation.adapter.OrderHistoryAdapter;
import com.cencosud.scanandgo.order_history.presentation.contract.OrderHistoryFragmentContract;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentShoppingHistoryBinding;
import com.cencosud.scanandgo.order_history.utils.DateUtils;
import com.core.presentation.adapter.OnItemClickListener;
import com.core.presentation.fragment.BaseStackFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by joseamaro on 11-07-18.
 */

public class OrderHistoryFragment extends BaseStackFragment<FragmentShoppingHistoryBinding> implements OrderHistoryFragmentContract.View, OnItemClickListener<Transaction> {

    @Inject
    OrderHistoryAdapter adapter;

    @Inject
    OrderDetailsFragment orderDetailsFragment;

    @Inject
    OrderHistoryFragmentContract.Presenter presenter;

    @Override
    protected int getNavigationContainer() {
        return R.id.fragmentContainer;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shopping_history;
    }

    @Override
    protected void initView() {

        presenter.initialize(this);
        binder.progressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorDivider), android.graphics.PorterDuff.Mode.MULTIPLY);
    }

    @Override
    protected void injectDependencies() {

        DaggerOrderHistoryFragmentComponent.builder().build().inject(this);
    }

    @Override
    public void showProgress(boolean show) {

        binder.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void displayOrder(List<Transaction> items) {
        adapter.setList(orderHeader(items));
        adapter.setOnItemClickListener(this);
        binder.rvHistoryList.setAdapter(adapter);
    }

    public List<Transaction> orderHeader(List<Transaction> items){

        String last = null;
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction: items){
            if(last==null){
                last = DateUtils.formaterToString("dd MMMM yyyy", transaction.date);
                transaction.header = true;
            }else {

                if(last.equals(DateUtils.formaterToString("dd MMMM yyyy", transaction.date))){
                    transaction.header = false;
                }else {
                    last = DateUtils.formaterToString("dd MMMM yyyy", transaction.date);
                    transaction.header = true;
                }
            }
            transactions.add(transaction);
        }
        return transactions;
    }

    @Override
    public void onItemClick(int adapterPosition, Transaction item) {
        Bundle b = new Bundle();
        b.putString("transaction", new Gson().toJson(item));
        orderDetailsFragment.setArguments(b);
        addFragmentToStack(orderDetailsFragment);
    }
}
