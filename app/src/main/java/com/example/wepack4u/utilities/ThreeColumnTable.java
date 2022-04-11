package com.example.wepack4u.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.example.wepack4u.R;
import com.example.wepack4u.utilities.FoodItem;

import java.util.List;

public class ThreeColumnTable {
    private final TableLayout t1;
    private final TableLayout t2;
    private final TableLayout t3;
    private final List<FoodItem> cart;
    private final String stall;
    private final Context context;
    private final Typeface typeface;

    public ThreeColumnTable(TableLayout t1, TableLayout t2, TableLayout t3, List<FoodItem> cart,
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

        for (FoodItem f : cart) {
            if (f.getStall().equals(this.stall)) {
                createRow(f.getName(), t1, Gravity.START);

                createRow(f.getUnit(), t2, Gravity.CENTER_HORIZONTAL);

                String priceValue = f.getPrice();
                createRow(priceValue, t3, Gravity.END);
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
