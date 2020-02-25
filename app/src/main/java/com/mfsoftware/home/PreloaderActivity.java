package com.mfsoftware.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.mfsoftware.home.api.Api;
import com.mfsoftware.home.ui.login.LoggedInUserView;

import java.util.concurrent.Executor;

public class PreloaderActivity extends AppCompatActivity {

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);

        preferences = getPreferences(MODE_PRIVATE);

        boolean biometricPromptEnabled = preferences.getBoolean("biometricPromptEnabled", false);

        if (biometricPromptEnabled) {
            Executor executor = ContextCompat.getMainExecutor(this);
            BiometricPrompt biometricPrompt = new BiometricPrompt(PreloaderActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_SHORT).show();

                    finish();
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);

                    Api.create(); // Инициализируем API
                    Api.token = preferences.getString("token", ""); // Передаем токен

                    Intent intent = new Intent(PreloaderActivity.this, MainActivity.class);
                    intent.putExtra("user", new LoggedInUserView(preferences.getString("userName", ""), preferences.getString("firstName", "")));

                    // Показываем главную activity
                    startActivity(intent);

                    finish();
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                    Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();

                    finish();
                }
            });

            BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                    .setTitle(getString(R.string.biometric_confirmation))
                    .setSubtitle(getString(R.string.сonfirm_identity))
                    .setNegativeButtonText(getString(R.string.cancel))
                    .build();

            biometricPrompt.authenticate(promptInfo);
        }
        else {
            String token = preferences.getString("token", "");

            Api.create(); // Инициализируем API

            if (token.equals("")) // Если токен не сохранен
                startActivityForResult(new Intent(this, IdentificationActivity.class), 1);
            else {
                Api.token = token; // Передаем токен

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", new LoggedInUserView(preferences.getString("userName", ""), preferences.getString("firstName", "")));

                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) return;
        if (resultCode != RESULT_OK) return;

        switch (requestCode) {
            case 1:
            case 2:
                LoggedInUserView user = data.getParcelableExtra("user");
                assert user != null;

                // Сохраняем JWT токен в приватном хранилище
                SharedPreferences.Editor ed = preferences.edit();
                ed.putString("token", user.getToken());
                ed.apply();

                Api.create(); // Инициализируем API
                Api.token = user.getToken(); // И помещаем туда токен

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);

                finish();
                break;
        }
    }
}
