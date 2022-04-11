package com.example.wepack4u.utilities;

import com.example.wepack4u.utilities.FoodItem;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class TotalPrice {
    private final List<FoodItem> cart;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public TotalPrice(List<FoodItem> cart) { this.cart = cart; }

    public String getTotal() {
        BigDecimal subtotal = new BigDecimal(0);

        for (FoodItem f : cart) { subtotal = subtotal.add(f.getPriceValue()); }

        return "$" + subtotal.setScale(2, BigDecimal.ROUND_UP).toString();

        //String totalPrice = "$" + df.format(subtotal);
        //if (subtotal * 10 % 1 == 0) { totalPrice = totalPrice; }
        //return totalPrice;
    }
}
