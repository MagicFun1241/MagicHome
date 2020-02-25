package com.mfsoftware.home.data;

import androidx.annotation.NonNull;

import com.mfsoftware.home.api.Api;
import com.mfsoftware.home.api.SignInResponse;
import com.mfsoftware.home.data.model.LoggedInUser;
import com.mfsoftware.home.security.Hash;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        try {
            Call<SignInResponse> call = Api.json.signIn(username, Hash.create(password));
            call.enqueue(new Callback<SignInResponse>() {
                @Override
                public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        return new Result.Success<>(new LoggedInUser(response.body().getUserName(), response.body().getFirstName(), response.body().getToken()));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {

                }
            });
        }
        catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
