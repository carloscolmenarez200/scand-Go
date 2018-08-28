package com.cencosud.scan_commons.store.data.repository;

import com.cencosud.scan_commons.store.domain.model.Store;
import java.util.List;
import io.reactivex.Observable;

/**
 * Created by joseamaro on 03-07-18.
 */

public interface StoreRepository {

    Observable<List<Store>> getStore();
}
