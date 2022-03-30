package com.example.anotherbottomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //REMOVE THE UGLY TOP BAR , must be placed before setcontentview
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //FORCE DAY MODE
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);




        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

    }
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();


    // TODO Just need to change the fragment_home.xml and fragment_settings.xml I think
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
                return true;

            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, settingsFragment).commit();
                return true;
        }
        return false;
    }

    //TODO SUTDCanteedFood1 redirects to dummy page
    public void launchSUTDCanteenFood1(View view){
        Intent intent = new Intent(this,SUTDCanteenFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 1 is launched");
    }

    public void launchSUTDCanteenFood2(View view){
        Intent intent = new Intent(this,SUTDCanteenFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 2 is launched");
    }

    public void launchSUTDCanteenFood3(View view){
        Intent intent = new Intent(this,SUTDCanteenFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 3 is launched");
    }

    public void launchSUTDCanteenFood4(View view){
        Intent intent = new Intent(this,SUTDCanteenFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 4 is launched");
    }

    public void launchSUTDCanteenFood5(View view){
        Intent intent = new Intent(this,SUTDCanteenFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 5 is launched");
    }
}