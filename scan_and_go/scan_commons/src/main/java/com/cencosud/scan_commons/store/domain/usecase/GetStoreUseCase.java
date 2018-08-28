package com.cencosud.scan_commons.store.domain.usecase;

import com.cencosud.scan_commons.store.data.repository.StoreRepository;
import com.cencosud.scan_commons.store.domain.model.Store;
import com.core.domain.usecase.UseCase;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by joseamaro on 03-07-18.
 */

public class GetStoreUseCase extends UseCase<List<Store>> {

    private final StoreRepository storeRepository;

    @Inject
    public GetStoreUseCase(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    protected Observable<List<Store>> createObservableUseCase() {
        return storeRepository.getStore();
    }
}
