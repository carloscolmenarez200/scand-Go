package com.cencosud.scanandgo.checkout.presentation.presenter;

import com.cencosud.scan_commons.product.domain.model.Product;
import com.cencosud.scan_commons.product.domain.usecase.GetProductsUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scanandgo.analytic.data.repository.Analytic;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.domain.model.CheckoutResume;
import com.cencosud.scanandgo.checkout.domain.model.Discount;
import com.cencosud.scanandgo.checkout.domain.model.ProductDiscount;
import com.cencosud.scanandgo.checkout.domain.usecase.GetPromotionUseCase;
import com.cencosud.scanandgo.checkout.domain.usecase.UpdateTransactionUseCase;
import com.cencosud.scanandgo.checkout.presentation.contract.CheckoutContract;
import com.cencosud.scanandgo.checkout.utils.FormatUtils;
import com.cencosud.scanandgo.cybersource.SoapResponse;
import com.cencosud.scanandgo.cybersource.anti_fraud.AntiFraudUseCase;
import com.cencosud.scanandgo.cybersource.decision_manager.data.local.DeviceFingerprintIdPreferences;
import com.cencosud.scanandgo.cybersource.payment.PaymentUseCase;
import com.cencosud.scanandgo.wallet.domain.model.Card;
import com.cencosud.scanandgo.wallet.domain.usecase.GetCardsUseCase;
import com.core.domain.usecase.UseCaseObserver;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseamaro on 16-05-18.
 */

public class CheckoutPresenter implements CheckoutContract.Presenter {

    private final GetProductsUseCase getProductsUseCase;
    private final GetCardsUseCase getCardsUseCase;
    private final GetUserUseCase getUserUseCase;
    private final GetPromotionUseCase getPromotionUseCase;
    private final CheckoutPreferences checkoutPreferences;
    private final AntiFraudUseCase antiFraudUseCase;
    private final PaymentUseCase paymentUseCase;
    private final DeviceFingerprintIdPreferences deviceFingerprintIdPreferences;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final StorePreferences storePreferences;
    private final Analytic analytic;

    private CheckoutContract.View view;

    private User user;
    private String tenderCode;
    private Card card;
    private CheckoutResume checkoutResume;
    private String countCards;


    public CheckoutPresenter(GetProductsUseCase getProductsUseCase, GetCardsUseCase getCardsUseCase, GetUserUseCase getUserUseCase, GetPromotionUseCase getPromotionUseCase, CheckoutPreferences checkoutPreferences, AntiFraudUseCase antiFraudUseCase, PaymentUseCase paymentUseCase, DeviceFingerprintIdPreferences deviceFingerprintIdPreferences, UpdateTransactionUseCase updateTransactionUseCase, StorePreferences storePreferences, Analytic analytic) {
        this.getProductsUseCase = getProductsUseCase;
        this.getCardsUseCase = getCardsUseCase;
        this.getUserUseCase = getUserUseCase;
        this.getPromotionUseCase = getPromotionUseCase;
        this.checkoutPreferences = checkoutPreferences;
        this.antiFraudUseCase = antiFraudUseCase;
        this.paymentUseCase = paymentUseCase;
        this.deviceFingerprintIdPreferences = deviceFingerprintIdPreferences;
        this.updateTransactionUseCase = updateTransactionUseCase;
        this.storePreferences = storePreferences;
        this.analytic = analytic;
        this.checkoutResume = null;
    }

    @Override
    public void initialize(CheckoutContract.View view) {
        this.view = view;
        getUserLocal();
    }

    private void getUserLocal() {

        User user = getUserUseCase.getLoggedUser();
        if (user != null) {
            this.user = user;
            refreshCards();
            analytic.sendPageView("Pago", user.userProfileId);
        }
    }

    private void getPromotions(List<Card> cards, List<Product> products) {

        getPromotionUseCase.setData(FormatUtils.FormatRUT(user.document), user.email,
                checkoutPreferences.getTransactionId(), storePreferences.getStoreID(),
                priceTotal(products), products, cards).execute(new UseCaseObserver<CheckoutResume>() {
            @Override
            public void onNext(CheckoutResume value) {
                view.showProgressDialog(false, "");
                checkoutResume = value;
                checkoutPreferences.saveTransactionId(value.transactionId);
                List<ProductDiscount> productList = getProductsFindByTenderCode();
                view.displayProducts(productList);
                analytic.sendPaymentMethod(productList,card);
            }

            @Override
            public void onError(Throwable e) {
                view.showProgressDialog(false, "");
                view.showErrorPromotion();
                super.onError(e);
            }
        });

    }

