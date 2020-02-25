package com.mfsoftware.home.api

import com.google.gson.annotations.SerializedName
import com.mfsoftware.home.models.core.Device

class GetDevicesResponse {
    @SerializedName("items")
    private val items: List<Device>? = null
}