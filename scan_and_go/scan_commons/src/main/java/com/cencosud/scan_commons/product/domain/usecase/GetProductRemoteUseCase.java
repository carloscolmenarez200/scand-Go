package com.cencosud.scan_commons.product.domain.usecase;

import com.cencosud.scan_commons.product.data.repository.ProductRepository;
import com.cencosud.scan_commons.product.domain.model.Product;
import com.core.domain.usecase.UseCase;
import javax.inject.Inject;
import io.reactivex.Observable;

/**
 * Created by carlos on 24-05-18.
 */

public class GetProductRemoteUseCase extends UseCase<Product>{

    private final ProductRepository repository;
    private String ean;
    private String storeId;

    @Inject
    public GetProductRemoteUseCase(ProductRepository repository) {
        this.repository = repository;
    }

    public GetProductRemoteUseCase setData(String ean, String storeId) throws Exception {
        this.ean = ean;
        this.storeId = storeId;
        return this;
    }

    @Override
    protected Observable<Product> createObservableUseCase() {
        return repository.getProduct(ean,storeId);
    }
}
