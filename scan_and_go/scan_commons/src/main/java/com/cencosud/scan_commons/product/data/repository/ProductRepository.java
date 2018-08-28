package com.cencosud.scan_commons.product.data.repository;

import com.cencosud.scan_commons.product.domain.model.Product;
import io.reactivex.Observable;

/**
 * Created by carlos on 04-05-18.
 */

public interface ProductRepository {
    Observable<Product> getProduct(String ean,String storeId);
}
