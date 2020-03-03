package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

open class Device : RealmObject() {
    @Required
    var local_ip: String? = null

    @Required
    var type: Int? = null

    var room: Room? = null
}