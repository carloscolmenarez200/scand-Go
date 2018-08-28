package com.cencosud.scan_commons.security.data.local;

import android.content.Context;
import android.util.Base64;
import com.core.data.local.preferences.Preferences;
import javax.inject.Inject;

/**
 * Created by carlos on 26-06-18.
 */

public class SecurityPreferences extends Preferences {

    public class EncryptedInfo {
        public byte[] encryption;
        public byte[] iv;
    }

    @Inject
    public SecurityPreferences(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "SecurityPreferences";
    }


    public void setEncryptedInfo(String key, byte[] encryption, byte[] iv){
        saveEncryption(key,encryption);
        saveIv(key, iv);
    }

    public EncryptedInfo getEncryptedInfo(String key){
        EncryptedInfo  encryptedInfo = new EncryptedInfo();
        encryptedInfo.encryption = getEncryption(key);
        encryptedInfo.iv = getIv(key);
       return encryptedInfo;
    }

    private void saveEncryption(String key, byte[] value) {
        save(key, Base64.encodeToString(value, Base64.DEFAULT));
    }

    private void saveIv(String key, byte[] value) {
        save(key+"_iv", Base64.encodeToString(value, Base64.DEFAULT));
    }

    private byte[] getEncryption(String key) {
        return Base64.decode(getString(key), Base64.DEFAULT);
    }

    private byte[] getIv(String key) {
        return Base64.decode(getString(key+"_iv"), Base64.DEFAULT);
    }
}
