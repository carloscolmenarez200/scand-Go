package com.cencosud.scan_commons.utils;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by carlos on 23-03-18.
 */

public abstract class DBRepository<T extends RealmObject>{

    protected abstract Class<T> getItemClass();

    protected String getIdPropertyName(){return "id";}

    public Observable<Boolean> save(final List<T> items) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.insertOrUpdate(items);
                realm.commitTransaction();
                e.onNext(true);
                e.onComplete();
                realm.close();
            }
        });
    }

    public Observable<Boolean> save(final T item) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Boolean> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.insertOrUpdate(item);
                realm.commitTransaction();
                e.onNext(true);
                e.onComplete();
                realm.close();
            }
        });
    }

    public Observable<List<T>> getAll() {
        return Observable.create(new ObservableOnSubscribe<RealmResults<T>>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<RealmResults<T>> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                e.onNext(realm.where(getItemClass())
                        .findAll());

                e.onComplete();
                realm.close();
            }
        }).map(realmResultsToListMapper);
    }

    public Observable<T> get(final long id) {
        return Observable.create(new ObservableOnSubscribe<RealmObject>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<RealmObject> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                e.onNext(realm.where(getItemClass())
                        .equalTo(getIdPropertyName(), id)
                        .findFirst());

                e.onComplete();
                realm.close();
            }
        }).map(realmObjectToTMapper);
    }

    public Observable<Boolean> delete(final String propertyName,final long id) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.where(getItemClass()).equalTo(propertyName, id).findFirst().deleteFromRealm();
                realm.commitTransaction();
                e.onNext(true);
                e.onComplete();
                realm.close();
            }
        });
    }

    public Observable<Boolean> clear() {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override public void subscribe(final ObservableEmitter<Boolean> e) throws Exception {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                boolean result = realm.where(getItemClass()).findAll().deleteAllFromRealm();
                realm.commitTransaction();
                e.onNext(result);
                e.onComplete();
                realm.close();
            }
        });
    }

    private Function<RealmResults<T>,  List<T>> realmResultsToListMapper=new Function<RealmResults<T>, List<T>>() {
        @Override
        public List<T> apply(RealmResults<T> ts) throws Exception {

            return Realm.getDefaultInstance().copyFromRealm(ts);
        }
    };

    private Function<RealmObject, T> realmObjectToTMapper=new Function<RealmObject, T>() {
        @Override
        public T apply(RealmObject ts) throws Exception {
            return (T) ts;
        }
    };
}