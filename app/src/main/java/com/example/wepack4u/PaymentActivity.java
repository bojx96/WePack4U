package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Checkout section
        ArrayList<FoodItem> cart = new ArrayList<>(); // arraylist should be retrieved from firebase
        TableLayout cartDisplay = findViewById(R.id.cart);
        TextView total = findViewById(R.id.total);
        int counter = 1;
        double subtotal = 0;
        // for testing
        cart.add(new FoodItem("Beef Ramen", 2, 4.70));

        for (FoodItem f : cart) {
            TableRow trow = new TableRow(this);
            TextView index = new TextView(this);
            index.setText(counter+".");
            trow.addView(index);
            TextView name = new TextView(this);
            name.setText(f.getName());
            trow.addView(name);
            TextView unit = new TextView(this);
            unit.setText(f.getUnit());
            trow.addView(unit);
            TextView price = new TextView(this);
            price.setText(f.getPrice());
            trow.addView(price);
            cartDisplay.addView(trow);
            counter++;
            subtotal = subtotal + f.getPriceValue();
        }

        total.setText("$"+subtotal);

        // Payment section
        RadioGroup payment_method = findViewById(R.id.payment_method);
        Button checkout = findViewById(R.id.checkout_button);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (payment_method.getCheckedRadioButtonId() != -1) {
                    int selected = payment_method.getCheckedRadioButtonId();
                    RadioButton method = findViewById(selected);
                    // go to next page
                }
            }
        });
    }
}
