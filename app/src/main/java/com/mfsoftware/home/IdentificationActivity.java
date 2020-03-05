package com.mfsoftware.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mfsoftware.home.ui.login.LoginActivity;
import com.mfsoftware.home.ui.registration.RegistrationActivity;

public class IdentificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        Button signIn = findViewById(R.id.signin_button);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), LoginActivity.class), 2); // Ждем авторизации
            }
        });

        Button signUp = findViewById(R.id.signup_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(v.getContext(), RegistrationActivity.class), 3); // Ждем совершения регистрации
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 2: // Если пользователь авторизовался
                case 3: // Если успешно зарегистрировался
                    Intent result = new Intent();
                    result.putExtra("token", data.getStringExtra("token"));

                    setResult(RESULT_OK, result);
                    finish();
                    break;
            }
        }
    }
}
