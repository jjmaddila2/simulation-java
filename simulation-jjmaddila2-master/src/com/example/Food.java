package com.example;

public class Food {
    private String name;
    private double basePrice;
    private int lowerPop;
    private int higherPop;
    private int quantity;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return basePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void changeQuantity(int change) {
        this.quantity = quantity + change;
    }

    public void subtractQuantity(int change) {
        this.quantity  = quantity - change;
    }

}
