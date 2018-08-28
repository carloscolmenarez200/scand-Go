package com.cencosud.scanandgo.cybersource.decision_manager.domain.usecase;

import android.content.Context;
import android.util.Log;
import com.cencosud.scanandgo.cybersource.ConfigCyberSource;
import com.cencosud.scanandgo.cybersource.decision_manager.CompletionNotifier;
import com.cencosud.scanandgo.cybersource.decision_manager.data.local.DeviceFingerprintIdPreferences;
import com.threatmetrix.TrustDefender.Config;
import com.threatmetrix.TrustDefender.ProfilingOptions;
import com.threatmetrix.TrustDefender.THMStatusCode;
import com.threatmetrix.TrustDefender.TrustDefender;

import javax.inject.Inject;

/**
 * Created by carlos on 18-04-18.
 */

public class DecisionManagerUseCase {

    private final static String TAG = "DecisionManagerUseCase";
    private final Context context;
    private final DeviceFingerprintIdPreferences preferences;

    @Inject
    public DecisionManagerUseCase(Context context, DeviceFingerprintIdPreferences preferences) {
        this.context = context;
        this.preferences = preferences;

    }

    public void generateIdSession() {

        Config config = new Config().setOrgId(ConfigCyberSource.ORG_ID_TEST)
                .setContext(context)
                .setTimeout(10)
                .setRegisterForLocationServices(true)
                .setEnableCallbackOnFailure(true);

        THMStatusCode initStatus = TrustDefender.getInstance().init(config);

        if (initStatus == THMStatusCode.THM_OK || initStatus == THMStatusCode.THM_Already_Initialised) {
            //Init was successful or there is a valid instance to be used for further calls. Fire a p
            Log.e(TAG, "Successfully init-ed " + initStatus.getDesc());
            doProfile();
        }
    }

    private void doProfile() {
        Log.i(TAG, "Using: " + TrustDefender.version);
        // (OPTIONAL) Assign some custom attributes to be included with the profiling information
        //List<String> list = new ArrayList<String>();
        //list.add("attribute 1");
        //list.add("attribute 2");
        CompletionNotifier completionNotifier = new CompletionNotifier();
        completionNotifier.setPreferences(preferences);
        ProfilingOptions options = new ProfilingOptions().setEndNotifier(completionNotifier);
        THMStatusCode status = TrustDefender.getInstance().doProfileRequest(options);

        if (status == THMStatusCode.THM_OK) {
            Log.d(TAG, "Profiling started successfully");
        } else {
            Log.d(TAG, "Profiling failed to start with code of " + status);
        }

    }
}
