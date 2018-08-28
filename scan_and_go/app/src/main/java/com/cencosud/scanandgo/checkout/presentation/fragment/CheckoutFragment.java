package com.cencosud.scanandgo.checkout.presentation.fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.car.presentation.activity.CarActivity;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.databinding.FragmentCheckoutBinding;
import com.cencosud.scanandgo.qr.presentation.activity.QrActivity;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.checkout.di.component.DaggerCheckoutFragmentComponent;
import com.cencosud.scanandgo.checkout.di.module.CheckoutFragmentModule;
import com.cencosud.scanandgo.checkout.presentation.adapter.CardPageAdapter;
import com.cencosud.scanandgo.checkout.presentation.adapter.CheckoutAdapter;
import com.cencosud.scanandgo.checkout.presentation.contract.CheckoutContract;
import com.cencosud.scanandgo.checkout.presentation.dialog.CheckoutSuccessDialog;
import com.cencosud.scanandgo.checkout.presentation.dialog.FirstCheckoutDialog;
import com.cencosud.scanandgo.cybersource.SoapResponse;
import com.cencosud.scanandgo.databinding.ActivityCheckoutBinding;
import com.cencosud.scanandgo.onboarding.presentation.widget.ZoomOutPageTransformer;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.presentation.fragment.AddCardFragment;
import com.cencosud.scanandgo.wallet.utils.DoneListener;
import com.core.presentation.fragment.BaseStackFragment;
import com.core.util.DialogHelper;

import java.util.List;
import javax.inject.Inject;

public class CheckoutFragment extends BaseStackFragment<FragmentCheckoutBinding> implements CheckoutContract.View, FirstCheckoutDialog.NoticeDialogListener, CheckoutSuccessDialog.NoticeDialogListener, SoapResponse, ViewPager.OnPageChangeListener {

    @Inject
    CheckoutAdapter adapter;

    @Inject
    CheckoutContract.Presenter presenter;

    @Inject
    CardPageAdapter cardPageAdapter;

    @Inject
    FirstCheckoutDialog dialogFirst;

    @Inject
    CheckoutSuccessDialog dialogSuccess;

    @Inject
    AddCardFragment addCardFragment;

    private int positionCard = -1;

    private ProgressDialog progressDialog;
    protected AlertDialog dialog;

    @Override
    protected void initView() {

        dialogFirst.setmListener(this);
        dialogSuccess.setmListener(this);
        progressDialog = new ProgressDialog(getContext());
        dialogSuccess.setCancelable(false);

        getActivity().setTitle("");
        binder.progressBar.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);
        presenter.initialize(this);
        presenter.setListenerCyberSource(this);
        binder.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binder.btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(positionCard!=-1){
                    if(cardPageAdapter.cards.get(positionCard).counter!=0)
                            presenter.antiFraudCyberSource(cardPageAdapter.cards.get(positionCard));
                    else dialogFirst.show(getFragmentManager(), "NoticeDialogFragment");
                }
            }
        });

        binder.tvAddPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateCard();
            }
        });

    }

    @Override
    protected void injectDependencies() {
        DaggerCheckoutFragmentComponent.builder().checkoutFragmentModule(new CheckoutFragmentModule(getActivity())).build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_checkout;
    }

    @Override
    public void showProgress(boolean show) {
        binder.progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(CONTEXT,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void displayProducts(List<ProductDiscount> items) {
        adapter.setList(items);
        binder.recyclerView.setAdapter(adapter);
    }

    @Override
    public void setCards(List<Card> value) {
        binder.pager.setPageTransformer(true, new ZoomOutPageTransformer());
        binder.pager.addOnPageChangeListener(this);
        binder.pager.setAdapter(cardPageAdapter);
        binder.tabDots.setupWithViewPager(binder.pager, true);
        cardPageAdapter.setCards(value);
        positionCard = 0;
    }

    public void openCreateCard() {
        addCardFragment.setDoneListener(new DoneListener() {
            @Override
            public void onDone() {
                if(((AppCompatActivity)getActivity()).getSupportActionBar()!=null){
                    ((AppCompatActivity)getActivity()).getSupportActionBar().show();
                }
                presenter.refreshCards();
            }
        });
        addFragmentToStack(addCardFragment);
    }

    @Override
    public void showEmptyState(boolean show) {
        binder.llCheckout.setVisibility(show ? View.VISIBLE : View.GONE);
        binder.tabDots.setVisibility(show ? View.GONE : View.VISIBLE);
        binder.btnCheckout.setEnabled(!show);
    }

    @Override
    public void showTotals(double subTotal, double totalPayment, int totalProducts) {
        binder.tvPriceSubtotal.setText(TextUtils.decimalFormat(subTotal));
        binder.tvPriceTotal.setText(TextUtils.decimalFormat(totalPayment));
        binder.tvProductScanned.setText("Tienes " + String.valueOf(totalProducts) + " producto(s) escaneado(s).");

        if((subTotal-totalPayment) == 0)
            binder.tvPriceDiscount.setText(TextUtils.decimalFormat(subTotal - totalPayment));
        else
            binder.tvPriceDiscount.setText(TextUtils.decimalFormatNegative(subTotal - totalPayment));

    }

    @Override
    public void showDialogSuccess() {
        dialogSuccess.show(getFragmentManager(), "NoticeDialogFragment");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onSuccessCheckoutDialogPositiveClick();
            }
        }, 3000);
    }

    @Override
    public void showProgressDialog(boolean show, String title) {
        progressDialog.setMessage(title);
        progressDialog.setCancelable(false);
        if(show)
        progressDialog.show();
        else progressDialog.dismiss();
    }

    @Override
    public void showErrorPromotion() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
         dialog = builder.setCancelable(false)
                .setTitle("Error")
                .setMessage("Ha ocurrido un problema al calcular promociones.")
                .setPositiveButton(R.string.text_retry_promotion, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       presenter.refreshCards();
                    }
                })
                 .setNegativeButton(R.string.text_cancel_promotion, new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         getActivity().finish();
                     }
                 }).create();
        dialog.show();
    }

    @Override
    public void onSuccess(String tag,String paymentAuthorizationId, String referenceNumber) {

        if (tag.equals("AntiFraud")) {
            presenter.paymentCyberSource(cardPageAdapter.cards.get(positionCard),referenceNumber);
        } else if (tag.equals("Payment")) {
            presenter.updateTransaction(cardPageAdapter.cards.get(positionCard),paymentAuthorizationId,referenceNumber);
        }
    }

    @Override
    public void onError(String tag) {
        progressDialog.dismiss();
        new DialogHelper().attachContext(getContext()).showMessageDialog("Ha ocurrido un problema con el pago intenta nuevamente");
        presenter.sendActionError();
    }

    @Override
    public void onFirstDialogPositiveClick(String cvv) {
        if(cvv.isEmpty()){
            showMessage("El campo cvv no debe estar vacio");
        }else{
            dialogFirst.dismiss();
            Card card = cardPageAdapter.cards.get(positionCard);
            card.cvv = cvv;
            presenter.antiFraudCyberSource(card);
        }
    }

    @Override
    public void onSuccessCheckoutDialogPositiveClick() {

        Intent intent = new Intent(getContext(), QrActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finishActivity();
    }

    @Override
    protected int getNavigationContainer() {
        return R.id.fragmentContainer;
    }

    public static CheckoutFragment newInstance(){
        CheckoutFragment instance = new CheckoutFragment();
        return instance;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        positionCard = position;
        presenter.updateTenderCode(cardPageAdapter.cards.get(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
