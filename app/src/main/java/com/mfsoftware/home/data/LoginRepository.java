package com.mfsoftware.home.data;

import androidx.annotation.NonNull;

import com.mfsoftware.home.api.Api;
import com.mfsoftware.home.api.SignInResponse;
import com.mfsoftware.home.data.model.LoggedInUser;
import com.mfsoftware.home.security.Hash;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;
    private LoggedInUser user = null;

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

    // TODO: Начать использовать эту функцию
    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;


    }

    public void login(String username, String password, Callback<SignInResponse> callback) {
        Call<SignInResponse> call = Api.json.signIn(username, Hash.create(password));
        call.enqueue(callback);
    }
}
