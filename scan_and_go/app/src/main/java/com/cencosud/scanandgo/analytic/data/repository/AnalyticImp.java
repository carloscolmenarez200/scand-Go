package com.cencosud.scanandgo.analytic.data.repository;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseamaro on 13-06-18.
 */

public class AnalyticImp implements Analytic {

    private static final String TAG = "AnalyticImp";

    FirebaseAnalytics mFirebaseAnalytics;

    public AnalyticImp(Context context) {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @Override
    public void sendPageView(String viewName, String userId) {

        try {
            Bundle bundle = new Bundle();
            bundle.putString("paginaVista", viewName);
            bundle.putString("user_id", userId);
            mFirebaseAnalytics.logEvent("paginavistaevento", bundle);

            Log.e("sendPageView", "" + bundle);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void sendErrorView(String viewName, String userId) {

        try {
            Bundle bundle = new Bundle();
            bundle.putString("tipoError", viewName);
            bundle.putString("user_id", userId);
            mFirebaseAnalytics.logEvent("errorevento", bundle);
            Log.e("sendErrorView", "" + bundle);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void sendAction(String category, String action, String userId, String label) {

        try {
            Bundle params = new Bundle();
            params.putString("categoria", category);
            params.putString("accion", action);
            params.putString("etiqueta", label);
            params.putString("user_id", userId);
            mFirebaseAnalytics.logEvent("accionevento", params);
            Log.e("sendAction", "" + params);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void sendUseId(String userId) {

        try {
            Bundle params = new Bundle();
            params.putString("user_id", userId);
            mFirebaseAnalytics.logEvent("setUserID", params);
            Log.e("sendUseId", "" + params);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void sendAddToCar(String ean, String name, String brandName, double amount, int productQuantity) {

        Bundle product1 = new Bundle();
        product1.putString( FirebaseAnalytics.Param.ITEM_ID, ean);
        product1.putString( FirebaseAnalytics.Param.ITEM_NAME, name);
        product1.putDouble( FirebaseAnalytics.Param.PRICE, amount );
        product1.putString( FirebaseAnalytics.Param.ITEM_BRAND, brandName);
        product1.putString( FirebaseAnalytics.Param.CURRENCY, "CLP" );
        product1.putLong( FirebaseAnalytics.Param.QUANTITY, productQuantity );
        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle.putBundle( "items", product1 );
        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.ADD_TO_CART, ecommerceBundle);
    }

    @Override
    public void sendRemoveToCar(String ean, String name, String brandName, double amount, int productQuantity) {
        Bundle product1 = new Bundle();
        product1.putString( FirebaseAnalytics.Param.ITEM_ID, ean);
        product1.putString( FirebaseAnalytics.Param.ITEM_NAME, name);
        product1.putString( FirebaseAnalytics.Param.ITEM_BRAND, brandName);
        product1.putDouble( FirebaseAnalytics.Param.PRICE, amount);
        product1.putString( FirebaseAnalytics.Param.CURRENCY, "CLP" );
        product1.putLong( FirebaseAnalytics.Param.QUANTITY, productQuantity);

        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle.putBundle( "items", product1 );
        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.REMOVE_FROM_CART, ecommerceBundle );
    }

    @Override
    public void sendPurchaseToCar(List<ProductDiscount> products, String transactionId, String store, double amount) {

        ArrayList items = new ArrayList();

        for(ProductDiscount product : products){
            Bundle product1 = new Bundle();
            product1.putString( FirebaseAnalytics.Param.ITEM_ID, product.ean);
            product1.putString( FirebaseAnalytics.Param.ITEM_NAME, product.name);
           // product1.putString( FirebaseAnalytics.Param.ITEM_BRAND, product.brandName);
            product1.putDouble( FirebaseAnalytics.Param.PRICE, amount);
            product1.putString( FirebaseAnalytics.Param.CURRENCY, "CLP");
            product1.putLong( FirebaseAnalytics.Param.QUANTITY, product.quantity);

            items.add(product1);
        }

        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle.putParcelableArrayList( "items", items );
        ecommerceBundle.putString( FirebaseAnalytics.Param.TRANSACTION_ID, transactionId );
        ecommerceBundle.putString( FirebaseAnalytics.Param.AFFILIATION, store );
        ecommerceBundle.putDouble( FirebaseAnalytics.Param.VALUE, amount );
        ecommerceBundle.putDouble( FirebaseAnalytics.Param.SHIPPING, 0 );
        ecommerceBundle.putString( FirebaseAnalytics.Param.CURRENCY, "CLP" );
        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, ecommerceBundle);
    }

    @Override
    public void sendStartCar(List<Product> products) {

        ArrayList items = new ArrayList();

        for(Product product : products) {
            Bundle product1 = new Bundle();
            product1.putString(FirebaseAnalytics.Param.ITEM_ID, product.ean);
            product1.putString(FirebaseAnalytics.Param.ITEM_NAME, product.name);
            product1.putString(FirebaseAnalytics.Param.ITEM_BRAND, product.brandName);
            product1.putDouble(FirebaseAnalytics.Param.PRICE, product.amount);
            product1.putString(FirebaseAnalytics.Param.CURRENCY, "CLP");
            product1.putLong(FirebaseAnalytics.Param.QUANTITY, product.productQuantity);

            items.add(product1);
        }
            Bundle ecommerceBundle = new Bundle();
            ecommerceBundle.putParcelableArrayList( "items", items );

            ecommerceBundle.putLong( FirebaseAnalytics.Param.CHECKOUT_STEP, 1);
            mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.CHECKOUT_PROGRESS, ecommerceBundle );

    }

    @Override
    public void sendPaymentMethod(List<ProductDiscount> products, Card card) {

        ArrayList items = new ArrayList();

        for(ProductDiscount product : products) {
            Bundle product1 = new Bundle();
            product1.putString(FirebaseAnalytics.Param.ITEM_ID, product.ean);
            product1.putString(FirebaseAnalytics.Param.ITEM_NAME, product.name);
           // product1.putString(FirebaseAnalytics.Param.ITEM_BRAND, product.brandName);
            product1.putDouble(FirebaseAnalytics.Param.PRICE, product.totalWithDiscount);
            product1.putString(FirebaseAnalytics.Param.CURRENCY, "CLP");
            product1.putLong(FirebaseAnalytics.Param.QUANTITY, product.quantity);

            items.add(product1);

        }
            Bundle ecommerceBundle = new Bundle();
            ecommerceBundle.putParcelableArrayList( "items", items );

            ecommerceBundle.putLong( FirebaseAnalytics.Param.CHECKOUT_STEP, 2);
            ecommerceBundle.putString( FirebaseAnalytics.Param.CHECKOUT_OPTION, card!=null?card.nameCard.name:"");
            mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.CHECKOUT_PROGRESS, ecommerceBundle);
    }

    @Override
    public void sendResumePurchase(List<ProductDiscount> products, Card card) {

        ArrayList items = new ArrayList();

        for(ProductDiscount product : products) {
            Bundle product1 = new Bundle();
            product1.putString(FirebaseAnalytics.Param.ITEM_ID, product.ean);
            product1.putString(FirebaseAnalytics.Param.ITEM_NAME, product.name);
            //product1.putString(FirebaseAnalytics.Param.ITEM_BRAND, product.brandName);
            product1.putDouble(FirebaseAnalytics.Param.PRICE, product.totalWithDiscount);
            product1.putString(FirebaseAnalytics.Param.CURRENCY, "CLP");
            product1.putLong(FirebaseAnalytics.Param.QUANTITY, product.quantity);

            items.add(product1);

        }
            Bundle ecommerceBundle = new Bundle();
            ecommerceBundle.putParcelableArrayList( "items", items );

            ecommerceBundle.putLong( FirebaseAnalytics.Param.CHECKOUT_STEP, 3);
            ecommerceBundle.putString( FirebaseAnalytics.Param.CHECKOUT_OPTION, card!=null?card.nameCard.name:"");
            mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.CHECKOUT_PROGRESS, ecommerceBundle);

    }

    @Override
    public void sendChangeCard(Card card) {
        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle.putLong( FirebaseAnalytics.Param.CHECKOUT_STEP, 2 );
        ecommerceBundle.putString( FirebaseAnalytics.Param.CHECKOUT_OPTION, card!=null?card.nameCard.name:"");
        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.SET_CHECKOUT_OPTION, ecommerceBundle );
    }
}
