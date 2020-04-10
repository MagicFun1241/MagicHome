package com.mfsoftware.home.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApi {
    @POST("user/signin")
    Call<SignInResponse> signIn(@Body SignInRequest body, @Header("Fingerprint") String fingerPrint);

    @POST("user/register")
    Call<SignUpResponse> registerUser(@Body SignUpRequest body, @Header("Fingerprint") String fingerPrint);

    @GET("devices")
    Call<GetDevicesResponse> getDevices(@Header("Fingerprint") String fingerPrint, @Header("Authorization") String token);

    @GET("device/{id}")
    Call<SignInResponse> getDevice(@Path("id") String id, @Header("Fingerprint") String fingerPrint, @Header("Authorization") String token);

    @GET("rooms")
    Call<GetRoomsResponse> getRooms(@Header("Fingerprint") String fingerPrint, @Header("Authorization") String token);

    @GET("homes")
    Call<GetHomesResponse> getHomes(@Header("Fingerprint") String fingerPrint, @Header("Authorization") String token);
}
