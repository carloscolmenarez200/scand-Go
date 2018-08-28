package com.cencosud.scanandgo.splash.presentation.presenter;

import android.location.Location;

import com.cencosud.scan_commons.security.domain.EncryptUseCase;
import com.cencosud.scan_commons.store.data.local.StorePreferences;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.cencosud.scan_commons.store.domain.usecase.GetStoreUseCase;
import com.cencosud.scan_commons.store.domain.usecase.SetStoreJumboLocalUseCase;
import com.cencosud.scan_commons.user.data.local.UserPreferences;
import com.cencosud.scan_commons.user.domain.model.User;
import com.cencosud.scan_commons.user.domain.usecase.GetUserUseCase;
import com.cencosud.scan_commons.utils.MyLocationManager;
import com.cencosud.scanandgo.checkout.data.local.CheckoutPreferences;
import com.cencosud.scanandgo.checkout.domain.usecase.UpdateTransactionUseCase;
import com.cencosud.scanandgo.splash.presentation.SplashContract;
import com.core.domain.usecase.UseCaseObserver;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by carlos on 26-03-18.
 */

public class SplashPresenter implements SplashContract.Presenter {

    private final GetUserUseCase getUserUseCase;
    private SplashContract.View view;
    private final UserPreferences preferences;
    private final CheckoutPreferences checkoutPreferences;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final GetStoreUseCase getStoreUseCase;
    private final StorePreferences storePreferences;
    private final SetStoreJumboLocalUseCase setStoreJumboLocalUseCase;
    private final EncryptUseCase encryptUseCase;
    private static float RADIUS = 200;

    private User user;

    public SplashPresenter(GetUserUseCase getUserUseCase, UserPreferences preferences, CheckoutPreferences checkoutPreferences, UpdateTransactionUseCase updateTransactionUseCase, GetStoreUseCase getStoreUseCase, StorePreferences storePreferences, SetStoreJumboLocalUseCase setStoreJumboLocalUseCase, EncryptUseCase encryptUseCase) {
        this.getUserUseCase = getUserUseCase;
        this.preferences = preferences;
        this.checkoutPreferences = checkoutPreferences;
        this.updateTransactionUseCase = updateTransactionUseCase;
        this.getStoreUseCase = getStoreUseCase;
        this.storePreferences = storePreferences;
        this.setStoreJumboLocalUseCase = setStoreJumboLocalUseCase;
        this.encryptUseCase = encryptUseCase;
    }

    @Override
    public void initialize(SplashContract.View view) {
        this.view = view;
        encryptKey();
    }


    @Override
    public void getStores(final Location location){

        getStoreUseCase.execute(new UseCaseObserver<List<Store>>() {
            @Override
            public void onNext(List<Store> value) {

                if(location!=null){

                    List<Store> sortedStoreList = getStoresScanAndGo(value, location);
                    if (sortedStoreList.size() > 0){

                        if((sortedStoreList.get(0).available && sortedStoreList.get(0).distance <= RADIUS && sortedStoreList.get(0).distance!=0) || true){
                            storePreferences.saveNearbyStore(true);
                            storePreferences.saveStoreId(sortedStoreList.get(0).local);
                            storePreferences.saveStoreName(sortedStoreList.get(0).name);
                        }else storePreferences.saveNearbyStore(false);
                    }
                }

                setStoreJumboLocalUseCase.setData(value).saveStores();
                view.showProgress(false);
                getUserLocal();
            }

            @Override
            public void onError(Throwable e) {
                view.showDialogError();
                view.showProgress(false);
            }
        });
    }

    private List<Store> getStoresScanAndGo(List<Store> stores, Location location) {

        List<Store> StoresScanAndGo = new ArrayList<>();
        for (Store store : stores) {
            if (store.available) {
                store.distance = MyLocationManager.calculateDistance(new LatLng(location.getLatitude(), location.getLongitude()),new LatLng(Double.parseDouble(store.latitude), Double.parseDouble(store.longitude)));
                StoresScanAndGo.add(store);
            }
        }
        Collections.sort(StoresScanAndGo);
        return StoresScanAndGo;
    }

    private void getUserLocal() {

        user = getUserUseCase.getLoggedUser();

        if (user != null) {

            if (checkoutPreferences.getTransactionId() != null && checkoutPreferences.getTransactionStatus()!=null) {

                if (checkoutPreferences.getTransactionStatus().equals("1")) {
                    //pagado y no avisado ---> update
                    updateTransaction();
                    return;
                }

                if (checkoutPreferences.getTransactionStatus().equals("2")) {
                    //pagado y avisado y no quemado -----> qr
                    view.goToQr();
                    return;
                }

                if (checkoutPreferences.getTransactionStatus().equals("3")) {
                    //quemado ----> nps
                    view.goToNps();
                    return;
                }
            }

            if (preferences.isOnBoarding()) {
                view.goToDashboard();
            } else {
                view.goToOnBoarding();
            }

        } else view.goToLogin();

    }

    private void updateTransaction() {

        String cardId = checkoutPreferences.getCardId();
        String paymentAuthorizationId = checkoutPreferences.getPaymentAuthorization();
        String referenceNumber = checkoutPreferences.getReferenceNumber();
        String transactionID = checkoutPreferences.getTransactionId();
        String email = user.email;

        updateTransactionUseCase.setData(transactionID, cardId, email, paymentAuthorizationId, referenceNumber)
                .execute(new UseCaseObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean value) {
                        view.goToQr();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
    }

    private void encryptKey(){
        encryptUseCase.setData("prueba","mi_clave_nueva").encryptData();
    }


}
