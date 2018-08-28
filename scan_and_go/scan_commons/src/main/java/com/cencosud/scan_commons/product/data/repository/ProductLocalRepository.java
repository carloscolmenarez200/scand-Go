package com.cencosud.scan_commons.product.data.repository;

import com.cencosud.scan_commons.product.data.local.ProductLocal;
import java.util.List;
import javax.inject.Inject;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by carlos on 04-05-18.
 */

public class ProductLocalRepository {

    @Inject
    public ProductLocalRepository() {
    }

    public ProductLocal getProduct(String nativeEan) {

        Realm realm = Realm.getDefaultInstance();
        ProductLocal productLocal = realm
                .where(ProductLocal.class)
                .equalTo("nativeEan", nativeEan)
                .findFirst();

        if(productLocal!=null){
           return realm.copyFromRealm(productLocal);
        }
        return null;
    }


    public boolean deleteProduct(final String nativeEan) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bRealm) {
                ProductLocal productLocal = bRealm.where(ProductLocal.class).equalTo("nativeEan", nativeEan).findFirst();
                if(productLocal!=null) {
                    productLocal.deleteCascade();
                }
            }
        });
        return true;
    }

    public boolean addOrUpdateProduct(final ProductLocal productLocal){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bRealm) {
                bRealm.copyToRealmOrUpdate(productLocal);
            }
        });

        return true;
    }

    public List<ProductLocal> getAll(){

        Realm realm = Realm.getDefaultInstance();
        return realm.copyFromRealm(realm.where(ProductLocal.class).findAll());
    }

    public boolean clearAll(){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<ProductLocal> productLocals = bgRealm.where(ProductLocal.class).findAll();
                for (ProductLocal productLocal: productLocals){
                    productLocal.deleteCascade();
                }
            }
        });
        return true;
    }

}
