package com.cencosud.scanandgo.store.utils;

import com.cencosud.scan_commons.store.domain.model.Store;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by carlos on 03-07-18.
 */

public class StoreUtil {

    private LocationHelper locationHelper;

    public StoreUtil(LocationHelper locationHelper){
        this.locationHelper = locationHelper;
        if(this.locationHelper.checkPlayServices()){
            locationHelper.buildGoogleApiClient();
        }
    }

    public List<Store> sortStoreList(List<Store> stores){

        List<Store> sortedStoreList = new ArrayList<>();

        for (Store store : stores) {
            store.distance = locationHelper.calculateDistance(new LatLng(Double.parseDouble(store.latitude), Double.parseDouble(store.longitude)));
            sortedStoreList.add(store);
        }

        Collections.sort(sortedStoreList);

        return sortedStoreList;
    }
}
