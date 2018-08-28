package com.cencosud.scan_commons.product.domain.usecase;

import android.support.annotation.NonNull;
import com.cencosud.scan_commons.product.data.repository.ProductLocalRepository;
import com.cencosud.scan_commons.product.data.repository.mapper.ProductLocalToDomainMapper;
import com.cencosud.scan_commons.product.domain.model.Product;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by carlos on 09-05-18.
 */

public class SetProductsUseCase {

    private final ProductLocalToDomainMapper mapperCarItem;
    private ProductLocalRepository productRepository;
    private List<Product> data;

    @Inject
    public SetProductsUseCase(@NonNull ProductLocalToDomainMapper mapperCarItem,
                              @NonNull ProductLocalRepository productRepository) {

        this.mapperCarItem = mapperCarItem;
        this.productRepository = productRepository;
    }

    public SetProductsUseCase setData(List<Product> data) {
        this.data = data;
        return this;
    }

    public boolean setProducts() {

        productRepository.clearAll();
        for(Product product: data){
            productRepository.addOrUpdateProduct(mapperCarItem.reverseMap(product));
        }
        return true;
    }

}