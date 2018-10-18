package com.example;

import java.util.ArrayList;
import java.text.*;

public class Menu {
    private ArrayList<Food> food;
    private ArrayList<Recipe> recipe;
    private ArrayList<Equipment> equipment;
    private ArrayList<Food> foodOnMenu = new ArrayList<Food>();
    private ArrayList<Recipe> recipeOnMenu = new ArrayList<Recipe>();

    private static final int OUT_COMMA = 2;
    private static final double MULTIPLIER = 1.5;
    private static final double SELL_PRICE = 0.8;
    private static final int MINUTES = 60;

    public ArrayList<Equipment> getEquipment() {
        return equipment;
    }

    /**
     *
     * @return an arraylist of food on the menu
     */
    public ArrayList<Food> getFoodOnMenu() {
        return foodOnMenu;
    }

    /**
     *
     * @return an arraylist of of recipes on the menu
     */
    public ArrayList<Recipe> getRecipeOnMenu() {
        return recipeOnMenu;
    }

    /**
     *
     * @return an arraylist of the inventory of food
     */
    public ArrayList<Food> getFood() {
        return food;
    }

    /**
     *
     * @return an arraylist of the recipes in inventory
     */
    public ArrayList<Recipe> getRecipe() {
        return recipe;
    }

    /**
     *
     * @param type the type (equipment, food, recipe) that the person wants to print out
     */
    public void printInventory(String type) {
        System.out.println("");
        //if user wants to print out the entire inventory than it does so
        if (type.equalsIgnoreCase("all")) {
            System.out.println("Here is your inventory:");
            System.out.println(printFoodInInventory());
            System.out.println(printRecipeInInventory());
            System.out.println(printEquipmentInInventory());
        }
        //if the user wants to print out something else it prints the correct type
        else if (type.equalsIgnoreCase("equipment")) {
            System.out.println("Your equipment: " + printEquipmentInInventory());
        }
        else if (type.equalsIgnoreCase("food")) {
            System.out.println("Your food: " + printFoodInInventory());
        }
        else if (type.equalsIgnoreCase("recipe")) {
            System.out.println("Your recipes: " + printRecipeInInventory());
        }
    }

    /**
     *
     * @return the food in the inventory in  String
     */
    public String printFoodInInventory() {
        //string that will be returned
        String foodInInventory = "Food: ";
        //goes through all the food in the inventory and adds all names to the string
        for (int i = 0; i < this.food.size(); i++) {
            foodInInventory += food.get(i).getName() + ", ";
        }
        foodInInventory = foodInInventory.substring(0, foodInInventory.length() - OUT_COMMA);
        //returns the string
        return foodInInventory;
    }

    /**
     *
     * @return the recipe in the inventory in a String
     */
    public String printRecipeInInventory() {
        //string that will be returned
        String recipeInInventory = "Recipes: ";
        //goes through all the recipe in the inventory and adds all names to the string
        for (int i = 0; i < this.recipe.size(); i++) {
            recipeInInventory += recipe.get(i).getName() + ", ";
        }
        recipeInInventory = recipeInInventory.substring(0, recipeInInventory.length() - OUT_COMMA);
        //returns the string
        return recipeInInventory;
    }

    /**
     *
     * @return the equipment in the inventory in a String
     */
    public String printEquipmentInInventory() {
        //string that will be returned
        String equipmentInInventory = "Equipment: ";
        //goes through all the equipment in the inventory and adds all names to the string
        for (int i = 0; i < this.equipment.size(); i++) {
            equipmentInInventory += equipment.get(i).getName() + ", ";
        }
        equipmentInInventory = equipmentInInventory.substring(0, equipmentInInventory.length() - OUT_COMMA);
        //returns the string
        return equipmentInInventory;
    }

    /**
     *
     * @param quantity the amount of food the person wants to buy
     * @param foodToAdd the food the person wants to buy
     */
    public void addFoodToInventory(int quantity, Food foodToAdd) {
        //if food is already owned than it just changes the quantity
        if (isFoodOwned(foodToAdd.getName())) {
            foodToAdd.changeQuantity(quantity);
        }
        //if food is not owned than adds it to inventory
        else {
            foodToAdd.changeQuantity(quantity);
            food.add(foodToAdd);
        }
    }

    /**
     *
     * @param equipmentToAdd the equipment the person wants to add
     */
    public void addEquipmentToInventory(Equipment equipmentToAdd) {
        //adds the equipment to the inventory
        equipment.add(equipmentToAdd);
    }

    /**
     *
     * @param recipeToAdd the recipe the person wants to add
     */
    public void addRecipeToInventory(Recipe recipeToAdd) {
        //adds the recipe to the inventory
        recipe.add(recipeToAdd);
    }

