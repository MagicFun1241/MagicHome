package com.mfsoftware.home.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.Context;

import com.mfsoftware.home.api.SignInResponse;
import com.mfsoftware.home.data.LoginRepository;
import com.mfsoftware.home.R;
import com.mfsoftware.home.security.Validator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(final Context context, String username, String password) {
        loginRepository.login(username, password, new Callback<SignInResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    String userName = response.body().userName;
                    String firstName = response.body().firstName;

                    LoggedInUserView loggedInUserView = new LoggedInUserView(userName, firstName, response.body().token);

                    loginRepository.setLoggedInUser(context, loggedInUserView);

                    loginResult.setValue(new LoginResult(loggedInUserView));
                } else loginResult.setValue(new LoginResult(R.string.login_failed));
            }

            @Override
            public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    void loginDataChanged(String username, String password) {
        if (!Validator.isLogin(username))
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        else if (!Validator.isPassword(password))
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        else loginFormState.setValue(new LoginFormState(true));
    }
}
