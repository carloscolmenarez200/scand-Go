package com.cencosud.scanandgo.order_history.presentation.fragment;

import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.order_history.di.component.DaggerOrderDetailsFragmentComponent;
import com.cencosud.scanandgo.order_history.domain.model.Transaction;
import com.cencosud.scanandgo.order_history.presentation.adapter.OrderDetailsAdapter;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.databinding.FragmentPurchaseDetailsBinding;
import com.cencosud.scanandgo.order_history.presentation.contract.OrderDetailsFragmentContract;
import com.cencosud.scanandgo.order_history.presentation.presenter.OrderHistoryPresenter;
import com.cencosud.scanandgo.order_history.utils.DateUtils;
import com.cencosud.scanandgo.wallet.utils.OnBackPressed;
import com.core.presentation.fragment.BaseFragment;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joseamaro on 11-07-18.
 */

public class OrderDetailsFragment extends BaseFragment<FragmentPurchaseDetailsBinding> implements OnBackPressed, OrderDetailsFragmentContract.View {


    @Inject
    OrderDetailsAdapter adapter;
    private Transaction transaction;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_purchase_details;
    }

    @Override
    protected void initView() {

        String jsonTransaction = getArguments().getString("transaction");
        transaction = new Gson().fromJson(jsonTransaction, Transaction.class);
        adapter.setList(transaction.productDiscounts);
        binder.tvTypeOfCard.setText(transaction.cardId);
        binder.tvDayOfPurchase.setText(DateUtils.formaterToString("dd MMMM yyyy HH:mm",transaction.date));
        binder.recyclerView.setAdapter(adapter);
        binder.tvProductScanned.setText(String.valueOf(totalProducts(transaction.productDiscounts)+ " producto(s) escaneado(s)."));
        if(getActivity() instanceof DrawerMenuActivity){
            DrawerMenuActivity drawerMenuActivity = (DrawerMenuActivity) getActivity();
            drawerMenuActivity.setOnBackPressed(this);
            drawerMenuActivity.setIconToggleBack(transaction.storeName);}

        showTotals();
    }

    @Override
    protected void injectDependencies() {
        DaggerOrderDetailsFragmentComponent.builder().build().inject(this);
    }

    private void showTotals(){

        double subtotal = 0;
        for (ProductDiscount productDiscount : transaction.productDiscounts){
            subtotal += productDiscount.total;
        }

        double discountTotal = subtotal - transaction.totalAmount;
        if(discountTotal<0)
            discountTotal = 0;
        binder.tvPriceSubtotal.setText(TextUtils.decimalFormat(subtotal));
        binder.tvPriceDiscount.setText(TextUtils.decimalFormat(discountTotal));
        binder.tvPriceTotal.setText(TextUtils.decimalFormat(transaction.totalAmount));
    }

    @Override
    public void onBack() {
        if(getActivity() instanceof DrawerMenuActivity){
            DrawerMenuActivity drawerMenuActivity = (DrawerMenuActivity) getActivity();
            drawerMenuActivity.setIconToggleMenu(getString(R.string.menu_shopping_history));
        }
        clearBackStack();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    private int totalProducts(List<ProductDiscount> products) {
        int total = 0;
        for (ProductDiscount item : products) {
            if (item.isPesable) {
                total = total + 1;
            } else total = total + (int) Math.floor(item.quantity);

        }
        return total;
    }

}
