package com.example.wepack4u;

import java.util.ArrayList;

public class TotalPrice {
    private final ArrayList<ArrayList<FoodItem>> carts;

    public TotalPrice(ArrayList<ArrayList<FoodItem>> carts) {
        this.carts = carts;
    }

    public String getTotal() {
        double subtotal = 0f;

        for (ArrayList<FoodItem> c : carts) {
            for (FoodItem f : c) {
                subtotal = subtotal + f.getPriceValue();
            }
        }

        String totalPrice = "$" + subtotal;
        if (subtotal * 10 % 1 == 0) { totalPrice = totalPrice + "0"; }
        return totalPrice;
    }
}
