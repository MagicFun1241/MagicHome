package com.mfsoftware.home.api

import com.google.gson.annotations.SerializedName

class SignInResponse {
    @SerializedName("token")
    lateinit var token: String

    @SerializedName("userName")
    lateinit var userName: String

    @SerializedName("firstName")
    lateinit var firstName: String
}