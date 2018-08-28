package com.cencosud.scan_commons.store.data.repository;

import com.cencosud.scan_commons.BuildConfig;
import com.cencosud.scan_commons.store.data.entity.StoreEntity;
import com.cencosud.scan_commons.store.data.remote.StoreApi;
import com.cencosud.scan_commons.store.data.repository.mapper.StoreEntityToDomainMapper;
import com.cencosud.scan_commons.store.domain.model.Store;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by joseamaro on 03-07-18.
 */

public class StoreRepositoryImp implements StoreRepository {

    private final StoreApi api;
    private final StoreEntityToDomainMapper storeMapper;

    public StoreRepositoryImp(StoreApi api, StoreEntityToDomainMapper storeMapper){
        this.api = api;
        this.storeMapper = storeMapper;
    }

    @Override
    public Observable<List<Store>> getStore() {

        return api.getStore(BuildConfig.ApiKey).map(new Function<List<StoreEntity>, List<Store>>() {
            @Override
            public List<Store> apply(List<StoreEntity> storeEntities) throws Exception {

                return storeMapper.map(storeEntities);
            }
        });
    }
}
