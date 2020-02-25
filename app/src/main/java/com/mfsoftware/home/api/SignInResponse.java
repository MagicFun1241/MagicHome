package com.mfsoftware.home.api;

import com.google.gson.annotations.SerializedName;

public class SignInResponse {
    @SerializedName("token")
    private String token;

    @SerializedName("username")
    private String userName;

    @SerializedName("firstname")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getToken() {
        return token;
    }
}
