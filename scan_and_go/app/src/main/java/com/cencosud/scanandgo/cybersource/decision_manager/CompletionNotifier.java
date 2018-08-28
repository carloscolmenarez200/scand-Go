package com.cencosud.scanandgo.cybersource.decision_manager;

import android.util.Log;

import com.cencosud.scanandgo.cybersource.decision_manager.data.local.DeviceFingerprintIdPreferences;
import com.threatmetrix.TrustDefender.EndNotifier;
import com.threatmetrix.TrustDefender.ProfilingResult;
import com.threatmetrix.TrustDefender.TrustDefender;

/**
 * Created by carlos on 05-04-18.
 */

public class CompletionNotifier implements EndNotifier {

   private DeviceFingerprintIdPreferences preferences;

    @Override
    public void complete(ProfilingResult result) {

        String m_sessionID = result.getSessionID();
        preferences.saveDevicePrintId(m_sessionID);
        Log.i("<Scan&GO>Completion", "Profile completed with: " + result.getStatus().toString()
                + " - " + result.getStatus().getDesc());
        TrustDefender.getInstance().doPackageScan(0);

    }

    public void  setPreferences(DeviceFingerprintIdPreferences preferences){
        this.preferences = preferences;
    }
}
