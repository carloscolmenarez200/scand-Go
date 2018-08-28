package com.cencosud.scan_commons.product.data.repository.mapper;

import com.cencosud.scan_commons.product.data.entity.StoreEntity;
import com.cencosud.scan_commons.product.domain.model.Store;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by carlos on 16-05-18.
 */

public class StoreEntityToDomainMapper extends Mapper<StoreEntity, Store> {


    @Inject
    public StoreEntityToDomainMapper() {
    }


    @Override
    public Store map(StoreEntity value) {
        Store store = new Store();
        store.price = value.price;
        store.lastUpdate = value.lastUpdate;
        store.pum = value.pum;
        store.storeId = value.storeId;
        store.um = value.um;
        return store;
    }

    @Override
    public StoreEntity reverseMap(Store value) {
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.price = value.price;
        storeEntity.lastUpdate = value.lastUpdate;
        storeEntity.pum = value.pum;
        storeEntity.storeId = value.storeId;
        storeEntity.um = value.um;
        return storeEntity;
    }
}
