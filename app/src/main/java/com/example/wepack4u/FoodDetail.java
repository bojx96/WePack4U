package com.example.wepack4u;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FoodDetail extends AppCompatActivity {
    RecyclerView foodDetailRecyclerView;
    String options[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        foodDetailRecyclerView = findViewById(R.id.foodDetailRecyclerView);

        options = getResources().getStringArray(R.array.food_options);
        FoodDetailAdaptor foodDetailAdaptor = new FoodDetailAdaptor(this, options);
        foodDetailRecyclerView.setAdapter(foodDetailAdaptor);
        foodDetailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
