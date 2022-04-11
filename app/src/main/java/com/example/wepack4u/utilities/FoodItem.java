package com.example.wepack4u.utilities;

import java.math.BigDecimal;

public class FoodItem {
    private String name;
    private int unit;
    private String price;
    private String stall;

    // No-arg constructor needed for Firebase to work
    FoodItem() {}

    FoodItem(String name, int unit, String price, String stall) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.stall = stall;
    }

    public String getName() { return this.name; }

    public String getUnit() { return ""+this.unit; }

    public String getPrice() {
        BigDecimal subtotal = new BigDecimal(price).multiply(new BigDecimal(unit));
        return "$" + subtotal.setScale(2, BigDecimal.ROUND_UP).toString();
    }

    public BigDecimal getPriceValue() { return new BigDecimal(price).multiply(new BigDecimal(unit)); }

    public String getStall() { return this.stall; }
}
