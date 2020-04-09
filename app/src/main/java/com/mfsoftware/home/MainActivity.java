package com.mfsoftware.home;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mfsoftware.home.api.Api;
import com.mfsoftware.home.data.model.LoggedInUser;
import com.mfsoftware.home.models.Device;
import com.mfsoftware.home.models.Room;
import com.mfsoftware.home.views.OnSwipeTouchListener;
import com.vk.api.sdk.utils.VKUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Arrays;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements EventsFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener {

    private FragmentManager fragmentManager; // Для управления фрагментами в BottomAppBar

    BottomNavigationView bottomNavigation;

    // А вот и сами фрагменты
    private Fragment homeFragment;
    private Fragment eventsFragment;
    private Fragment menuFragment;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FIXME: Вообще не работает
        LinearLayout root = findViewById(R.id.content_main);
        root.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();

                Toast.makeText(getApplicationContext(), "Left", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();

                Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_LONG).show();
            }
        });

        String[] fingerprints = VKUtils.getCertificateFingerprint(this, this.getPackageName());
        assert fingerprints != null;

        android.util.Log.d("VK", Arrays.toString(fingerprints));

        bottomNavigation = findViewById(R.id.bottom_navigation);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);

        LoggedInUser user = new LoggedInUser(preferences.getString("username", ""), preferences.getString("firstname", ""));

        // Инициализируем фрагменты для более быстрого доступа в будущем
        eventsFragment = EventsFragment.newInstance();
        menuFragment = MenuFragment.newInstance(user);

        if (!Api.isAvailable(this)) {
            Realm realm = Realm.getDefaultInstance(); // Получаем экземпляр для работы с локальной базой данных
            homeFragment = HomeFragment.newInstance(user, realm.copyFromRealm(realm.where(Device.class).findAll()), realm.copyFromRealm(realm.where(Room.class).findAll()));
        }
        else homeFragment = HomeFragment.newInstance(user);

        // Доверяем их под руководство менеджера
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.content_main, homeFragment)
                .add(R.id.content_main, eventsFragment)
                .add(R.id.content_main, menuFragment)
                .commit();

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    fragmentManager.beginTransaction().replace(R.id.content_main, homeFragment).commit();
                    return true;
                case R.id.navigation_events:
                    fragmentManager.beginTransaction().replace(R.id.content_main, eventsFragment).commit();
                    return true;
                case R.id.navigation_menu:
                    fragmentManager.beginTransaction().replace(R.id.content_main, menuFragment).commit();
                    return true;
            }
            return false;
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
