package com.example.wepack4u;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        int order = 69; // nice dummy var
        TextView orderNo = findViewById(R.id.order_num);
        String orderNum = "Order No. " + order;
        orderNo.setText(orderNum);

        Calendar c = Calendar.getInstance();
        TextView timestamp = findViewById(R.id.timestamp);
        int second = c.get(Calendar.SECOND);
        String secondText;
        if (second < 10) { secondText = "0" + second; }
        else { secondText = "" + second; }
        int minute = c.get(Calendar.MINUTE);
        String minuteText;
        if (minute < 10) { minuteText = "0" + minute; }
        else { minuteText = "" + minute; }
        String datetime =
                c.get(Calendar.HOUR_OF_DAY) + ":" + minuteText + ":" + secondText + " " +
                c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
        timestamp.setText(datetime);

        ArrayList<FoodItem> cart = new ArrayList<>(); // arraylist should be retrieved from firebase
        TextView total = findViewById(R.id.total2);
        int counter = 1;
        double subtotal = 0.0f;

        TableLayout cartA = findViewById(R.id.cart2a);
        TableLayout cartB = findViewById(R.id.cart2b);
        TableLayout cartC = findViewById(R.id.cart2c);

        TableRow.LayoutParams paramsLeft = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams paramsMid = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        paramsMid.gravity = Gravity.CENTER;
        TableRow.LayoutParams paramsRight = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        paramsRight.gravity = Gravity.END;

        cart.add(new FoodItem("Beef Ramen", 2, 4.70));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));
        cart.add(new FoodItem("Curry Katsu Don", 3, 4.50));
        cart.add(new FoodItem("A", 1, 4.50));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));

        for (FoodItem f : cart) {
            TableRow trow1 = new TableRow(this);
            TextView name = new TextView(this);
            String text = counter + ".   " + f.getName();
            name.setText(text);
            name.setLayoutParams(paramsLeft);
            trow1.addView(name);
            cartA.addView(trow1);

            TableRow trow2 = new TableRow(this);
            TextView unit = new TextView(this);
            unit.setText(f.getUnit());
            unit.setLayoutParams(paramsMid);
            trow2.addView(unit);
            cartB.addView(trow2);

            TableRow trow3 = new TableRow(this);
            TextView price = new TextView(this);
            String priceValue = f.getPrice();
            if (f.getPriceValue() * 10 % 1 == 0) { priceValue = priceValue + "0"; }
            price.setText(priceValue);
            price.setLayoutParams(paramsRight);
            trow3.addView(price);
            cartC.addView(trow3);

            counter++;
            subtotal = subtotal + f.getPriceValue();
        }

        String totalPrice = "$" + subtotal;
        if (subtotal * 10 % 1 == 0) {
            totalPrice = totalPrice + "0";
        }
        total.setText(totalPrice);

        TextView paymentText = findViewById(R.id.payment_text);
        String text1 = "A payment of $" + totalPrice + " has been made.";
        paymentText.setText(text1);

        int time = 15; // dummy var
        TextView waitingTime = findViewById(R.id.waiting_time);
        String text2 = "Your order will be done in " + time + " minutes.";
        waitingTime.setText(text2);

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
