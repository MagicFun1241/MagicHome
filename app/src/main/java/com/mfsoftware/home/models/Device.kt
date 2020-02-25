package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

class Device : RealmObject() {
    @Required
    val local_ip: String? = null

    @Required
    val type: Number? = 0

    val room: Room? = null
}