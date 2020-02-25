package com.mfsoftware.home.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.mfsoftware.home.data.LoginRepository;
import com.mfsoftware.home.data.Result;
import com.mfsoftware.home.data.model.LoggedInUser;
import com.mfsoftware.home.R;

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
        Result<LoggedInUser> result = loginRepository.login(username, password);

        if (result instanceof Result.Success) {
            LoggedInUser data = ((Result.Success<LoggedInUser>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(data.getUserName(), data.getFirstName(), data.getToken())));
        } else loginResult.setValue(new LoginResult(R.string.login_failed));
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
