package com.example;

import java.util.ArrayList;

public class Layout {
    public static final int MULTIPLIER_RUSH = 5;
    public static final int ADD_RUSH = 3;
    public static final int MULTIPLIER_NORMAL = 4;
    public static final int MULTIPLIER_TYPE = 10;
    public static final int THRESHOLD = 6;
    public static final double MULTIPLIER = 1.5;
    private String name;
    private double money;
    private double hours;
    private int days;
    private Market market;
    private Menu menu;

    private static final double MINUTES = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final int STRATING_TIME = 6;
    private static final int ENDING_TIME = 22;
    private static final int FIRST_RUSH = 8;
    private static final int SECOND_RUSH = 13;
    private static final int THIRD_RUSH = 19;


    /**
     *
     * @return the money of the user
     */
    public double getMoney() {
        return money;
    }

    /**
     *
     * @param amount the amount to subtract from the total money
     */
    public void subtractMoney(double amount) {
        money -= amount;
    }

    /**
     *
     * @param amount the amount of money to add to total money
     */
    public void addMoney(double amount) {
        money += amount;
    }

    /**
     *
     * @return the hours
     */
    public double getHours() {
        return hours;
    }

    /**
     *
     * @param time add time on to hours basically updates time
     */
    public void addHours (double time) {
        this.hours = time + hours;
    }

    /**
     * resets the hours so if it is abouve 24 than rests back to 0 and adds a day
     */
    public void resetHours() {
        //keeps going until hours is below 24
        while (this.hours >= HOURS_IN_DAY) {
            subtractMoney(getMenu().getUpkeep());
            double remainder = this.hours - HOURS_IN_DAY;
            this.hours = 0 + remainder;
            addDays();
        }
    }

    /**
     *
     * @return total amont of  days passed
     */
    public int getDays() {
        return days;
    }

    /**
     * adds days
     */
    public void addDays() {
        this.days++;
    }

    /**
     *
     * @return the market
     */
    public Market getMarket() {
        return market;
    }

    /**
     *
     * @return menu object so it can accessed later
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     *
     * @param minutes the amount of time in minutes the user wants to pass
     */
    public void passTime(double minutes) {
        double startingTime = getHours();
        //converts into hours
        double minToHours = minutes / MINUTES;
        //adds time
        addHours(minToHours);
        double endingHours = getHours();
        //start of creating customers
        createStore(startingTime, endingHours);
        //if hours too high calls restHours
        if (hours >= HOURS_IN_DAY) {
            resetHours();
        }
    }

    /**
     *
     * @param startingTime the time before the person passed time
     * @param endingTime the time after the person passed time
     */
    public void createStore(double startingTime, double endingTime) {
        //this creates an arraylist of all the time that is passed between starting and ending time
        ArrayList<Integer> hours = arrayListOfTimes(startingTime, endingTime);
        //if hours is null then not enough time passed for customers
        if (hours == null) {
            return;
        }
        //goe through all the hours and if the hour is in store opperating times then it will call generate customers
        for (int i = 0; i < hours.size(); i++) {
            System.out.println("Hour: " + hours.get(i));
            if (hours.get(i) >= STRATING_TIME && hours.get(i) <= ENDING_TIME) {
                generateCustomers(hours.get(i));
            }
        }
    }

    /**
     *
     * @param hour the current hour it is
     */
    public void generateCustomers(int hour) {
        //if rush then generate between 4 and 7 customers
        if (hour == FIRST_RUSH || hour == SECOND_RUSH || hour == THIRD_RUSH) {
            int rushCustomers = (int) (Math.random() * MULTIPLIER_RUSH) + ADD_RUSH;
            System.out.println(" ");
            System.out.println("number of customers: " + rushCustomers);
            System.out.println("");
            orderOfCustomers(rushCustomers);
        }
        //if no rush generate between 0 and 3 customers
        else {
            int nonRushCustomers = (int) (Math.random() * MULTIPLIER_NORMAL);
            System.out.println(" ");
            System.out.println("number of customers: " + nonRushCustomers);
            System.out.println("");
            orderOfCustomers(nonRushCustomers);
        }
    }

    /**
     *
     * @param numberOfCustomers  the number of customers ordering for the hour
     */
    public void orderOfCustomers(int numberOfCustomers) {
        //if no customers then no point of ordering
        if (numberOfCustomers == 0) {
            System.out.println(" ");
            return;
        }
        //goes through all customers and randomly select if they want food off food or menu
        //then calls method to actually order the food
        for (int i = 0; i < numberOfCustomers; i++) {
            int typeOfOrder = (int) (Math.random() * MULTIPLIER_TYPE) + 1;
            if (typeOfOrder >= THRESHOLD) {
                System.out.println("Ordering food");
                orderFoodOffMenu();
            }
            else {
                System.out.println("Ordering recipe");
                orderRecipeOffMenu();
            }
        }
    }

