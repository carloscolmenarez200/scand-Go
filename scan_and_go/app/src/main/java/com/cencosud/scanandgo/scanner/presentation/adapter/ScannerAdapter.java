package com.cencosud.scanandgo.scanner.presentation.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.R;
import com.cencosud.scanandgo.car.presentation.contract.CarContract;
import com.cencosud.scanandgo.car.utils.TextUtils;
import com.cencosud.scanandgo.databinding.FragmentScannerBinding;
import com.cencosud.scanandgo.menu.presentation.activity.DrawerMenuActivity;
import com.cencosud.scanandgo.scanner.presentation.contract.ScannerContract;
import com.core.util.ViewUtils;
import com.scandit.barcodepicker.BarcodePicker;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by joseamaro on 27-05-18.
 */

public class ScannerAdapter {

    Product product;
    FragmentScannerBinding binder;
    ScannerContract.Presenter presenter;
    private Date lastUpdate;
    private long timeProduct = 8000;
    public String code = "";
    private BarcodePicker picker;

    public void initialize(FragmentScannerBinding binder, ScannerContract.Presenter presenter, BarcodePicker picker) {
        this.binder = binder;
        this.presenter = presenter;
        this.picker = picker;
    }

    public void displayProduct(Product product_) {
        this.product = product_;

        if (this.product.stores != null && this.product.stores.size() > 0) {

            if(presenter.totalQuantity()==30){
                showToolTip();
                return;
            }

            Glide.with(binder.getRoot().getContext())
                    .load(product.imageUrl)
                    .centerCrop()
                    .error(R.drawable.noimage)
                    .placeholder(R.drawable.noimage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(binder.ivItemImage);

            lastUpdate = new Date();


            if (this.product.isPesable) {
                if (this.product.productQuantity == 0) {
                    this.product.productQuantity = this.product.productQuantity + 1;
                }
                binder.btnAdd.setEnabled(false);
                binder.btnRemove.setEnabled(false);
                binder.tvTextPrice.setText(TextUtils.decimalFormat(product.amount));

            } else {
                this.product.productQuantity = product.productQuantity + 1;
                product.quantity = product.productQuantity;

                if (!product.stores.isEmpty())
                    product.amount = Double.parseDouble(product.stores.get(product.stores.keySet().toArray()[0]).price) * product.productQuantity;

                binder.tvTextPrice.setText(TextUtils.decimalFormat(Double.parseDouble(product.stores.get(product.stores.keySet().toArray()[0]).price)));
                binder.btnAdd.setEnabled(!(presenter.totalQuantity()+1== 30));
                binder.btnRemove.setEnabled(!(product.productQuantity == 1));
            }

            updateProduct();
            updatePriceTotal();

            binder.tvItemName.setText(product.fullName);
            binder.tvTextQuantityProduct.setText(String.valueOf(product.productQuantity));
            binder.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    product.productQuantity = product.productQuantity + 1;
                    product.quantity = product.productQuantity;

                    if (!product.stores.isEmpty())
                        product.amount = Double.parseDouble(product.stores.get(product.stores.keySet().toArray()[0]).price) * product.productQuantity;


                    binder.tvTextQuantityProduct.setText(String.valueOf(product.productQuantity));
                    if (product.productQuantity == 30 || presenter.totalQuantity()==29) {
                        binder.btnAdd.setEnabled(false);

                    } else {
                        binder.btnRemove.setEnabled(true);
                    }

                    lastUpdate = new Date();
                    updateProduct();
                    updatePriceTotal();

                }
            });

            binder.btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    product.productQuantity = product.productQuantity - 1;
                    product.quantity = product.productQuantity;

                    if (!product.stores.isEmpty())
                        product.amount = Double.parseDouble(product.stores.get(product.stores.keySet().toArray()[0]).price) * product.productQuantity;

                    binder.tvTextQuantityProduct.setText(String.valueOf(product.productQuantity));

                    if (product.productQuantity == 1) {
                        binder.btnRemove.setEnabled(false);

                    } else {
                        binder.btnAdd.setEnabled(true);
                    }

                    lastUpdate = new Date();
                    updateProduct();
                    updatePriceTotal();
                }
            });

            binder.cvCar.setVisibility(View.VISIBLE);
        }

    }

    private void updatePriceTotal() {
        binder.tvPriceTotal.setText(TextUtils.decimalFormat(presenter.totalPrice()));

    }


    private void updateProduct() {
        presenter.setProduct(product);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (evaluateLimit(lastUpdate, new Date())) {
                    binder.cvCar.setVisibility(View.GONE);
                    code = "";
                    //picker.startScanning();
                }
            }
        }, timeProduct);
    }

    private boolean evaluateLimit(Date lastUpdate, Date actual) {

        long timeInitial = lastUpdate.getTime();
        long timeFinal = actual.getTime();
        long resta = timeFinal - timeInitial;
        return (resta >= timeProduct);
    }

    private void animateView(View foodCardView, Bitmap b) {

        binder.mDummyImgView.setImageBitmap(b);
        binder.mDummyImgView.setVisibility(View.VISIBLE);
        binder.mDummyImgView.setLeft(foodCardView.getLeft());
        binder.mDummyImgView.setTop(foodCardView.getTop());
        AnimatorSet animSetXY = new AnimatorSet();
        ObjectAnimator y = ObjectAnimator.ofFloat(binder.mDummyImgView, "translationY", 250, -1000);
        ObjectAnimator x = ObjectAnimator.ofFloat(binder.mDummyImgView, "translationX", 0, 600);
        ObjectAnimator sy = ObjectAnimator.ofFloat(binder.mDummyImgView, "scaleY", 0.8f, 0.05f);
        ObjectAnimator sx = ObjectAnimator.ofFloat(binder.mDummyImgView, "scaleX", 0.8f, 0.05f);
        animSetXY.playTogether(x, y, sx, sy);
        animSetXY.setDuration(650);
        animSetXY.start();
    }

    public void showToolTip(){
        binder.rlTooltip.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                binder.rlTooltip.setVisibility(View.GONE);
            }
        }, 4000);
    }
}


