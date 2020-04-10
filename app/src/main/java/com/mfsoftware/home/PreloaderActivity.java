package com.mfsoftware.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.content.SharedPreferences;
import android.provider.Settings;

import com.mfsoftware.home.adapters.FetchedData;
import com.mfsoftware.home.api.Api;
import com.mfsoftware.home.api.GetDevicesResponse;
import com.mfsoftware.home.api.GetHomesResponse;
import com.mfsoftware.home.api.GetRoomsResponse;
import com.mfsoftware.home.models.Device;
import com.mfsoftware.home.models.Home;
import com.mfsoftware.home.models.Room;

import java.util.List;
import java.util.concurrent.Executor;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreloaderActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private String fingerPrint;

    @SuppressLint("HardwareIds")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preloader);

        fingerPrint = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        preferences = getSharedPreferences("user", MODE_PRIVATE);

        boolean biometricPromptEnabled = getSharedPreferences("settings", MODE_PRIVATE).getBoolean("biometric_prompt", false);

        if (biometricPromptEnabled) {
            final byte[] tryCount = {3};

            Executor executor = ContextCompat.getMainExecutor(this);
            BiometricPrompt biometricPrompt = new BiometricPrompt(PreloaderActivity.this, executor, new BiometricPrompt.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);

                    if (tryCount[0] == 0) finish();
                    else tryCount[0]--;
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);

                    Api.create(getApplicationContext(), fingerPrint); // Инициализируем API
                    Api.token = preferences.getString("token", ""); // Передаем токен

                    startActivity(new Intent(PreloaderActivity.this, MainActivity.class));

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

            Api.create(getApplicationContext(), fingerPrint); // Инициализируем API

            if (token.equals("")) // Если токен не сохранен
                startActivityForResult(new Intent(this, IdentificationActivity.class), 1);
            else {
                Api.token = token; // Передаем токен

                startActivity(new Intent(this, MainActivity.class));
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
                String token = data.getStringExtra("token");

                // Сохраняем JWT токен в приватном хранилище
                SharedPreferences.Editor ed = preferences.edit();
                ed.putString("token", token);
                ed.apply();

                Api.create(getApplicationContext(), fingerPrint); // Инициализируем API
                Api.token = token; // И помещаем туда токен

                Realm realm = Realm.getDefaultInstance();
                Intent intent = new Intent(this, MainActivity.class);

                if (Api.isAvailable(this)) {
                    Api.json.getHomes(Api.getFingerPrint(), Api.getAuthorizationHeader()).enqueue(new Callback<GetHomesResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<GetHomesResponse> call, @NonNull Response<GetHomesResponse> response) {
                            if (response.isSuccessful()) {
                                assert response.body() != null;

                                List<com.mfsoftware.home.data.Home> homes = response.body().items;
                                Api.json.getRooms(Api.getFingerPrint(), Api.getAuthorizationHeader()).enqueue(new Callback<GetRoomsResponse>() {
                                    @Override
                                    public void onResponse(@NonNull Call<GetRoomsResponse> call, @NonNull Response<GetRoomsResponse> response) {
                                        if (response.isSuccessful()) {
                                            assert response.body() != null;

                                            List<com.mfsoftware.home.data.Room> rooms = response.body().items;
                                            Api.json.getDevices(Api.getFingerPrint(), Api.getAuthorizationHeader()).enqueue(new Callback<GetDevicesResponse>() {
                                                @Override
                                                public void onResponse(@NonNull Call<GetDevicesResponse> call, @NonNull Response<GetDevicesResponse> response) {
                                                    if (response.isSuccessful()) {
                                                        assert response.body() != null;
                                                        List<com.mfsoftware.home.data.Device> devices = response.body().items;

                                                        intent.putExtra("data", FetchedData.parse(devices, rooms, homes));
                                                        startActivity(intent);

                                                        finish();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(@NonNull Call<GetDevicesResponse> call, @NonNull Throwable t) {
                                                    finish();
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onFailure(@NonNull Call<GetRoomsResponse> call, @NonNull Throwable t) {
                                        finish();
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<GetHomesResponse> call, @NonNull Throwable t) {
                            finish();
                        }
                    });
                } else {
                    intent.putExtra("data", FetchedData.parseRealm(realm.copyFromRealm(realm.where(Device.class).findAll()), realm.copyFromRealm(realm.where(Room.class).findAll()), realm.copyFromRealm(realm.where(Home.class).findAll())));
                    startActivity(intent);

                    finish();
                }

                break;
        }
    }
}
