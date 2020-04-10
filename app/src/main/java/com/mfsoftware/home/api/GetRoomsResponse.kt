package com.mfsoftware.home.api

import com.google.gson.annotations.SerializedName
import com.mfsoftware.home.data.Room

class GetRoomsResponse {
    @SerializedName("items")
    lateinit var items: List<Room>
}