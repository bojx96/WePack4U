package com.example.wepack4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView recycler = findViewById(R.id.cart_recycler_a);
        CartRecycler adapter = new CartRecycler(PaymentActivity.this, stores, new int[0], carts, true);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        TextView total = findViewById(R.id.total);
        total.setText(new TotalPrice(carts).getTotal());

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
