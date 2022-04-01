package com.example.wepack4u;

import android.content.Context;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumnTable {
    private final TableLayout t1;
    private final TableLayout t2;
    private final TableLayout t3;
    private final ArrayList<FoodItem> cart;
    private final Context context;
    private TableRow.LayoutParams paramsLeft;
    private TableRow.LayoutParams paramsMid;
    private TableRow.LayoutParams paramsRight;
    private double subtotal;

    ThreeColumnTable(TableLayout t1, TableLayout t2, TableLayout t3, ArrayList<FoodItem> cart,
                     Context context) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.cart = cart;
        this.context = context;
    }

    private void setParams() {
        paramsLeft = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        paramsMid = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        paramsMid.gravity = Gravity.CENTER;
        paramsRight = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        paramsRight.gravity = Gravity.END;
    }

    public void createTable() {
        setParams();
        int counter = 1;
        subtotal = 0.0f;

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
            String text = counter + ".   " + f.getName();
            createRow(text, paramsLeft, t1);

            createRow(f.getUnit(), paramsMid, t2);

            String priceValue = f.getPrice();
            if (f.getPriceValue() * 10 % 1 == 0) { priceValue = priceValue + "0"; }
            createRow(priceValue, paramsRight, t3);

            counter++;
            subtotal = subtotal + f.getPriceValue();
        }
    }

    private void createRow(String text, TableRow.LayoutParams params, TableLayout table) {
        TableRow trow = new TableRow(context);
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setLayoutParams(params);
        trow.addView(textView);
        table.addView(trow);
    }

    public String getTotalPrice() {
        String totalPrice = "$" + subtotal;
        if (subtotal * 10 % 1 == 0) { totalPrice = totalPrice + "0"; }
        return totalPrice;
    }
}
