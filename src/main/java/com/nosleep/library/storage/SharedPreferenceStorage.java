package com.nosleep.library.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nosleep.library.storage.preferences.AppPreferenceStorage;

/**
 * Created by Sanjeev on 13/09/16.
 */
class SharedPreferenceStorage implements AppPreferenceStorage {
    SharedPreferences preferences;

    SharedPreferenceStorage(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override

    public void storeIntegerValue(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    @Override
    public void storeStringValue(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    @Override
    public void storeFloatValue(String key, float value) {
        preferences.edit().putFloat(key, value).apply();
    }

    @Override
    public void storeLongValue(String key, long value) {
        preferences.edit().putLong(key, value).apply();
    }

    @Override
    public void storeBoolValue(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public int retrieveIntegerValue(String key) {
        return preferences.getInt(key, 0);
    }

    @Override
    public String retrieveStringValue(String key) {
        return preferences.getString(key, "");
    }

    @Override
    public float retrieveFloatValue(String key) {
        return preferences.getFloat(key, 0);
    }

    @Override
    public long retrieveLongValue(String key) {
        return preferences.getLong(key, 0);
    }

    @Override
    public boolean retrieveBooleanValue(String key) {
        return preferences.getBoolean(key, false);
    }
}
