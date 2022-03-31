package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class StorePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);
    }

    //TODO SUTDCanteedFood1 redirects to dummy page
    public void launchSUTDCanteenFood1(View view){
        Toast.makeText(this,"food1 selected",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,SUTDCanteedFood1.class);
//        startActivity(intent);
   }

    public void launchSUTDCanteenFood2(View view){
        Toast.makeText(this,"food2 selected",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,SUTDCanteedFood1.class);
//        startActivity(intent);
    }

    public void launchSUTDCanteenFood3(View view){
        Toast.makeText(this,"food3 selected",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,SUTDCanteedFood1.class);
//        startActivity(intent);
    }

    public void launchSUTDCanteenFood4(View view){
        Toast.makeText(this,"food4 selected",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,SUTDCanteedFood1.class);
//        startActivity(intent);
    }

    public void launchSUTDCanteenFood5(View view){
        Toast.makeText(this,"food5 selected",Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,SUTDCanteedFood1.class);
//        startActivity(intent);
    }
}