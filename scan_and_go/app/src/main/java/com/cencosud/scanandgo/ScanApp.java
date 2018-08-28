package com.cencosud.scanandgo;

import com.cencosud.scan_commons.App;
import com.cencosud.scan_commons.ConfigScan;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by carlos on 26-03-18.
 */

public class ScanApp extends App {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }
}