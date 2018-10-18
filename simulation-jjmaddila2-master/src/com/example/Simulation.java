package com.example;
import com.google.gson.Gson;
import java.util.*;
import java.text.*;

public class Simulation {

    private static final int SECOND_WORD = 2;
    private static final int THIRD_WORD = 3;
    private static final int MINUTES = 60;
    /**
     * simply the msin
     * @param args something
     */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.startSimulation();
    }

    /**
     *
     * @return a parsed version of the JSON as a part of the layout class
     */
    public Layout layoutJson() {
        //creation of a new gson object
        Gson adventure = new Gson();
        String jsonToString = Data.getFileContentsAsString("restaurant.json");
        Layout jsonLayoutObject = adventure.fromJson(jsonToString, Layout.class);
        //after turning the json to a string the string is printed
        return jsonLayoutObject;
    }

    /**
     * the first part of the game handles all commands not in market
     */
    public void startSimulation() {
        //so i csn acess the JSON whenever I need to
        Layout layout = layoutJson();
        //while loop will only end once someone types exit
        //also a number format to make the money have better format
        NumberFormat format = NumberFormat.getCurrencyInstance();
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("What action do you want to do?");
            //getting user input
            String input = keyboard.nextLine();
            System.out.println("");
            //splits the user input into a string and then converts into an arraylist
            String[] wordsOfInput = input.split(" ");
            ArrayList<String> words = new ArrayList<String>(Arrays.asList(wordsOfInput));
            //if first word is quit or exit program exits
            if (words.get(0).equalsIgnoreCase("quit") ||
                    words.get(0).equalsIgnoreCase("exit")) {
                System.exit(0);
            }
            //if wealth will call method to print out current wealth
            else if (words.get(0).equalsIgnoreCase("wealth")) {
                System.out.println("You have " + format.format(layout.getMoney()));
            }
            //method to pass however time the user wants to
            else if (words.get(0).equalsIgnoreCase("pass")) {
                //if size is 3 words then only method can run
                if (words.size() >= THIRD_WORD) {
                    int minutesToPass = Integer.parseInt(words.get(SECOND_WORD));
                    layout.passTime(minutesToPass);
                }
                //otherwise the method cannot run
                else {
                    System.out.println("Incorrect input, next time input: pass time <amount> if you want to pass time");
                }
            }
            //if first word is time then simply it shows the time
            else if (words.get(0).equalsIgnoreCase("time")) {
                System.out.println("Days passed: " +layout.getDays());
                System.out.println("Hours passed: " + layout.getHours());
            }
            //command that prints inventory
            else if (words.get(0).equalsIgnoreCase("inventory")) {
                //if a type is used than use that to print out desired thing
                if (words.size() >= SECOND_WORD) {
                    layout.getMenu().printInventory(words.get(1));
                }
                //if no second word is included than whole inventory is printed
                else {
                    layout.getMenu().printInventory("all");
                }
            }
            //command to print a specific info
            else if (words.get(0).equalsIgnoreCase("info")) {
                //second word describes what to find info on
                if (words.size() >= SECOND_WORD) {
                    layout.getMarket().MarketInfo(words.get(1));
                }
                //no second word than error message
                else {
                    System.out.println("Invalid input. You need to include an item");
                }
            }
            //command that will enter the market
            else if (words.get(0).equalsIgnoreCase("market")) {
                enterMarket(layout);
            }
            //commands that will enter other commands with the menu
            else if (words.get(0).equalsIgnoreCase("menu")) {
                //need a second word so no second word than incorrect input
                if (words.size() == 1) {
                    System.out.println("Incorrect input");
                }
                //if second word is list than menu list will be printed
                else if (words.get(1).equalsIgnoreCase("list")) {
                    System.out.println(layout.getMenu().printMenu());
                }
                //if amount of words is over 3 than you can have add and remove commands
                else if (words.size() >= THIRD_WORD) {
                    //add to menu command
                    if (words.get(1).equalsIgnoreCase("add")) {
                        layout.getMenu().addToMenu(words.get(SECOND_WORD));
                    }
                    //remove to menu command
                    else if (words.get(1).equalsIgnoreCase("remove")) {
                        layout.getMenu().removeFromMenu(words.get(SECOND_WORD));
                    }
                }
            }
            //command to cook food
            else if (words.get(0).equalsIgnoreCase("cook")) {
                //needs a second word or else incorrect input
                if (words.size() == 1) {
                    System.out.println("Incorrect input");
                }
                //second word represents the type and quantity is zero
                else if (words.size() == SECOND_WORD) {
                    layout.getMenu().cookRecipe(words.get(1), 1, layout);
                }
                //third word is quantity
                else {
                    int quantity = Integer.parseInt(words.get(SECOND_WORD));
                    layout.getMenu().cookRecipe(words.get(1), quantity, layout);
                }
            }
        }
    }

    /**
     *
     * @param layout the Json to access other stuff
     */
    public void enterMarket(Layout layout) {
        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("WELCOME TO THE MARKET");
        System.out.println("---------------------------");
        System.out.println("");
        //loop that will run until someone types exit
        while (true) {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("What action do you want to do?");
            String input = keyboard.nextLine();
            System.out.println("");
            //gets user input and all words are set as an array of words
            String[] wordsOfInput = input.split(" ");
            //if person types exit then it will exit out of the market
            if (wordsOfInput[0].equalsIgnoreCase("exit")) {
                //also once it adds an hour to the time
                layout.passTime(MINUTES);
                return;
            }
            //first command if you want to buy from the market
            else if (wordsOfInput[0].equalsIgnoreCase("buy")) {
                //need a second word, if no second word then invalid input
                if (wordsOfInput.length == 1) {
                    System.out.println("Invalid input");
                }
                //checks to see if second word is in food, recipe, or equipment
                Food itemOfFood = layout.foodInMarket(wordsOfInput[1]);
                Equipment equipmentItem = layout.equipmentInMarket(wordsOfInput[1]);
                Recipe recipeItem = layout.recipeInMarket(wordsOfInput[1]);
                //if it is in food than it will do stuff
                if (itemOfFood != null) {
                    //if no quantity is specified and you have enough money then you will buy one of the object
                    if (wordsOfInput.length == SECOND_WORD && layout.getMoney() >= itemOfFood.getPrice()) {
                        layout.getMenu().addFoodToInventory(1, itemOfFood);
                        layout.subtractMoney(itemOfFood.getQuantity());
                    }
                    //if quantity is not specified than you will buy the amount that you want
                    //if you have enough money
                    else if (wordsOfInput.length > SECOND_WORD) {
                        int quantity = Integer.parseInt(wordsOfInput[SECOND_WORD]);
                        if (layout.getMoney() >= itemOfFood.getPrice() * quantity) {
                            layout.getMenu().addFoodToInventory(quantity, itemOfFood);
                            layout.subtractMoney(itemOfFood.getPrice() * quantity);
                        }
                    }
                    //if you don't have enough money then error message is shown
                    else {
                        System.out.println("You don't have the money to buy the good");
                    }
                }
                //if not a food then it will check if the person wants to buy some equipment
                else if (equipmentItem != null) {
                    //if you have enough money to buy the equipment then you buy the piece of equipment
                    if (layout.getMoney() >= equipmentItem.getPrice()) {
                        layout.getMenu().addEquipmentToInventory(equipmentItem);
                        layout.subtractMoney(equipmentItem.getPrice());
                    }
                    //don't have enough money then error message comes up
                    else {
                        System.out.println("You don't have the money to buy the good");
                    }
                }
                //if not food or equipment than it could be a recipe
                else if (recipeItem != null) {
                    //if you have enough money to buy the recipe than you will buy the recipe
                    if (layout.getMoney() >= recipeItem.getPrice()) {
                        layout.getMenu().addRecipeToInventory(recipeItem);
                        layout.subtractMoney(recipeItem.getPrice());
                    }
                    //don't have enough money than error message comes up
                    else {
                        System.out.println("You don't have the money to buy the good");
                    }

                }
                //if the item that you want to buy is not in market than error message comes in
                else {
                    System.out.println("Sorry the item you are looking for is not in the market");
                }
            }
            // prints a list of all the items of a given type in the market
            else if (wordsOfInput[0].equalsIgnoreCase("list")) {
                //if only word than no type is there and error message
                if (wordsOfInput.length == 1) {
                    System.out.println("Invalid input");
                }
                //next three if statements it someone types in  atype all
                else if (wordsOfInput[1].equalsIgnoreCase("food")) {
                    System.out.println(layout.getMarket().printFoodInMarket());
                }
                else if (wordsOfInput[1].equalsIgnoreCase("equipment")) {
                    System.out.println(layout.getMarket().printEquipmentInMarket());
                }
                else if (wordsOfInput[1].equalsIgnoreCase("recipe")) {
                    System.out.println(layout.getMarket().printRecipeInMarket());
                }
                //wrong type = error message
                else {
                    System.out.println("I can only print recipes, foods, and equipment");
                }
            }
            //command that sells from inventory to market
            else if (wordsOfInput[0].equalsIgnoreCase("sell")) {
                //one word than nothing specfied to sell
                if (wordsOfInput.length == 1) {
                    System.out.println("Invalid input");
                }
                //if only word than sells only one of the object
                else if (wordsOfInput.length == SECOND_WORD) {
                    layout.getMenu().sellItem(wordsOfInput[1], 1, layout);
                }
                //if quantity is third word than sells the amount the user put in the quantity.
                else {
                    int quantity = Integer.parseInt(wordsOfInput[SECOND_WORD]);
                    layout.getMenu().sellItem(wordsOfInput[1], quantity, layout);
                }

            }
        }
    }
}
