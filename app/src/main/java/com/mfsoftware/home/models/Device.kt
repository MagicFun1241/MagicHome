package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

open class Device : RealmObject() {
    @Required
    lateinit var id: String

    @Required
    lateinit var localIp: String

    @Required
    var type: Int = 0

    var room: String? = null
}