package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

open class Device : RealmObject() {
    @Required
    var id: String? = null

    @Required
    var localIp: String? = null

    @Required
    var type: Int? = null

    var room: String? = null
}