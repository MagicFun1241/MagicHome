package com.mfsoftware.home.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonApi {
    @POST("user/signin")
    Call<SignInResponse> signIn(@Body SignInRequest obj);

    @POST("user/register")
    Call<SignInResponse> registerUser(@Body SignUpRequest obj);

    @GET("devices")
    Call<GetDevicesResponse> getDevices();
}
