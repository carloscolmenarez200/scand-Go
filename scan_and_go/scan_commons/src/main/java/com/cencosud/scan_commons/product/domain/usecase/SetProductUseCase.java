package com.cencosud.scan_commons.product.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.product.data.repository.ProductLocalRepository;
import com.cencosud.scan_commons.product.data.repository.mapper.ProductLocalToDomainMapper;
import com.cencosud.scan_commons.product.domain.model.Product;

import javax.inject.Inject;

/**
 * Created by carlos on 25-05-18.
 */

public class SetProductUseCase {

    private final ProductLocalToDomainMapper mapperCarItem;
    private ProductLocalRepository productRepository;
    private Product data;

    @Inject
    public SetProductUseCase(@NonNull ProductLocalToDomainMapper mapperCarItem,
                              @NonNull ProductLocalRepository productRepository) {

        this.mapperCarItem = mapperCarItem;
        this.productRepository = productRepository;
    }

    public SetProductUseCase setData(Product data) {
        this.data = data;
        return this;
    }

    public boolean setProduct() {

        return productRepository.addOrUpdateProduct(mapperCarItem.reverseMap(data));

    }
}
