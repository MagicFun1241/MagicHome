package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

open class Home: RealmObject() {
    @Required
    lateinit var id: String

    @Required
    lateinit var address: String

    @Required
    lateinit var localIp: String
}