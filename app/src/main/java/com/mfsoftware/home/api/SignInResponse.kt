package com.mfsoftware.home.api

import com.google.gson.annotations.SerializedName

class SignInResponse {
    @SerializedName("token")
    lateinit var token: String

    @SerializedName("username")
    lateinit var userName: String

    @SerializedName("firstname")
    lateinit var firstName: String
}