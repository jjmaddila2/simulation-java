package com.example;

import java.util.ArrayList;

public class Market {

    private static final int OUT_COMMA = 2;

    private ArrayList<Food> food = new ArrayList<Food>();
    private ArrayList<Equipment> equipment = new ArrayList<Equipment>();
    private ArrayList<Recipe> recipe = new ArrayList<Recipe>();

    public ArrayList<Food> getFood() {
        return food;
    }

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    public ArrayList<Recipe> getRecipe() {
        return recipe;
    }

    /**
     *
     * @param item the item people want to print out the info for
     */
    public void MarketInfo(String item) {
        //prints if it food
        for (int i = 0; i < this.food.size(); i++) {
            if (food.get(i).getName().equalsIgnoreCase(item)) {
                System.out.println(printFoodItem(food.get(i)));
            }
        }
        //prints if it is recipe
        for (int i  = 0; i < this.recipe.size(); i++) {
            if (recipe.get(i).getName().equalsIgnoreCase(item)) {
                System.out.println(printRecipeItem(recipe.get(i)));
            }
        }
        //prints if it is equipment
        for (int i  = 0; i < this.equipment.size(); i++) {
            if (equipment.get(i).getName().equalsIgnoreCase(item)) {
                System.out.println(printEquipmentItems(equipment.get(i)));
            }
        }
    }

    /**
     *
     * @param food the food that the user wants to print
     * @return a string of all the food atributes
     */
    public String printFoodItem(Food food) {
        return "Name: " + food.getName() + "\n"
                + "Base price: " + food.getPrice() + "\n"
                + "Quantity owned: " + food.getQuantity();
    }

    /**
     *
     * @param recipe the recipe that the user wants to print
     * @return a string of all the recipe atributes
     */
    private String printRecipeItem(Recipe recipe) {
        return "Name: " + recipe.getName() + "\n"
                + "Base price: " + recipe.getPrice() + "\n"
                + "Time to prepare: " + recipe.getTime() + "\n"
                + "Food needed " + recipe.printFood() + "\n"
                + "Equipment needed " + recipe.printEquipment() + "\n"
                + "Quantity made: " + recipe.getQuantity();
    }

    /**
     *
     * @param equipment the equipement that the user wants to print
     * @return a string of all the equipment atributes
     */
    public String printEquipmentItems(Equipment equipment) {
        return "Name: " + equipment.getName() + "\n"
                + "Base Price: " + equipment.getPrice() + "\n"
                + "Upkeep Price: " + equipment.getUpkeep();
    }

    /**
     *
     * @param item the item you want to find in the market
     * @return the food if found
     */
    public Food findFoodInMarket(String item) {
        for (int i = 0; i < this.food.size(); i++) {
            if (food.get(i).getName().equalsIgnoreCase(item)) {
                return food.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param item the item you want to find in the market
     * @return the equipment if found
     */
    public Equipment findEquipmentInMarket(String item) {
        for (int i = 0; i < this.equipment.size(); i++) {
            if (equipment.get(i).getName().equalsIgnoreCase(item)) {
                return equipment.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param item the item you want to find in the market
     * @return the recipe if found
     */
    public Recipe findRecipeInMarket(String item) {
        for (int i = 0; i < this.recipe.size(); i++) {
            if (recipe.get(i).getName().equalsIgnoreCase(item)) {
                return recipe.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @return a string containing all of the food in the market
     */
    public String printFoodInMarket() {
        String foodInInventory = "Food: ";
        for (int i = 0; i < this.food.size(); i++) {
            foodInInventory += food.get(i).getName() + ", ";
        }
        foodInInventory = foodInInventory.substring(0, foodInInventory.length() - OUT_COMMA);
        return foodInInventory;
    }

    /**
     *
     * @return a string contining all of the equipment in the market
     */
    public String printEquipmentInMarket() {
        String equipmentInInventory = "Equipment: ";
        for (int i = 0; i < this.equipment.size(); i++) {
            equipmentInInventory += equipment.get(i).getName() + ", ";
        }
        equipmentInInventory = equipmentInInventory.substring(0, equipmentInInventory.length() - OUT_COMMA);
        return equipmentInInventory;
    }

    /**
     *
     * @return a string containing all of the recipie in the market
     */
    public String printRecipeInMarket() {
        String recipeInInventory = "Recipes: ";
        for (int i = 0; i < this.recipe.size(); i++) {
            recipeInInventory += recipe.get(i).getName() + ", ";
        }
        recipeInInventory = recipeInInventory.substring(0, recipeInInventory.length() - OUT_COMMA);
        return recipeInInventory;
    }
}
