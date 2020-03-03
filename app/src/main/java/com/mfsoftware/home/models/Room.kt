package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

open class Room : RealmObject() {
    @Required
    var name: String? = null
}