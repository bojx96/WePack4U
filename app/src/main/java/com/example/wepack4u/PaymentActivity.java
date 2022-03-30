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
    public static final String TABLE_KEY = "table_key";
    public static final String TOTAL_KEY = "total_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        // Checkout section
        ArrayList<FoodItem> cart = new ArrayList<>(); // arraylist should be retrieved from firebase
        TableLayout cartDisplay = findViewById(R.id.cart);
        TextView total = findViewById(R.id.total);
        int counter = 1;
        double subtotal = 0.0f;

        TableRow.LayoutParams paramsLeft = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1.8f);
        paramsLeft.gravity = Gravity.START;
        TableRow.LayoutParams paramsMid = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1.05f);
        paramsMid.gravity = Gravity.CENTER;
        TableRow.LayoutParams paramsRight = new TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT,
                1.05f);
        paramsRight.gravity = Gravity.END;

        ArrayList<TableRow> rows = new ArrayList<>();

        // for testing
        cart.add(new FoodItem("Beef Ramen", 2, 4.70));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));

        for (FoodItem f : cart) {
            TableRow trow = new TableRow(this);

            TextView name = new TextView(this);
            String text = counter + ".   " + f.getName();
            name.setText(text);
            name.setLayoutParams(paramsLeft);
            trow.addView(name);

            TextView unit = new TextView(this);
            unit.setText(f.getUnit());
            unit.setLayoutParams(paramsMid);
            trow.addView(unit);

            TextView price = new TextView(this);
            price.setText(f.getPrice());
            price.setLayoutParams(paramsRight);
            trow.addView(price);

            cartDisplay.addView(trow);
            counter++;
            subtotal = subtotal + f.getPriceValue();
            rows.add(trow);
        }

        String totalPrice = "$" + subtotal;
        if (subtotal * 10 % 1 != 0) {
            totalPrice = totalPrice + "0";
        }
        String finalTotalPrice = totalPrice;
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
                    intent.putExtra(TABLE_KEY, rows);
                    intent.putExtra(TOTAL_KEY, finalTotalPrice);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(PaymentActivity.this, R.string.choose_payment, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
