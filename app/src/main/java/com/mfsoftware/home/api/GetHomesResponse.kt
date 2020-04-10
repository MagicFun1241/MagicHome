package com.mfsoftware.home.api

import com.google.gson.annotations.SerializedName
import com.mfsoftware.home.data.Home

class GetHomesResponse {
    @SerializedName("items")
    lateinit var items: List<Home>
}