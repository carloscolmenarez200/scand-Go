package com.cencosud.scanandgo.cybersource.decision_manager.data.local;

import android.content.Context;
import com.core.data.local.preferences.Preferences;
import javax.inject.Inject;

/**
 * Created by carlos on 07-06-18.
 */

public class DeviceFingerprintIdPreferences extends Preferences {

    enum Key {devicePrintId}

    @Inject
    public DeviceFingerprintIdPreferences(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "DeviceFingerprintIdPreferences";
    }

    public String getDevicePrintId() {
        return getString(Key.devicePrintId);
    }

    public void saveDevicePrintId(String value) {
        save(Key.devicePrintId, value);
    }

}
