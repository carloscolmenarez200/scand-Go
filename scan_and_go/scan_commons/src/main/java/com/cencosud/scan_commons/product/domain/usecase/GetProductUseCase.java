package com.cencosud.scan_commons.product.domain.usecase;

import android.support.annotation.NonNull;

import com.cencosud.scan_commons.product.data.local.ProductLocal;
import com.cencosud.scan_commons.product.data.repository.ProductLocalRepository;
import com.cencosud.scan_commons.product.data.repository.mapper.ProductLocalToDomainMapper;
import com.cencosud.scan_commons.product.domain.model.Product;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by joseamaro on 24-05-18.
 */

public class GetProductUseCase {

    private final ProductLocalToDomainMapper mapper;
    private ProductLocalRepository productRepository;
    private String ean;

    @Inject
    public GetProductUseCase(@NonNull ProductLocalToDomainMapper mapper, @NonNull ProductLocalRepository carRepository) {
        this.mapper = mapper;
        this.productRepository = carRepository;
    }

    public GetProductUseCase setData(String ean) {
        this.ean = ean;
        return this;
    }

    public Product getProduct() {

        ProductLocal productLocal = productRepository.getProduct(ean);
        if(productLocal!=null){
            return mapper.map(productLocal);
        }else return null;
    }
}
