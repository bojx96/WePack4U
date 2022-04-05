package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class StorePage extends AppCompatActivity {
    //below this is where the data is inputted in
    RecyclerView recyclerView;
    String s1[];
    //TODO for now the image is hardcoded, replace from db
    int images[] = {R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background,R.drawable.ic_launcher_background};

public class StorePage extends AppCompatActivity {
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        recyclerView = findViewById(R.id.recyclerview);
        //TODO for now the array is hardcoded, should replace th R.array with smth else
        s1 = getResources().getStringArray(R.array.programming_languages);
        //my adapter java is where the data being allocated
        StoreAdapter myAdapter = new StoreAdapter(this, s1, images);
        //recyclerview is the layout
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
    //TODO make the clickable in a list?
    public void goestostore(View view){

        Toast.makeText(this,"selected",Toast.LENGTH_SHORT).show();

    }
    //TODO SUTDCanteedFood1 redirects to dummy page
   /* public void launchSUTDCanteenFood1(View view){
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
//        startActivity(intent);*/

}