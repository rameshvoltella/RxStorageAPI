package com.nosleep.library.storage.preferences;

/**
 * Created by Sanjeev on 13/09/16.
 */
public interface AppPreferenceStorage {

    void storeIntegerValue(String key, int value);

    void storeStringValue(String key, String value);

    void storeFloatValue(String key, float value);

    void storeLongValue(String key, long value);

    void storeBoolValue(String key, boolean value);

    int retrieveIntegerValue(String key);

    String retrieveStringValue(String key);

    float retrieveFloatValue(String key);

    long retrieveLongValue(String key);

    boolean retrieveBooleanValue(String key);
}
