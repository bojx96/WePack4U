package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
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
        TableLayout cartA = findViewById(R.id.cart1a);
        TableLayout cartB = findViewById(R.id.cart1b);
        TableLayout cartC = findViewById(R.id.cart1c);
        ThreeColumnTable table = new ThreeColumnTable(cartA, cartB, cartC, cart, PaymentActivity.this);
        table.createTable();

        TextView total = findViewById(R.id.total);
        total.setText(table.getTotalPrice());

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
