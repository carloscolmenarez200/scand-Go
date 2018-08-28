package com.cencosud.scanandgo.fingerprint.domain.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.cencosud.scanandgo.fingerprint.presentation.OnFingerprintListener;

/**
 * Created by joseamaro on 14-08-18.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;
    private OnFingerprintListener onFingerprintListener;

    // Constructor
    public FingerprintHandler(Context mContext, OnFingerprintListener onFingerprintListener) {
        context = mContext;
        this.onFingerprintListener = onFingerprintListener;
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Error de Autenticación de huellas dactilares\n" + errString, false);
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Ayuda de Autenticación de huellas dactilares\n" + helpString, false);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fallo al autenticar con la huella dactilar.", false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Éxito al autenticar con la huella dactilar.", true);
    }

    public void update(String e, Boolean success) {

        //Toast.makeText(context, e, Toast.LENGTH_LONG).show();
        if (success) {
            onFingerprintListener.onSuccessFingerprint();
        }else onFingerprintListener.onErrorFingerprint();
    }
}