    /**
     * orders food off the food menu
     */
    public void orderFoodOffMenu() {
        //gets the food menu from menu
        ArrayList<Food> foodOnMenu = getMenu().getFoodOnMenu();
        //if no food then food cannot be ordered
        if (foodOnMenu.size() == 0) {
            System.out.println("The customer ordered food but there was none on the menu");
            System.out.println("");
            return;
        }
        //randomly picks a food of the menu and orders it
        else {
            int randomIndexOfFood = (int) (Math.random() * foodOnMenu.size());
            String desieredFood = foodOnMenu.get(randomIndexOfFood).getName();
            int indexInInventory = getMenu().getIndexOfFood(desieredFood);
            if (indexInInventory != -1) {
                Food chosenFood = getMenu().getFood().get(indexInInventory);
                //if quantity of food is zero than food cannot be ordered
                if (chosenFood.getQuantity() <= 0) {
                    System.out.println("Sorry " + desieredFood + " is out of stock");
                    System.out.println("");
                }
                //otherwise food is ordered and money is gained
                else {
                    System.out.println("Person has odered " + chosenFood.getName() + " successfully");
                    System.out.println("");
                    chosenFood.subtractQuantity(1);
                    addMoney(chosenFood.getPrice() * MULTIPLIER);
                }
            }
            //if food not on menu then it is not ordered
            else {
                System.out.println("Sorry " + desieredFood + " is not on menu");
                System.out.println("");
            }
        }
    }

    /**
     * orders recipe off the food menu
     */
    public void orderRecipeOffMenu() {
        //gets the recipe menu from menu
        ArrayList<Recipe> recipeOnMenu = getMenu().getRecipeOnMenu();
        //if no recipe then recipe cannot be ordered
        if (recipeOnMenu.size() == 0) {
            System.out.println("The customer ordered a recipe but there was none on the menu");
            System.out.println("");
            return;
        }
        //randomly picks a food of the menu and orders it
        else {
            int randomIndexOfRecipe = (int) (Math.random() * recipeOnMenu.size());
            String desieredRecipe = recipeOnMenu.get(randomIndexOfRecipe).getName();
            //if quantity of recipe is zero than food cannot be ordered
            int indexInInventory = getMenu().getIndexOfRecipe(desieredRecipe);
            if (indexInInventory != -1) {
                Recipe choseRecipe = getMenu().getRecipe().get(indexInInventory);
                if (choseRecipe.getQuantity() <= 0) {
                    System.out.println("Sorry " + desieredRecipe + " is out of stock");
                    System.out.println("");
                }
                //recipe order succesfully and money is gained
                else {
                    System.out.println("Person has ordered " + desieredRecipe + " succesfully");
                    System.out.println("");
                    choseRecipe.decreaseQuantity(1);
                    addMoney(getMenu().priceFromRecipe(indexInInventory) * MULTIPLIER);
                }

            }
            //recipe not available and error message printed
            else {
                System.out.println("Sorry " + desieredRecipe + " is not in the inventory");
                System.out.println("");
            }
        }
    }

    /**
     *
     * @param startingTime the time before the users passes time
     * @param endingTime the ending time after the user passes time
     * @return an arraylist of integers which represent the time passes
     */
    public ArrayList<Integer> arrayListOfTimes(double startingTime, double endingTime) {
        ArrayList<Integer> hours = new ArrayList<Integer>();
        hours.clear();
        //if time did not change enough for customers hours is just null
        if ((int) startingTime == (int) endingTime) {
            return null;
        }
        //otherwise goes between starting and ending times and adds all possible hour markers
        for (int i = (int) startingTime + 1; i <= (int) endingTime; i++) {
            hours.add(i % HOURS_IN_DAY);
        }
        return hours;
    }

    /**
     *
     * @param item string of something
     * @return a method in market whitch does the actual work
     */
    public Food foodInMarket(String item) {
        return market.findFoodInMarket(item);
    }

    /**
     *
     * @param item a string of equipment
     * @return a method in market whitch does the actual work
     */
    public Equipment equipmentInMarket(String item) {
        return market.findEquipmentInMarket(item);
    }

    /**
     *
     * @param item a string of recipe
     * @return a method in market whitch does the actual work
     */
    public Recipe recipeInMarket(String item) {
        return market.findRecipeInMarket(item);
    }

}
