package com.example.wepack4u;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Timestamp
        TextView timestamp = findViewById(R.id.timestamp);
        timestamp.setText(new Timestamp().setTimestamp());

        // Receipt
        // dummies; should be retrieved from firebase
        ArrayList<FoodItem> cart = new ArrayList<>();
        cart.add(new FoodItem("Beef Ramen", 2, 4.70));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));
        ArrayList<FoodItem> cart2 = new ArrayList<>();
        cart2.add(new FoodItem("Curry Katsu Don", 3, 4.50));
        cart2.add(new FoodItem("A", 1, 4.50));

        String[] stores = {"Japanese", "Western"};
        ArrayList<ArrayList<FoodItem>> carts = new ArrayList<>();
        carts.add(cart);
        carts.add(cart2);
        int[] orders = {69, 420};

        RecyclerView recycler = findViewById(R.id.cart_recycler_b);
        CartRecycler adapter = new CartRecycler(ConfirmationActivity.this, stores, orders, carts, false);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        TextView total = findViewById(R.id.total2);
        String totalPrice = new TotalPrice(carts).getTotal();
        total.setText(totalPrice);

        // Text
        TextView paymentText = findViewById(R.id.payment_text);
        String text1 = "A payment of " + totalPrice + " has been made.";
        paymentText.setText(text1);

        int time = 15; // dummy var
        TextView waitingTime = findViewById(R.id.waiting_time);
        String text2 = "Your order will be done in " + time + " minutes.";
        waitingTime.setText(text2);

        // Button
        Button done = findViewById(R.id.done_button);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmationActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}
