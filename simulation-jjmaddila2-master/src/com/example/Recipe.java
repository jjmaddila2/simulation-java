package com.example;

public class Recipe {
    private String name;
    private double price;
    private double time;
    private String[] food;
    private String[] equipment;
    private int lowerPop;
    private int higherPop;
    private int quantity;

    /**
     *
     * @return name of recipe
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return price of recipe
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @return time to take to make recipe
     */
    public double getTime() {
        return time;
    }

    /**
     *
     * @return the array of foods needed for a recipe
     */
    public String[] getFood() {
        return food;
    }

    /**
     *
     * @return the array of equipment for a recipe
     */
    public String[] getEquipment() {
        return equipment;
    }

    /**
     * @return the quantity of recipe made
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * increase quantity
     * @param quantity the quantity to be added to quantity
     */
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }

    /**
     * decrease quantity
     * @param quantity the quantity to be decreased
     */
    public void decreaseQuantity(int quantity) {
        this.quantity -= quantity;
    }

    /**
     *
     * @return a string of the food
     */
    public String printFood() {
        String foodNames = "";
        //goes through the food list and adds the food to the string
        for (int i = 0; i < food.length; i++) {
            foodNames += food[i] + ", ";
        }
        foodNames = foodNames.substring(0, foodNames.length() - 2);
        return foodNames;
    }

    /**
     *
     * @return a string of the equipment
     */
    public String printEquipment() {
        String equipmentNames = "";
        //goes through all the equipment
        for (int i = 0; i < equipment.length; i++) {
            equipmentNames += equipment[i] + ", ";
        }
        equipmentNames = equipmentNames.substring(0, equipmentNames.length() - 2);
        return equipmentNames;
    }
}
