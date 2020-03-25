package com.mfsoftware.home.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

import com.mfsoftware.home.security.Hash;
import com.readystatesoftware.chuck.ChuckInterceptor;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static String token;
    public static JsonApi json;
    private static String fingerPrint = "";

    private static final String HTTP_URL = "https://home.mfsoftware.site/api/";

    public static void create(Context context, String fingerprint) {
        fingerPrint = Hash.create(fingerprint);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ChuckInterceptor(context))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HTTP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        json = retrofit.create(JsonApi.class);
    }

    public static boolean isAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;

        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return (capabilities != null) && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
    }

    public static String getFingerPrint() {
        return fingerPrint;
    }

    public static String getAuthorizationHeader() {
        return String.format("Bearer %s", token);
    }
}
