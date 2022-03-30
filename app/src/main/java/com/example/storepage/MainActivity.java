package com.example.storepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import android.util.Log;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //REMOVE THE UGLY TOP BAR
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        //FORCE DAY MODE
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_main);
    }


    //TODO SUTDCanteedFood1 redirects to dummy page
    public void launchSUTDCanteenFood1(View view){
        Intent intent = new Intent(this,SUTDCanteedFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 1 is launched");
    }

    public void launchSUTDCanteenFood2(View view){
        Intent intent = new Intent(this,SUTDCanteedFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 2 is launched");
    }

    public void launchSUTDCanteenFood3(View view){
        Intent intent = new Intent(this,SUTDCanteedFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 3 is launched");
    }

    public void launchSUTDCanteenFood4(View view){
        Intent intent = new Intent(this,SUTDCanteedFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 4 is launched");
    }

    public void launchSUTDCanteenFood5(View view){
        Intent intent = new Intent(this,SUTDCanteedFood1.class);

        startActivity(intent);
        Log.d(LOG_TAG, "Canteen Food 5 is launched");
    }


}