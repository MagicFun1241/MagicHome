package com.mfsoftware.home.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApi {
    @POST("/api/user/signin")
    Call<SignInResponse> signIn(@Body String username, @Body String password);

    @POST("/api/user/register")
    Call<SignInResponse> registerUser(@Body SignInResponse obj);

    @GET("/api/devices")
    Call<GetDevicesResponse> getDevices();
}
