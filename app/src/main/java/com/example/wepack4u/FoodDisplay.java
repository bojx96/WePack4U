package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class FoodDisplay extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[],s2[];
    int images[] = {R.drawable.food_image_placeholder,R.drawable.food_image_placeholder,R.drawable.food_image_placeholder,R.drawable.food_image_placeholder};
    //Brandon's Edit
//    List<FoodStore> foodStores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_display);
        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.food_names);
        s2 = getResources().getStringArray(R.array.food_prices);

        // Brandon's Edit
//        FoodDisplayAdaptor foodDisplayAdaptor = new FoodDisplayAdaptor(this, foodStores );
        FoodDisplayAdaptor foodDisplayAdaptor = new FoodDisplayAdaptor(this, s1, s2, images);
        recyclerView.setAdapter(foodDisplayAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
    public void toFoodDetail(View v){
        startActivity(new Intent (FoodDisplay.this,FoodDetail.class));
    }
}