package com.mfsoftware.home

import android.app.Application
import com.mfsoftware.home.models.MagicMigration
import com.vk.api.sdk.VK
import io.realm.Realm
import io.realm.RealmConfiguration

class MagicApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        val realmConfiguration = RealmConfiguration.Builder()
                .schemaVersion(1)
                .migration(MagicMigration()) // Migration to run instead of throwing an exception
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)

        VK.initialize(this)
    }
}