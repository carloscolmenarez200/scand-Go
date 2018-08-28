package com.cencosud.scan_commons.product.domain.usecase;
import com.cencosud.scan_commons.product.data.repository.ProductLocalRepository;
import javax.inject.Inject;

/**
 * Created by carlos on 17-05-18.
 */

public class DeleteProductsUseCase {

    private ProductLocalRepository productRepository;

    @Inject
    DeleteProductsUseCase(ProductLocalRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean deleteProducts() {
        return productRepository.clearAll();
    }
}
