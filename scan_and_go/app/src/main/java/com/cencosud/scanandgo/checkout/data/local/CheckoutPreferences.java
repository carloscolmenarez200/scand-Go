package com.cencosud.scanandgo.checkout.data.local;

import android.content.Context;

import com.core.data.local.preferences.Preferences;

import javax.inject.Inject;

/**
 * Created by joseamaro on 06-06-18.
 */

public class CheckoutPreferences extends Preferences {

    enum Key {transactionId,transactionStatus,totalPayment,cardId,paymentAuthorization, referenceNumber, totalProducts, typeCard}

    @Inject
    public CheckoutPreferences(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "CheckoutPreferences";
    }

    public String getTransactionId() {
        if(getString(Key.transactionId).equals(""))
            return null;
        return getString(Key.transactionId);
    }

    public void saveTransactionId(String value) {
        save(Key.transactionId, value);
    }

    public String getTransactionStatus(){
        if(getString(Key.transactionStatus).equals(""))
            return null;
        return getString(Key.transactionStatus);
    }

    public void saveTransactionStatus(String value){
        save(Key.transactionStatus,value);
    }

    public String getTotalPayment(){
        return getString(Key.totalPayment);
    }

    public void saveTotalPayment(String value){
        save(Key.totalPayment,value);
    }

    public String getCardId(){
        return getString(Key.cardId);
    }

    public void saveCardId(String value){
        save(Key.cardId,value);
    }

    public String getPaymentAuthorization(){
        return getString(Key.paymentAuthorization);
    }

    public void savePaymentAuthorization(String value){
        save(Key.paymentAuthorization,value);
    }

    public String getReferenceNumber(){
        return getString(Key.referenceNumber);
    }

    public void saveReferenceNumber(String value){
        save(Key.referenceNumber,value);
    }

    public int getTotalProducts(){
        return getInt(Key.totalProducts);
    }

    public void saveTotalProducts(int value){
        save(Key.totalProducts,value);
    }

    public String getTypeCard(){
        return getString(Key.typeCard);
    }

    public void saveTypeCard(String value){
        save(Key.typeCard,value);
    }

}
