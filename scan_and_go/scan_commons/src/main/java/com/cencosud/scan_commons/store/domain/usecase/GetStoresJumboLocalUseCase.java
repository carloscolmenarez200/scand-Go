package com.cencosud.scan_commons.store.domain.usecase;

import android.support.annotation.NonNull;
import com.cencosud.scan_commons.store.data.local.StoreJumboLocal;
import com.cencosud.scan_commons.store.data.repository.StoreJumboLocalRepository;
import com.cencosud.scan_commons.store.data.repository.mapper.StoreJumboLocalToDomainMapper;
import com.cencosud.scan_commons.store.domain.model.Store;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by carlos on 31-07-18.
 */

public class GetStoresJumboLocalUseCase {

    private final StoreJumboLocalToDomainMapper mapper;
    private StoreJumboLocalRepository localRepository;

    @Inject
    public GetStoresJumboLocalUseCase(@NonNull StoreJumboLocalToDomainMapper mapper, @NonNull StoreJumboLocalRepository localRepository) {
        this.mapper = mapper;
        this.localRepository = localRepository;
    }

    public List<Store> getStore() {
        List<StoreJumboLocal> storeJumboLocals = localRepository.getAll();
        if(storeJumboLocals!=null){
            return mapper.map(storeJumboLocals);
        }
        return null;
    }
}
