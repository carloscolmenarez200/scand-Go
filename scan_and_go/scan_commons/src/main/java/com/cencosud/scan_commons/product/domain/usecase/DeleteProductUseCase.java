package com.cencosud.scan_commons.product.domain.usecase;

import com.cencosud.scan_commons.product.data.repository.ProductLocalRepository;
import com.cencosud.scan_commons.product.domain.model.Product;

import javax.inject.Inject;

/**
 * Created by joseamaro on 12-05-18.
 */

public class DeleteProductUseCase {

    private ProductLocalRepository productRepository;
    private Product data;

    @Inject
    DeleteProductUseCase(ProductLocalRepository productRepository) {
        this.productRepository = productRepository;
    }

    public DeleteProductUseCase setData(Product data) {
        this.data = data;
        return this;
    }

    public boolean deleteProduct() {
        return productRepository.deleteProduct(data.ean);
    }
}
