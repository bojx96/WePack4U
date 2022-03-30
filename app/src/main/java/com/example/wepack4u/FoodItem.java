package com.example.wepack4u;

public class FoodItem {
    private String name;
    private int unit;
    private double price;

    FoodItem(String name, int unit, double price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getUnit() {
        return ""+this.unit;
    }

    public String getPrice() {
        double subtotal = price * unit;
        return "$"+subtotal;
    }

    public double getPriceValue() {
        return price * unit;
    }
}
