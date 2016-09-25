package com.nosleep.library.storage;

import android.content.Context;

import com.nosleep.library.storage.realmio.RealmObservable;
import com.nosleep.library.storage.realmio.RealmStorageInterface;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Sanjeev on 24/09/16.
 */
public class RealmStorage implements RealmStorageInterface {
    private Context context;
    private File realmFile;

    RealmStorage(Context context) {
        this.context = context.getApplicationContext();
        this.realmFile = context.getFilesDir();
    }

    @Override
    public <T extends RealmObject> Observable<T> getRealmObservable(Func1<Realm, T> opFunc) {
        return RealmObservable.object(context, realmFile, opFunc);
    }

    @Override
    public <T extends RealmObject> Observable<T> saveObject(final T object) {
        return RealmObservable.object(context, realmFile, new Func1<Realm, T>() {
            @Override
            public T call(Realm realm) {
                return realm.copyToRealm(object);
            }
        });
    }


}
