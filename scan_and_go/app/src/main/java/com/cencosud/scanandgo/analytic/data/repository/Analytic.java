package com.cencosud.scanandgo.analytic.data.repository;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.wallet.domain.model.Card;

import java.util.List;

/**
 * Created by joseamaro on 13-06-18.
 */

public interface Analytic {

    void sendPageView( String viewName, String userId);
    void sendErrorView(String viewName, String userId);
    void sendAction(String category, String action, String userId, String label);
    void sendUseId(String userId);
    void sendAddToCar(String ean, String name, String brandName, double amount, int productQuantity);
    void sendRemoveToCar(String ean, String name, String brandName, double amount, int productQuantity);
    void sendPurchaseToCar(List<ProductDiscount> products, String transactionId, String store, double amount);
    void sendStartCar(List<Product> products);
    void sendPaymentMethod(List<ProductDiscount> products, Card card);
    void sendResumePurchase(List<ProductDiscount> products, Card card);
    void sendChangeCard(Card card);
}
