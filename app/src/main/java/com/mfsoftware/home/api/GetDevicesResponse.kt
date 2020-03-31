package com.mfsoftware.home.api

import com.google.gson.annotations.SerializedName
import com.mfsoftware.home.models.Device

class GetDevicesResponse {
    @SerializedName("items")
    lateinit var items: List<Device>
}