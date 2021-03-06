package com.mfsoftware.home.api

import com.google.gson.annotations.SerializedName
import com.mfsoftware.home.data.Device

class GetDevicesResponse {
    @SerializedName("items")
    lateinit var items: List<Device>
}