package com.mfsoftware.home.models

import io.realm.RealmObject
import io.realm.annotations.Required

class Room : RealmObject() {
    @Required
    val name: String? = null
}