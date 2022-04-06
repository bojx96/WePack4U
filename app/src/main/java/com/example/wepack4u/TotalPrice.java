package com.example.wepack4u;

import java.util.List;

public class TotalPrice {
    private final List<FoodItem> cart;

    public TotalPrice(List<FoodItem> cart) { this.cart = cart; }

    public String getTotal() {
        double subtotal = 0f;

        for (FoodItem f : cart) { subtotal = subtotal + f.getPriceValue(); }

        String totalPrice = "$" + subtotal;
        if (subtotal * 10 % 1 == 0) { totalPrice = totalPrice + "0"; }
        return totalPrice;
    }
}
