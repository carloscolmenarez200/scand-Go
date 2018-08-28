package com.cencosud.scanandgo.qr.presentation.presenter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Handler;
import com.cencosud.scan_commons.shopping_list.domain.usecase.GetShoppingListUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.domain.usecase.GetTransactionStatusUseCase;
import com.cencosud.scanandgo.qr.presentation.QrContract;
import com.core.domain.usecase.UseCaseObserver;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by carlos on 05-06-18.
 */

public class QrPresenter implements QrContract.Presenter {

    private final CheckoutPreferences checkoutPreferences;
    private final StorePreferences storePreferences;
    private final GetTransactionStatusUseCase getTransactionStatusUseCase;
    private final GetUserUseCase getUserUseCase;
    private final Analytic analytic;
    private QrContract.View view;

    private User user;

    public QrPresenter(CheckoutPreferences checkoutPreferences, GetTransactionStatusUseCase getTransactionStatusUseCase, GetUserUseCase getUserUseCase, Analytic analytic, StorePreferences storePreferences) {
        this.getTransactionStatusUseCase = getTransactionStatusUseCase;
        this.getUserUseCase = getUserUseCase;
        this.checkoutPreferences = checkoutPreferences;
        this.storePreferences = storePreferences;
        this.analytic = analytic;
    }

    @Override
    public void initialize(QrContract.View view) {
        this.view = view;
        String contentQr = storePreferences.getStoreID()+";"+checkoutPreferences.getTransactionId();
        generateQr(contentQr);
        getUserLocal();
    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            this.user = user;
            analytic.sendPageView("Codigo Qr", user.userProfileId);
        }
    }
    private void generateQr(String contents){

       BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = barcodeEncoder.encodeBitmap(contents, BarcodeFormat.QR_CODE, 400, 400);
            view.setQrBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }


    private Bitmap tintImage(Bitmap bitmap) {
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN));
        Bitmap bitmapResult = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapResult);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return bitmapResult;
    }


    @Override
    public void getTransactionStatus() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getTransactionStatusUseCase.setData(checkoutPreferences.getTransactionId()).execute(new UseCaseObserver<String>() {
                    @Override
                    public void onNext(String value) {
                        if(checkoutPreferences.getTransactionStatus() !=null && !checkoutPreferences.getTransactionStatus().equals("3") && value.equals("3")){
                            checkoutPreferences.saveTransactionStatus(value);
                            view.goToNps();
                        }else{
                            getTransactionStatus();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        getTransactionStatus();
                        super.onError(e);
                        analytic.sendErrorView("Error al realizar la transaccion", user.userProfileId);
                    }
                });

            }
        }, 2000);
    }

    @Override
    public void getTotals() {
        view.showTotals(checkoutPreferences.getTotalPayment(),checkoutPreferences.getTotalProducts());
    }


}
