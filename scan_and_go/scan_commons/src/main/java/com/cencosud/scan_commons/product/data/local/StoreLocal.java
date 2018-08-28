package com.cencosud.scan_commons.product.data.local;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by carlos on 16-05-18.
 */

public class StoreLocal extends RealmObject {

    public static AtomicInteger atomicInteger = new AtomicInteger();

    @PrimaryKey
    public long id;
    public String storeId;
    public String price;
    public String pum;
    public String um;
    public String lastUpdate;


    public void setId(){
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() { // must be in transaction for this to work
            @Override
            public void execute(Realm realm) {
                // increment index
                Number currentIdNum = realm.where(StoreLocal.class).max("id");
                int nextId;
                if(currentIdNum == null) {
                    nextId = 1;
                } else {
                    nextId = currentIdNum.intValue() + 1;
                }
                id = nextId;
            }
        });
    }

}
