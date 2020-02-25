package com.mfsoftware.home;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

class MagicApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .name("mfsoftware.home")
                //.schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
