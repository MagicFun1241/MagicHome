package com.mfsoftware.home;

import android.app.Application;

import com.mfsoftware.home.models.MagicMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MagicApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(new MagicMigration()) // Migration to run instead of throwing an exception
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
