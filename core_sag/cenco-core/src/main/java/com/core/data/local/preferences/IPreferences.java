package com.core.data.local.preferences;

/**
 * Created by jhonnybarrios on 10/23/17.
 */

public interface IPreferences {
    String getName();
    void clear();
    void save();
    void save(Enum key, int value);
    void save(Enum key, String value);
    void save(Enum key, boolean value);
    void save(String key, int value);
    void save(String key, String value);
    void save(String key, boolean value);
    int getInt(Enum key);
    String getString(Enum key);
    String getString(String key);
    boolean getBool(Enum key);
}