    private List<ProductDiscount> getProductsFindByTenderCode() {

        if (checkoutResume.orchestrator.discounts != null) {
            for (Discount discount : checkoutResume.orchestrator.discounts) {
                if (discount.tenderCode.equals(tenderCode)) {

                    int totalProducts = totalProducts(discount.products);
                    checkoutPreferences.saveTotalPayment(String.valueOf(discount.totalAmount));
                    checkoutPreferences.saveTotalProducts(totalProducts);

                    view.showTotals(checkoutResume.orchestrator.subTotal, discount.totalAmount, totalProducts); //show totals
                    return discount.products;
                }
            }
        }
        return new ArrayList<>();
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


    private double priceTotal(List<Product> products) {

        double priceTotal = 0;
        for (Product item : products) {
            priceTotal = priceTotal + item.amount;
        }
        return priceTotal;
    }

    @Override
    public void refreshCards() {
        view.showProgress(true);
        view.showProgressDialog(true, "Calculando promociones...");
        view.showEmptyState(false);
        getCardsUseCase.setData(user.email).execute(new UseCaseObserver<List<Card>>() {
            @Override
            public void onNext(List<Card> value) {
                view.showProgress(false);
                if (value.isEmpty()) {
                    view.showEmptyState(true);
                    tenderCode = "";
                    countCards = "0";
                } else {
                    tenderCode = value.get(0).tenderCode;
                    countCards = String.valueOf(value.size());
                    card =  value.get(0);
                    view.setCards(value);
                }
                getPromotions(value, getProductsUseCase.getProducts());
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                view.showProgress(false);
                if (e.getMessage().equals("500")) {
                    tenderCode = "";
                    view.showEmptyState(true);
                    getPromotions(new ArrayList<Card>(), getProductsUseCase.getProducts());
                } else view.showProgressDialog(false, "");
                //view.showError();
            }
        });
    }

    @Override
    public void updateTenderCode(Card card) {
        if (!card.tenderCode.equals(tenderCode)) {
            tenderCode = card.tenderCode;
            view.displayProducts(getProductsFindByTenderCode());
            analytic.sendPaymentMethod(getProductsFindByTenderCode(), card);
        }else analytic.sendChangeCard(card);
    }

    @Override
    public void setListenerCyberSource(SoapResponse soapResponse) {
        antiFraudUseCase.setSoapResponse(soapResponse);
        paymentUseCase.setSoapResponse(soapResponse);
    }

    @Override
    public void antiFraudCyberSource(Card card) {
        view.showProgressDialog(true, "Procesando pago...");
        antiFraudUseCase.setData(user, card, getProductsFindByTenderCode(), deviceFingerprintIdPreferences.getDevicePrintId(),storePreferences.getStoreID(),storePreferences.getStoreName(),countCards).call();

    }

    @Override
    public void paymentCyberSource(Card card, String referenceNumber) {
        paymentUseCase.setData(user, card, getProductsFindByTenderCode(), deviceFingerprintIdPreferences.getDevicePrintId(), referenceNumber).call();
    }

    @Override
    public void updateTransaction(final Card card, String paymentAuthorizationId, String referenceNumber) {

        checkoutPreferences.saveTransactionStatus("1");
        checkoutPreferences.savePaymentAuthorization(paymentAuthorizationId);
        checkoutPreferences.saveReferenceNumber(referenceNumber);
        checkoutPreferences.saveCardId(card.cardId);
        checkoutPreferences.saveTypeCard(card.nameCard.name);

        updateTransactionUseCase.setData(checkoutPreferences.getTransactionId(), card.cardId,
                user.email, paymentAuthorizationId, referenceNumber)
                .execute(new UseCaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean value) {
                        view.showProgressDialog(false, "");
                        view.showDialogSuccess();
                        analytic.sendPurchaseToCar(getProductsFindByTenderCode(), checkoutPreferences.getTransactionId(), "Kennedy", Double.parseDouble(checkoutPreferences.getTotalPayment()));
                        analytic.sendResumePurchase(getProductsFindByTenderCode(), card);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showProgressDialog(false, "");
                        super.onError(e);
                    }
                });
    }

    @Override
    public void sendActionError() {
        analytic.sendErrorView("No se pudo realizar la compra", user.userProfileId);
    }
}
