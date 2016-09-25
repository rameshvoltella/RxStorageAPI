package com.nosleep.library.storage.realmio;

import android.content.Context;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.exceptions.RealmException;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public abstract class OnSubscribeRealm<T extends RealmObject> implements Observable.OnSubscribe<T> {
    private Context context;
    private File file;

    public OnSubscribeRealm(Context context) {
        this.context = context;
        file = null;
    }

    public OnSubscribeRealm(Context context, File file) {
        this.context = context;
        this.file = file;
    }

    @Override
    public void call(final Subscriber<? super T> subscriber) {
        RealmConfiguration.Builder builder = file != null ? new RealmConfiguration.Builder(context, file) : new RealmConfiguration.Builder(context);
        RealmConfiguration configuration = builder.deleteRealmIfMigrationNeeded().build();//TODO this will have serious implication

        final Realm realm = file != null ? Realm.getInstance(configuration) : Realm.getDefaultInstance();
        subscriber.add(Subscriptions.create(new Action0() {
            @Override
            public void call() {
                try {
                    realm.close();
                } catch (RealmException ex) {
                    subscriber.onError(ex);
                }
            }
        }));

        T object;
        realm.beginTransaction();
        try {
            object = get(realm);
            realm.commitTransaction();
        } catch (RuntimeException e) {
            realm.cancelTransaction();
            subscriber.onError(new RealmException("Error during transaction.", e));
            return;
        } catch (Error e) {
            realm.cancelTransaction();
            subscriber.onError(e);
            return;
        }
        if (object != null) {
            subscriber.onNext(object);
        }
        subscriber.onCompleted();
    }

    public abstract T get(Realm realm);
}