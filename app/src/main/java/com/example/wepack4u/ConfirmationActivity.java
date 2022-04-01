package com.example.wepack4u;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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

        // Order number
        int order = 69; // nice dummy var
        TextView orderNo = findViewById(R.id.order_num);
        String orderNum = "Order No. " + order;
        orderNo.setText(orderNum);

        // Timestamp
        TextView timestamp = findViewById(R.id.timestamp);
        timestamp.setText(new Timestamp().setTimestamp());

        // Receipt
        ArrayList<FoodItem> cart = new ArrayList<>(); // arraylist should be retrieved from firebase
        TableLayout cartA = findViewById(R.id.cart2a);
        TableLayout cartB = findViewById(R.id.cart2b);
        TableLayout cartC = findViewById(R.id.cart2c);
        ThreeColumnTable table = new ThreeColumnTable(cartA, cartB, cartC, cart, ConfirmationActivity.this);
        table.createTable();

        TextView total = findViewById(R.id.total2);
        total.setText(table.getTotalPrice());

        // Text
        TextView paymentText = findViewById(R.id.payment_text);
        String text1 = "A payment of $" + table.getTotalPrice() + " has been made.";
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
                Intent intent = new Intent(ConfirmationActivity.this, StorePage.class);
                startActivity(intent);
            }
        });
    }
}