    /**
     *
     * @param foodName the string name of the food
     * @return a boolean if the food is already owned
     */
    public boolean isFoodOwned(String foodName) {
        //goes through all the foods in the inventory and sees if the food is already
        //in the inventory
        for (int i  = 0; i < food.size(); i++) {
            if (food.get(i).getName().equalsIgnoreCase(foodName)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return a string of the menu
     */
    public String printMenu() {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        //string that will end up being returned
        String menu = "";
        menu += "Menu:" + "\n";
        //adds all the food items on the menu while printing out the price
        if (foodOnMenu.size() >0) {
            for (int i = 0; i < foodOnMenu.size(); i++) {
                menu += foodOnMenu.get(i).getName() + "\t" + format.format(foodOnMenu.get(i).getPrice() * MULTIPLIER) + "\n";
            }
        }
        //adds all the recipe on the menu while printing out the price
        if (recipeOnMenu.size() > 0) {
            for (int i = 0; i < recipeOnMenu.size(); i++) {
                menu += recipeOnMenu.get(i).getName() + "\t" + format.format(priceFromRecipe(i) * MULTIPLIER) + "\n";
            }
        }
        //returns the menu
        return menu;
    }

    /**
     *
     * @param index the index of the recipe from the menu
     * @return the base selling price or recipe
     */
    public double priceFromRecipe(int index) {
        double total = 0;
        //makes it easier to access the recipe
        Recipe desiredRecipe = recipeOnMenu.get(index);
        //goes through food strings of the menu and adds the price of the foods
        for (int i = 0; i < desiredRecipe.getFood().length; i++) {
            for (int j = 0; j < food.size(); j++) {
                if (desiredRecipe.getFood()[i].equalsIgnoreCase(food.get(j).getName())) {
                    total += food.get(j).getPrice();
                }
            }
        }
        //returns the total price for a single recipe
        return total;
    }

    /**
     *
     * @param item the item that you want to sell
     * @param quantity the amount of the item that you want to sell
     * @param layout layout oubject so I can access the Json
     */
    public void sellItem(String item, int quantity, Layout layout) {
        //will be used to find the indexes of food, equipment, and recipe to see it
        int indexOfFood = -1;
        int indexOfEquipment = -1;
        int indexOfRecipe = -1;
        //goes through all the food
        for (int i  = 0; i < food.size(); i++) {
            //if food equals the new item than subtracts the quantity of the new item.
            if (food.get(i).getName().equalsIgnoreCase(item) && food.get(i).getQuantity() >= quantity) {
                food.get(i).subtractQuantity(quantity);
                //also adds money by .8 times the base cost
                layout.addMoney(food.get(i).getPrice() * quantity * SELL_PRICE);
                //change indexOfFood to i if quanityt is zero
                if (food.get(i).getQuantity() == 0) {
                    indexOfFood = i;
                }
            }
        }
        //indexOfFood is not zero so i delete the food item
        if (indexOfFood != -1) {
            food.remove(indexOfFood);
        }
        //if equipment equals the new item than removes the equipment and adds the money
        for (int i = 0; i < equipment.size(); i++) {
            if (equipment.get(i).getName().equalsIgnoreCase(item)) {
                layout.addMoney(equipment.get(i).getPrice() * SELL_PRICE);
                indexOfEquipment = i;
            }
        }
        if (indexOfEquipment != -1) {
            equipment.remove(indexOfEquipment);
        }
        //if recipe equals the new itam than removes the recipes and adds the money
        for (int i = 0; i < recipe.size(); i++) {
            if (recipe.get(i).getName().equalsIgnoreCase(item)) {
                layout.addMoney(recipe.get(i).getPrice() * SELL_PRICE);
                indexOfRecipe = i;
            }
        }
        if (indexOfRecipe != -1) {
            recipe.remove(indexOfRecipe);
        }
    }

    /**
     *
     * @param item the item that is potentilly a food
     * @return true is item is in inventory
     */
    public boolean isFoodInInventory(String item) {
        //goes through all the foods and if found than return true
        for (int i  = 0; i < food.size(); i++) {
            if (item.equalsIgnoreCase(food.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param item the item that is potentilly a recipe
     * @return true is item is in inventory
     */
    public boolean isRecipeInInventory(String item) {
        //goes through all the recipes and if found than return true
        for (int i = 0; i < recipe.size(); i++) {
            if (item.equalsIgnoreCase(recipe.get(i).getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param  item the string the person wants to find in the food
     * @return an int of the index of that food string
     */
    public int getIndexOfFood(String item) {
        int index = -1;
        //goes through all the foods and when the item is equal to the name of the
        //finds the index
        for (int i  = 0; i < food.size(); i++) {
            if (item.equalsIgnoreCase(food.get(i).getName())) {
                index = i;
            }
        }
        //returns the index of that food item
        return index;
    }

    /**
     *
     * @param item the item that is a string
     * @return teh index of that string in the foodOnMenu
     */
    public int getIndexOfFoodMenu(String item) {
        int index = -1;
        //goes through the arraylist and see if any names equal a name on the foodOnMenu
        for (int i  = 0; i < foodOnMenu.size(); i++) {
            if (item.equalsIgnoreCase(foodOnMenu.get(i).getName())) {
                index = i;
            }
        }
        //returns the index of foodOnMenu
        return index;
    }

    /**
     * @param item a string that represents a name that could be a recipe
     * @return index of recipe if it is in the list
     */
    public int getIndexOfRecipe(String item) {
        int index = -1;
        //goes through the arraylist and sees if the names the item is equal to  recipe name
        for (int i = 0; i < recipe.size(); i++) {
            if (item.equalsIgnoreCase(recipe.get(i).getName())) {
                //if it is equal changes index to i
               index = i;
            }
        }
        //returns index of recipe
        return index;
    }

    /**
     * @param item a string that recipe that represents a name of something
     * @return index of recipe if it is in menu
     */
    public int getIndexOfRecipeMenu(String item) {
        int index = -1;
        //goe through the arraylist and sees if item is equal to recipe name in menu
        for (int i = 0; i < recipeOnMenu.size(); i++) {
            if (item.equalsIgnoreCase(recipeOnMenu.get(i).getName())) {
                index = i;
            }
        }
        //retursn the index
        return index;
    }

    /**
     *
     * @param index the index of the desired recipe
     * @return a check if all of the required attributes of the recipe is present
     */
    public boolean checkPartsInRecipes(int index) {
        Recipe desiredRecipe = recipe.get(index);
        //counter to check if equipment and recipe have the right amount of elements
        int counter = 0;
        int increment = 0;
        for (int i = 0; i < desiredRecipe.getFood().length; i++) {
            for (int j = 0; j < food.size(); j++) {
                if (desiredRecipe.getFood()[i].equalsIgnoreCase(food.get(j).getName())) {
                    counter++;
                }
            }
        }
        for (int i = 0; i < desiredRecipe.getEquipment().length; i++) {
            for (int j = 0; j < equipment.size(); j++) {
                if (desiredRecipe.getEquipment()[i].equalsIgnoreCase(equipment.get(j).getName())) {
                    increment++;
                }
            }
        }
        //if everything checks out and all parts of recipe are satisfied than return true
        if (counter == desiredRecipe.getFood().length && increment == desiredRecipe.getEquipment().length) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param index the index of the recipe of choice
     * @return a boolean if they have some quantity left
     */
    public boolean checkQuantityInRecipes(int index) {
        //the recipe of choice
        Recipe desiredRecipe = recipe.get(index);
        //goes through all the recipes and finds the correct one
        //if quantity of that recipe is above zero than returns true
        for (int i = 0; i < desiredRecipe.getFood().length; i++) {
            for (int j = 0; j < food.size(); j++) {
                if (desiredRecipe.getFood()[i].equalsIgnoreCase(food.get(j).getName())) {
                    if (food.get(j).getQuantity() <= 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     *
     * @param item the item to add to the menu
     */
    public void addToMenu(String item) {
        //if food in inventory than adds the item
        if (isFoodInInventory(item)) {
            foodOnMenu.add(food.get(getIndexOfFood(item)));
        }
        //if recipe in inventory than adds the item
        else if (isRecipeInInventory(item) && checkPartsInRecipes(getIndexOfRecipe(item))) {
            recipeOnMenu.add(recipe.get(getIndexOfRecipe(item)));
        }
    }

    /**
     * @param item the item that wants to be removed
     */
    public void removeFromMenu(String item) {
        int indexOfFood = getIndexOfFoodMenu(item);
        int indexOfRecipe = getIndexOfRecipeMenu(item);
        if (indexOfFood != -1) {
            foodOnMenu.remove(indexOfFood);
        }
        else if (indexOfRecipe != -1) {
            recipeOnMenu.remove(indexOfRecipe);
        }
    }

    /**
     *
     * @param item teh item you want to cook
     * @param quantity the amount of the item you want to sell
     * @param layout so I can access a JSON
     */
    public void cookRecipe(String item, int quantity, Layout layout){
        //gets the index of the proper recipe
        int indexOfRecipe = getIndexOfRecipe(item);
        Recipe desiredRecipe = recipe.get(indexOfRecipe);
        //for loop through quantity and cooks new recipe
        for (int i = 0; i < quantity; i++) {
            if (checkPartsInRecipes(indexOfRecipe) && checkQuantityInRecipes(indexOfRecipe)) {
                //increases quantity of recipe
                recipe.get(indexOfRecipe).addQuantity(1);
                //passes the time of recipe
                layout.passTime(recipe.get(indexOfRecipe).getTime() * MINUTES);
                //goes through all the foods and removes a quantity of one for each food item
                for (int j = 0; j < desiredRecipe.getFood().length; j++) {
                    int index = getIndexOfFood(desiredRecipe.getFood()[j]);
                    food.get(index).subtractQuantity(1);
                }
            }
        }
    }

    /**
     *
     * @return a double of all hte upkeep for a equipment
     */
    public double getUpkeep() {
        double upkeep = 0;
        //goes through all the equipment and adds up all the equipment
        for (int i = 0; i < equipment.size(); i++) {
            upkeep += equipment.get(i).getUpkeep();
        }
        //returns the upkeep
        return upkeep;
    }


}
