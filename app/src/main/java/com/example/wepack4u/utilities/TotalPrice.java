package com.example.wepack4u.utilities;

import com.example.wepack4u.utilities.FoodItem;

import java.text.DecimalFormat;
import java.util.List;

public class TotalPrice {
    private final List<FoodItem> cart;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public TotalPrice(List<FoodItem> cart) { this.cart = cart; }

    public String getTotal() {
        double subtotal = 0f;

        for (FoodItem f : cart) { subtotal = subtotal + f.getPriceValue(); }

        String totalPrice = "$" + df.format(subtotal);
        if (subtotal * 10 % 1 == 0) { totalPrice = totalPrice; }
        return totalPrice;
    }
}
