package com.cencosud.scan_commons.store.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.store.data.repository.StoreJumboLocalRepository;
import com.cencosud.scan_commons.store.data.repository.mapper.StoreJumboLocalToDomainMapper;
import com.cencosud.scan_commons.store.domain.model.Store;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by carlos on 19-07-18.
 */

public class SetStoreJumboLocalUseCase {

    private final StoreJumboLocalToDomainMapper mapper;
    private StoreJumboLocalRepository localRepository;
    private List<Store> data;

    @Inject
    public SetStoreJumboLocalUseCase(@NonNull StoreJumboLocalToDomainMapper mapper, @NonNull StoreJumboLocalRepository localRepository) {
        this.mapper = mapper;
        this.localRepository = localRepository;
    }

    public SetStoreJumboLocalUseCase setData(List<Store> stores){
        this.data = stores;
        return this;
    }

    public boolean saveStores() {
        localRepository.clearAll();
        for (Store store: data){
            localRepository.addOrUpdateStoreJumbo(mapper.reverseMap(store));
        }
        return true;
    }
}
