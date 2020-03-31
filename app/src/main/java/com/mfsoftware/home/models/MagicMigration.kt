package com.mfsoftware.home.models

import io.realm.DynamicRealm
import io.realm.RealmMigration


class MagicMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

        if (oldVersion.toInt() == 0) {
            schema.create("Device")
                    .addField("localIp", String::class.java)
                    .addField("type", Int::class.java)
                    .addField("room", String::class.java)
                    .addField("id", String::class.java)
        }
    }
}