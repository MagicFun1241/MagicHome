package com.mfsoftware.home;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mfsoftware.home.api.Api;
import com.mfsoftware.home.api.GetDevicesResponse;
import com.mfsoftware.home.data.model.LoggedInUser;
import com.mfsoftware.home.models.Device;
import com.mfsoftware.home.models.Room;
import com.mfsoftware.home.ui.login.LoggedInUserView;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NotificationsFragment.OnFragmentInteractionListener,
        DashboardFragment.OnFragmentInteractionListener,
        AddDeviceFragment.OnFragmentInteractionListener {

    FragmentManager fragmentManager; // Для управления фрагментами в BottomAppBar
    Realm realm;

    // А вот и сами фрагменты
    Fragment notificationsFragment;
    Fragment dashboardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomAppBar bottomNavigation = findViewById(R.id.bottom_bar);
        setSupportActionBar(bottomNavigation);

        SharedPreferences preferences = getSharedPreferences("user", MODE_PRIVATE);

        LoggedInUser user = new LoggedInUser(preferences.getString("username", ""), preferences.getString("firstname", ""));

        realm = Realm.getDefaultInstance(); // Получаем экземпляр для работы с локальной базой данных

        // Инициализируем фрагменты для более быстрого доступа в будущем
        notificationsFragment = NotificationsFragment.newInstance();

        if (!Api.isAvailable(this))
            dashboardFragment = DashboardFragment.newInstance(user, realm.copyFromRealm(realm.where(Device.class).findAll()), realm.copyFromRealm(realm.where(Room.class).findAll()));
        else dashboardFragment = DashboardFragment.newInstance(user);

        // Доверяем их под руководство менеджера
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.content_main, dashboardFragment)
                .add(R.id.content_main, notificationsFragment)
                .commit();

        bottomNavigation.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_notifications:
                        fragmentManager.beginTransaction().replace(R.id.content_main, notificationsFragment).commit();
                        break;
                    case R.id.navigation_dashboard:
                        fragmentManager.beginTransaction().replace(R.id.content_main, dashboardFragment).commit();
                        break;
                }
                return false;
            }
        });

        bottomNavigation.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BottomNavigationDrawerFragment().show(fragmentManager, "d");
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDeviceFragment.newInstance().show(fragmentManager, "addDevice");
            }
        });

        // Picasso.get().placeholder().load(R.drawable.ic_person).into((ImageView) findViewById(R.id.profile));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
