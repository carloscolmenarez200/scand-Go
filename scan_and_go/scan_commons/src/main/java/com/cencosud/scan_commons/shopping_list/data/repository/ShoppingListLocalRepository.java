package com.cencosud.scan_commons.shopping_list.data.repository;

import com.cencosud.scan_commons.shopping_list.data.local.ShoppingListLocal;
import java.util.List;
import javax.inject.Inject;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by carlos on 13-07-18.
 */

public class ShoppingListLocalRepository {

    @Inject
    public ShoppingListLocalRepository() {}

    public ShoppingListLocal getShoppingList(String title) {

        Realm realm = Realm.getDefaultInstance();
        ShoppingListLocal shoppingListLocal = realm
                .where(ShoppingListLocal.class)
                .equalTo("title", title)
                .findFirst();

        if(shoppingListLocal!=null){
            return realm.copyFromRealm(shoppingListLocal);
        }
        return null;
    }

    public ShoppingListLocal getShoppingList() {

        Realm realm = Realm.getDefaultInstance();
        ShoppingListLocal shoppingListLocal = realm
                .where(ShoppingListLocal.class)
                .findFirst();

        if(shoppingListLocal!=null){
            return realm.copyFromRealm(shoppingListLocal);
        }
        return null;
    }


    public boolean deleteShoppinList(final String title) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bRealm) {
                ShoppingListLocal shoppingListLocal = bRealm.where(ShoppingListLocal.class).equalTo("title", title).findFirst();
                if(shoppingListLocal!=null) {
                    shoppingListLocal.tags.deleteAllFromRealm();
                    shoppingListLocal.deleteFromRealm();
                }
            }
        });
        return true;
    }


    public boolean addOrUpdateProduct(final ShoppingListLocal ShoppingListLocal){
        clearAll();
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bRealm) {
                bRealm.copyToRealmOrUpdate(ShoppingListLocal);
            }
        });

        return true;
    }

    public List<ShoppingListLocal> getAll(){

        Realm realm = Realm.getDefaultInstance();
        return realm.copyFromRealm(realm.where(ShoppingListLocal.class).findAll());
    }

    public boolean clearAll(){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<ShoppingListLocal> shoppingListLocals = bgRealm.where(ShoppingListLocal.class).findAll();
                for (ShoppingListLocal shoppingListLocal: shoppingListLocals){
                    shoppingListLocal.tags.deleteAllFromRealm();
                    shoppingListLocal.deleteFromRealm();
                }
            }
        });
        return true;
    }

}
