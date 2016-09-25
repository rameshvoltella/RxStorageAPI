package com.nosleep.library.storage.realmio;

import android.content.Context;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmObject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public final class RealmObservable {
    private RealmObservable() {
    }


    public static <T extends RealmObject> Observable<T> object(Context context, File file, final Func1<Realm, T> function) {
        return Observable.create(new OnSubscribeRealm<T>(context, file) {
            @Override
            public T get(Realm realm) {
                return function.call(realm);
            }
        });
    }

}