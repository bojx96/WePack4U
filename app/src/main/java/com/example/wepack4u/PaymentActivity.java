package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Checkout section
        ArrayList<FoodItem> cart = new ArrayList<>(); // arraylist should be retrieved from firebase
        TextView total = findViewById(R.id.total);
        int counter = 1;
        double subtotal = 0.0f;

        TableLayout cartA = findViewById(R.id.cart1a);
        TableLayout cartB = findViewById(R.id.cart1b);
        TableLayout cartC = findViewById(R.id.cart1c);

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

        // for testing
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

        // Payment section
        RadioGroup payment_method = findViewById(R.id.payment_method);
        Button checkout = findViewById(R.id.checkout_button);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payment_method.getCheckedRadioButtonId() != -1) {
                    int selected = payment_method.getCheckedRadioButtonId();
                    RadioButton method = findViewById(selected);
                    Intent intent = new Intent(PaymentActivity.this, ConfirmationActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PaymentActivity.this, R.string.choose_payment, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
