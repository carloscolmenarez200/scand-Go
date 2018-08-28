package com.cencosud.scan_commons.store.data.repository.mapper;


import com.cencosud.scan_commons.store.data.entity.StoreEntity;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.core.data.repository.mapper.Mapper;

import javax.inject.Inject;

/**
 * Created by joseamaro on 29-06-18.
 */

public class StoreEntityToDomainMapper extends Mapper<StoreEntity,Store> {

    @Inject
    public StoreEntityToDomainMapper(){}

    @Override
    public Store map(StoreEntity value) {

        Store store = new Store();
        store.local = value.local;
        store.latitude = value.latitud;
        store.longitude = value.longitud;
        store.name = value.nombre;
        store.address = value.direccion;
        store.phoneNumber = value.telefono;
        store.annexed = value.anexo;
        store.available = Boolean.parseBoolean(value.disponible);

        return store;
    }

    @Override
    public StoreEntity reverseMap(Store value) {

        StoreEntity storeEntity = new StoreEntity();
        storeEntity.local = value.local;
        storeEntity.latitud = value.latitude;
        storeEntity.longitud = value.longitude;
        storeEntity.nombre = value.name;
        storeEntity.direccion = value.address;
        storeEntity.telefono = value.phoneNumber;
        storeEntity.anexo = value.annexed;
        storeEntity.disponible = String.valueOf(value.available);

        return storeEntity;
    }
}
