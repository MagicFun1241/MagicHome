package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

open class Room : RealmObject() {
    @Required
    lateinit var id: String

    @Required
    lateinit var name: String
}