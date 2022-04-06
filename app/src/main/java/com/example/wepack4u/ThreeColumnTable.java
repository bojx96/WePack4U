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
import java.util.List;

public class ThreeColumnTable {
    private final TableLayout t1;
    private final TableLayout t2;
    private final TableLayout t3;
    private final List<FoodItem> cart;
    private final String stall;
    private final Context context;
    private final Typeface typeface;

    ThreeColumnTable(TableLayout t1, TableLayout t2, TableLayout t3, List<FoodItem> cart,
                     String stall, Context context) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.cart = cart;
        this.stall = stall;
        this.context = context;
        typeface = ResourcesCompat.getFont(context, R.font.montserrat_medium);
    }

    public void createTable() {
        int counter = 1;

        for (FoodItem f : cart) {
            if (f.getStall().equals(this.stall)) {
                String text = counter + ".   " + f.getName();
                createRow(text, t1, Gravity.START);

                createRow(f.getUnit(), t2, Gravity.CENTER_HORIZONTAL);

                String priceValue = f.getPrice();
                if (f.getPriceValue() * 10 % 1 == 0) {
                    priceValue = priceValue + "0";
                }
                createRow(priceValue, t3, Gravity.END);

                counter++;
            }
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
}
