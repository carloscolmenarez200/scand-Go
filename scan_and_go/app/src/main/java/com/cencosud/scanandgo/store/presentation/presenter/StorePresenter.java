package com.cencosud.scanandgo.store.presentation.presenter;


import android.location.Location;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.cencosud.scan_commons.store.domain.usecase.GetStoreUseCase;
import com.cencosud.scan_commons.utils.MyLocationManager;
import com.cencosud.scanandgo.store.presentation.contract.StoreContract;
import com.core.domain.usecase.UseCaseObserver;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseamaro on 03-07-18.
 */

public class StorePresenter implements StoreContract.Presenter {

    private StoreContract.View view;
    private final GetStoreUseCase getStoreUseCase;

    public StorePresenter(GetStoreUseCase getStoreUseCase) {
        this.getStoreUseCase = getStoreUseCase;
    }

    @Override
    public void initialize(StoreContract.View view) {
        this.view = view;
    }

    @Override
    public void getStore(final Location location) {
        view.showProgress(true);
        getStoreUseCase.execute(new UseCaseObserver<List<Store>>() {
            @Override
            public void onNext(List<Store> value) {

                List<Store> StoresScanAndGo = getStoresScanAndGo(value,location);
                view.displayStores(StoresScanAndGo);
                view.showProgress(false);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
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
        return StoresScanAndGo;
    }
}
