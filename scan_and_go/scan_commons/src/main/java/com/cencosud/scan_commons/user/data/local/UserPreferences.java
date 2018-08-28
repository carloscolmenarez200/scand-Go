package com.cencosud.scan_commons.user.data.local;

import android.content.Context;

import com.core.data.local.preferences.Preferences;

import javax.inject.Inject;

/**
 * Created by carlos on 23-03-18.
 */

public class UserPreferences extends Preferences {

    enum Key {jwtToken, onBoarding, terms, fingerprint, noDialogFingerprint}

    @Inject
    public UserPreferences(Context context) {
        super(context);
    }

    @Override
    public String getName() {
        return "UserPreferences";
    }

    public String getJwtToken() {
        return getString(Key.jwtToken);
    }

    public void saveJwtToken(String value) {
        save(Key.jwtToken, value);
    }

    public boolean isOnBoarding() {
        return getBool(Key.onBoarding);
    }

    public void saveOnBoarding(boolean value) {
        save(Key.onBoarding, value);
    }

    public boolean isTerms() {
        return getBool(Key.terms);
    }

    public void saveTerms(boolean value) {
        save(Key.terms, value);
    }

    public boolean isFingerprint() {
        return getBool(Key.fingerprint);
    }

    public void saveFingerprint(boolean value) {
        save(Key.fingerprint, value);
    }

    public boolean isNoDialogFingerprint() {
        return getBool(Key.noDialogFingerprint);
    }

    public void saveNoDialogFingerprint(boolean value) {
        save(Key.noDialogFingerprint, value);
    }
}