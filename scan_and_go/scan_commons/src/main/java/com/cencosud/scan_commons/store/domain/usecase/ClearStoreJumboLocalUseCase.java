package com.cencosud.scan_commons.store.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.store.data.repository.StoreJumboLocalRepository;

import javax.inject.Inject;

/**
 * Created by carlos on 19-07-18.
 */

public class ClearStoreJumboLocalUseCase {

    private StoreJumboLocalRepository localRepository;

    @Inject
    public ClearStoreJumboLocalUseCase(@NonNull StoreJumboLocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    public boolean clear() {
        return localRepository.clearAll();
    }
}
