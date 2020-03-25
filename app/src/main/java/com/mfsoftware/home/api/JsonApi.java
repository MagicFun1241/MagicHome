package com.mfsoftware.home.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApi {
    @POST("user/signin")
    Call<SignInResponse> signIn(@Body SignInRequest obj);

    @POST("user/register")
    Call<SignUpResponse> registerUser(@Body SignUpRequest obj);

    @GET("devices")
    Call<GetDevicesResponse> getDevices(@Query("fingerprint") String fingerPrint, @Header("Authorization") String token);

    @GET("device/{id}")
    Call<SignInResponse> getDevice(@Path("id") String id);
}
