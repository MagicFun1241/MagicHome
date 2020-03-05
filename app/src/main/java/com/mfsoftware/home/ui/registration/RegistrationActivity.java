package com.mfsoftware.home.ui.registration;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.mfsoftware.home.R;

public class RegistrationActivity extends AppCompatActivity {

    private RegistationViewModel registationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registationViewModel = new ViewModelProvider(this, new RegistrationViewModelFactory()).get(RegistationViewModel.class);
    }

    private void showRegistrationFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
