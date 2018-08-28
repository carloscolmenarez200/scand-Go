package com.cencosud.scan_commons.product.domain.usecase;

import android.support.annotation.NonNull;
import com.cencosud.scan_commons.product.data.repository.ProductLocalRepository;
import com.cencosud.scan_commons.product.data.repository.mapper.ProductLocalToDomainMapper;
import com.cencosud.scan_commons.product.domain.model.Product;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by joseamaro on 12-05-18.
 */

public class GetProductsUseCase {

    private final ProductLocalToDomainMapper mapper;
    private ProductLocalRepository productRepository;

    @Inject
    public GetProductsUseCase(@NonNull ProductLocalToDomainMapper mapper, @NonNull ProductLocalRepository carRepository) {
        this.mapper = mapper;
        this.productRepository = carRepository;
    }

    public List<Product> getProducts() {
        return mapper.map(productRepository.getAll());
    }
}
