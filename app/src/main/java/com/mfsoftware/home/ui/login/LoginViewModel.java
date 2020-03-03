package com.mfsoftware.home.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.mfsoftware.home.api.SignInResponse;
import com.mfsoftware.home.data.LoginRepository;
import com.mfsoftware.home.R;

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

    public void login(String username, String password) {
        // Может быть запущенно паралельно
        loginRepository.login(username, password, new Callback<SignInResponse>() {
            @Override
            public void onResponse(@NonNull Call<SignInResponse> call, @NonNull Response<SignInResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;

                    loginResult.setValue(new LoginResult(new LoggedInUserView(response.body().userName, response.body().firstName, response.body().token)));
                } else loginResult.setValue(new LoginResult(R.string.login_failed));
            }

            @Override
            public void onFailure(@NonNull Call<SignInResponse> call, @NonNull Throwable t) {
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        });
    }

    void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username))
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        else if (!isPasswordValid(password))
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        else loginFormState.setValue(new LoginFormState(true));
    }

    // Проверка правильности имени пользователя
    private boolean isUserNameValid(String username) {
        if (username == null) return false;

        return (username.contains("@")) ? Patterns.EMAIL_ADDRESS.matcher(username).matches() : !username.trim().isEmpty();
    }

    // Проверка правильности пароля
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 8;
    }
}
