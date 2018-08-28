package com.cencosud.scan_commons.store.data.repository;

import com.cencosud.scan_commons.store.data.local.StoreJumboLocal;

import java.util.List;
import javax.inject.Inject;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by carlos on 19-07-18.
 */

public class StoreJumboLocalRepository {

    @Inject
    public StoreJumboLocalRepository() {}

    public StoreJumboLocal getStoreJumbo(String storeId) {

        Realm realm = Realm.getDefaultInstance();
        StoreJumboLocal storeJumboLocal = realm
                .where(StoreJumboLocal.class)
                .equalTo("local", storeId)
                .findFirst();

        if(storeJumboLocal !=null){
            return realm.copyFromRealm(storeJumboLocal);
        }
        return null;
    }


    public boolean deleteStoreJumbo(final String storeId) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bRealm) {
                StoreJumboLocal storeJumboLocal = bRealm.where(StoreJumboLocal.class).equalTo("local", storeId).findFirst();
                if(storeJumboLocal !=null) {
                    storeJumboLocal.deleteFromRealm();
                }
            }
        });
        return true;
    }


    public boolean addOrUpdateStoreJumbo(final StoreJumboLocal storeJumboLocal){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bRealm) {
                bRealm.copyToRealmOrUpdate(storeJumboLocal);
            }
        });

        return true;
    }

    public List<StoreJumboLocal> getAll(){

        Realm realm = Realm.getDefaultInstance();
        return realm.copyFromRealm(realm.where(StoreJumboLocal.class).findAll());
    }

    public boolean clearAll(){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<StoreJumboLocal> storeJumboLocals = bgRealm.where(StoreJumboLocal.class).findAll();
                for (StoreJumboLocal storeJumboLocal : storeJumboLocals){
                    storeJumboLocal.deleteFromRealm();
                }
            }
        });
        return true;
    }
}
