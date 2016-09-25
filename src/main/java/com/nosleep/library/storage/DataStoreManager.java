package com.nosleep.library.storage;

import android.content.Context;

import com.nosleep.library.storage.preferences.AppPreferenceStorage;
import com.nosleep.library.storage.realmio.RealmStorageInterface;

/**
 * Created by Sanjeev on 11/09/16.
 */
public class DataStoreManager {

    private final Context context;
    private AppPreferenceStorage appPreferenceStorage;
    private RealmStorageInterface realmStorageInterface;

    public DataStoreManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public AppPreferenceStorage getAppPreferenceStorage() {
        if (appPreferenceStorage == null) {
            appPreferenceStorage = new SharedPreferenceStorage(context);
        }
        return appPreferenceStorage;
    }

    public RealmStorageInterface getRealmStorageInterface() {
        if (realmStorageInterface == null) {
            realmStorageInterface = new RealmStorage(context);
        }
        return realmStorageInterface;
    }
}
