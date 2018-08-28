package com.cencosud.scan_commons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by carlos on 26-03-18.
 */

public class App extends MultiDexApplication {
    @SuppressLint("StaticFieldLeak")
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        Realm.init(this);
        Realm.setDefaultConfiguration(getRealmConfiguration());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Context getInstance() {
        return instance;
    }

    public static RealmConfiguration getRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .name(ConfigScan.DB_NAME + ".realm")
                .schemaVersion(0)
                .encryptionKey(new byte[64])
                .deleteRealmIfMigrationNeeded()
                .build();
    }

}
