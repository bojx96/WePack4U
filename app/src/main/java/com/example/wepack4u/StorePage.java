package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class StorePage extends AppCompatActivity {
    ImageView image1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_page);

        image1 = (ImageView) findViewById(R.id.sutd_canteen_1_image);
        String imageurl = "https://firebasestorage.googleapis.com/v0/b/wepack4u-a3325.appspot.com/o/Pokemons%2Fbulbasaur.png?alt=media&token=87f81b38-d01c-4a3f-8670-9232e236c3c8";
        Picasso.get().load(imageurl).into(image1);

        dbTest testing1 = new dbTest();
        testing1.getStoreList();
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