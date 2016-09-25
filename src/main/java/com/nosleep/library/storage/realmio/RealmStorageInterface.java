package com.nosleep.library.storage.realmio;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Sanjeev on 24/09/16.
 */
public interface RealmStorageInterface {

    <T extends RealmObject> Observable<T> getRealmObservable(Func1<Realm, T> opFunc);

    <T extends RealmObject> Observable<T>  saveObject(T object);
}
