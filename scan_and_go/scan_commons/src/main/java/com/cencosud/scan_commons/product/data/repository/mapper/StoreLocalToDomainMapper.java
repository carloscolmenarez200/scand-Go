package com.cencosud.scan_commons.product.data.repository.mapper;

import com.cencosud.scan_commons.product.data.local.StoreLocal;
import com.cencosud.scan_commons.product.domain.model.Store;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by carlos on 16-05-18.
 */

public class StoreLocalToDomainMapper extends Mapper<StoreLocal, Store> {


    @Inject
    StoreLocalToDomainMapper() {
    }

    @Override
    public Store map(StoreLocal value) {
        Store store = new Store();
        store.id = value.id;
        store.price = value.price;
        store.lastUpdate = value.lastUpdate;
        store.pum = value.pum;
        store.storeId = value.storeId;
        store.um = value.um;
        return store;
    }

    @Override
    public StoreLocal reverseMap(Store value) {
        StoreLocal storeLocal = new StoreLocal();
        if(value.id==0){
            storeLocal.setId();
        }else storeLocal.id = value.id;
        storeLocal.price = value.price;
        storeLocal.lastUpdate = value.lastUpdate;
        storeLocal.pum = value.pum;
        storeLocal.storeId = value.storeId;
        storeLocal.um = value.um;
        return storeLocal;
    }
}
