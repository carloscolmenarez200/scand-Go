package com.cencosud.scan_commons.security.domain;

import com.cencosud.scan_commons.security.Encrypt;
import com.cencosud.scan_commons.security.data.local.SecurityPreferences;
import javax.inject.Inject;

/**
 * Created by carlos on 14-08-18.
 */

public class EncryptUseCase {

    private String key;
    private String value;

    private Encrypt encrypt;
    private final SecurityPreferences securityPreferences;

    @Inject
    public EncryptUseCase(Encrypt encrypt,SecurityPreferences securityPreferences){
        this.encrypt = encrypt;
        this.securityPreferences = securityPreferences;
    }

    public EncryptUseCase setData(String key, String value){
        this.key = key;
        this.value = value;
        return this;
    }

    public void encryptData(){

        try {
            encrypt.encryptText(key,value);
            securityPreferences.setEncryptedInfo(key,encrypt.getEncryption(),encrypt.getIv());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
