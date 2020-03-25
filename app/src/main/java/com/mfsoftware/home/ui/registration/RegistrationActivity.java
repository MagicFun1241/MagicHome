package com.mfsoftware.home.ui.registration;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.mfsoftware.home.R;

public class RegistrationActivity extends AppCompatActivity {

    private RegistrationViewModel registrationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationViewModel = new ViewModelProvider(this, new RegistrationViewModelFactory()).get(RegistrationViewModel.class);

        registrationViewModel.getRegistrationFormState().observe(this, new Observer<RegistrationFormState>() {
            @Override
            public void onChanged(@Nullable RegistrationFormState registrationFormState) {
                if (registrationFormState == null) return;

                /*loginButton.setEnabled(loginFormState.isDataValid());

                if (loginFormState.getUsernameError() != null)
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));

                if (loginFormState.getPasswordError() != null)
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));*/
            }
        });
    }

    private void showRegistrationFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
