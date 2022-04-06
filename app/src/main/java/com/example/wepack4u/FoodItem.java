package com.example.wepack4u;

public class FoodItem {
    private String name;
    private int unit;
    private double price;
    private String stall;

    // No-arg constructor needed for Firebase to work
    FoodItem() {}

    FoodItem(String name, int unit, double price, String stall) {
        this.name = name;
        this.unit = unit;
        this.price = price;
        this.stall = stall;
    }

    public String getName() { return this.name; }

    public String getUnit() { return ""+this.unit; }

    public String getPrice() {
        double subtotal = price * unit;
        return "$"+subtotal;
    }

    public double getPriceValue() { return this.price * this.unit; }

    public String getStall() { return this.stall; }
}
