package com.cencosud.scan_commons.security.domain;

import com.cencosud.scan_commons.security.Decrypt;
import com.cencosud.scan_commons.security.Encrypt;
import com.cencosud.scan_commons.security.data.local.SecurityPreferences;

import javax.inject.Inject;

/**
 * Created by carlos on 14-08-18.
 */

public class DecryptUseCase {

    public String key;

    private Decrypt decrypt;
    private final SecurityPreferences securityPreferences;

    @Inject
    public DecryptUseCase(Decrypt decrypt,SecurityPreferences securityPreferences){
        this.decrypt = decrypt;
        this.securityPreferences = securityPreferences;
    }

    public DecryptUseCase setData(String key){
        this.key = key;
        return this;
    }

    public String decryptData(){

        try {
            return decrypt.decryptData(key, securityPreferences.getEncryptedInfo(key).encryption, securityPreferences.getEncryptedInfo(key).iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
