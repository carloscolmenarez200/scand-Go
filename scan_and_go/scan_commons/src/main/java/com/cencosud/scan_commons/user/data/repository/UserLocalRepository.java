package com.cencosud.scan_commons.user.data.repository;

import com.cencosud.scan_commons.user.data.local.UserLocal;
import javax.inject.Inject;
import io.realm.Realm;

/**
 * Created by carlos on 23-03-18.
 */

public class UserLocalRepository  {

    @Inject
    public UserLocalRepository() {
    }

    public UserLocal getLoggedUser() {

        Realm realm = Realm.getDefaultInstance();
        UserLocal userLocal = realm
                .where(UserLocal.class)
                .findFirst();

        if(userLocal!=null){
            return realm.copyFromRealm(userLocal);
        }
        return null;
    }

    public boolean addOrUpdateUser(final UserLocal user){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bRealm) {
                bRealm.copyToRealmOrUpdate(user);
            }
        });

        return true;
    }

    public boolean clearAll(){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
               bgRealm.where(UserLocal.class).findAll().deleteAllFromRealm();
            }
        });
        return true;
    }

}
