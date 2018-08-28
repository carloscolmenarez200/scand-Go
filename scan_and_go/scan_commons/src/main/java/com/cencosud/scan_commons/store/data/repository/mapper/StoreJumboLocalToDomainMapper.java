package com.cencosud.scan_commons.store.data.repository.mapper;

import com.cencosud.scan_commons.store.data.local.StoreJumboLocal;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.core.data.repository.mapper.Mapper;
import javax.inject.Inject;

/**
 * Created by carlos on 19-07-18.
 */

public class StoreJumboLocalToDomainMapper extends Mapper<StoreJumboLocal,Store> {

    @Inject
    public StoreJumboLocalToDomainMapper(){}

    @Override
    public Store map(StoreJumboLocal value) {

        Store store = new Store();
        store.local = value.local;
        store.latitude = value.latitud;
        store.longitude = value.longitud;
        store.name = value.nombre;
        store.address = value.direccion;
        store.phoneNumber = value.telefono;
        store.annexed = value.anexo;
        store.available = value.disponible;

        return store;
    }

    @Override
    public StoreJumboLocal reverseMap(Store value) {

        StoreJumboLocal storeJumboLocal = new StoreJumboLocal();
        storeJumboLocal.local = value.local;
        storeJumboLocal.latitud = value.latitude;
        storeJumboLocal.longitud = value.longitude;
        storeJumboLocal.nombre = value.name;
        storeJumboLocal.direccion = value.address;
        storeJumboLocal.telefono = value.phoneNumber;
        storeJumboLocal.anexo = value.annexed;
        storeJumboLocal.disponible = value.available;

        return storeJumboLocal;
    }
}