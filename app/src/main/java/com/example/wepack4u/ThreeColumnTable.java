package com.example.wepack4u;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

public class ThreeColumnTable {
    private final TableLayout t1;
    private final TableLayout t2;
    private final TableLayout t3;
    private final ArrayList<FoodItem> cart;
    private final Context context;
    private final Typeface typeface;
    private double subtotal;

    ThreeColumnTable(TableLayout t1, TableLayout t2, TableLayout t3, ArrayList<FoodItem> cart,
                     Context context) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.cart = cart;
        this.context = context;
        typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium);
    }

    public void createTable() {
        int counter = 1;
        subtotal = 0.0f;

        // for testing
        cart.add(new FoodItem("Beef Ramen", 2, 4.70));
        cart.add(new FoodItem("Aglio Olio", 1, 4.50));
        cart.add(new FoodItem("Curry Katsu Don", 3, 4.50));
        cart.add(new FoodItem("A", 1, 4.50));

        for (FoodItem f : cart) {
            String text = counter + ".   " + f.getName();
            createRow(text, t1, Gravity.START);

            createRow(f.getUnit(), t2, Gravity.CENTER_HORIZONTAL);

            String priceValue = f.getPrice();
            if (f.getPriceValue() * 10 % 1 == 0) { priceValue = priceValue + "0"; }
            createRow(priceValue, t3, Gravity.END);

            counter++;
            subtotal = subtotal + f.getPriceValue();
        }
    }

    private void createRow(String text, TableLayout table, int alignment) {
        TableRow trow = new TableRow(context);
        TextView textView = new TextView(context);

        textView.setText(text);
        textView.setTypeface(typeface);
        textView.setGravity(alignment);

        trow.addView(textView);
        table.addView(trow);
    }

    public String getTotalPrice() {
        String totalPrice = "$" + subtotal;
        if (subtotal * 10 % 1 == 0) { totalPrice = totalPrice + "0"; }
        return totalPrice;
    }
}
