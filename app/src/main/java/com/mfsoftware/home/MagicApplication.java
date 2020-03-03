package com.mfsoftware.home;

import android.app.Application;

import io.realm.Realm;

public class MagicApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
