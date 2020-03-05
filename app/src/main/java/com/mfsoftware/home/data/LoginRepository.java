package com.mfsoftware.home.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.mfsoftware.home.api.Api;
import com.mfsoftware.home.api.SignInRequest;
import com.mfsoftware.home.api.SignInResponse;
import com.mfsoftware.home.data.model.LoggedInUser;
import com.mfsoftware.home.security.Hash;
import com.mfsoftware.home.ui.login.LoggedInUserView;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private LoggedInUserView user = null;

    public static LoginRepository getInstance() {
        if (instance == null) instance = new LoginRepository();
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    public void setLoggedInUser(Context context, LoggedInUserView user) {
        this.user = user;

        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", user.getUserName());
        editor.putString("firstname", user.getFirstName());
        editor.putString("token", user.getToken());
        editor.apply();
    }

    public void login(String username, String password, Callback<SignInResponse> callback) {
        Call<SignInResponse> call = Api.json.signIn(new SignInRequest(username, Hash.create(password)));
        call.enqueue(callback);
    }
}
