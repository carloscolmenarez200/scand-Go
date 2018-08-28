package com.cencosud.scanandgo.qr.presentation.activity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;

import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.databinding.ActivityQrBinding;
import com.cencosud.scanandgo.nps.presentation.activity.NpsActivity;
import com.cencosud.scanandgo.qr.di.DaggerQrComponent;
import com.cencosud.scanandgo.qr.presentation.QrContract;
import com.core.presentation.activity.BaseActivity;
import com.google.firebase.analytics.FirebaseAnalytics;

import javax.inject.Inject;

public class QrActivity extends BaseActivity<ActivityQrBinding> implements QrContract.View{

    @Inject
    QrContract.Presenter presenter;

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void initView() {
        presenter.initialize(this);
        presenter.getTotals();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle params = new Bundle();
        params.putString("paginaVista", "screen2");
        params.putString("userID", "model.getCurrentUser().getId()");
        mFirebaseAnalytics.logEvent("paginavistaevento", params);
    }

    @Override
    protected void injectDependencies() {
        DaggerQrComponent.builder().build().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qr;
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void setQrBitmap(Bitmap bitmap) {
        binder.ivQr.setImageBitmap(bitmap);
        presenter.getTransactionStatus();
    }

    @Override
    public void goToNps() {
        startActivity(NpsActivity.class);
        finish();
    }

    @Override
    public void showTotals(String totalPayment, int totalProducts) {
      binder.tvPriceTotal.setText(TextUtils.decimalFormat(Double.parseDouble(totalPayment)));
      binder.tvQuantityProductQr.setText(String.valueOf(totalProducts));
    }
}
