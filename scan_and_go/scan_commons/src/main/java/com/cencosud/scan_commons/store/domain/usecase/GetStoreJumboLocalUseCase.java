package com.cencosud.scan_commons.store.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.product.data.local.StoreLocal;
import com.cencosud.scan_commons.store.data.local.StoreJumboLocal;
import com.cencosud.scan_commons.store.data.repository.StoreJumboLocalRepository;
import com.cencosud.scan_commons.store.data.repository.mapper.StoreJumboLocalToDomainMapper;
import com.cencosud.scan_commons.store.domain.model.Store;
import javax.inject.Inject;

/**
 * Created by carlos on 19-07-18.
 */

public class GetStoreJumboLocalUseCase {

    private final StoreJumboLocalToDomainMapper mapper;
    private StoreJumboLocalRepository localRepository;
    private String local;

    @Inject
    public GetStoreJumboLocalUseCase(@NonNull StoreJumboLocalToDomainMapper mapper, @NonNull StoreJumboLocalRepository localRepository) {
        this.mapper = mapper;
        this.localRepository = localRepository;
    }

    public GetStoreJumboLocalUseCase setData(String local){
        this.local = local;
        return this;
    }

    public Store getStore() {
        StoreJumboLocal storeJumboLocal = localRepository.getStoreJumbo(local);
        if(storeJumboLocal!=null){
            return mapper.map(localRepository.getStoreJumbo(local));
        }
        return null;
    }
}
