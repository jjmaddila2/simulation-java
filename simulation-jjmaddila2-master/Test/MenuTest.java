import com.example.*;
import com.google.gson.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MenuTest {
    Gson gson = new Gson();
    Simulation test = new Simulation();
    Layout layout = test.layoutJson();
    ArrayList<Food> foodOnMenu = new ArrayList<Food>();
    ArrayList<Food> food = new ArrayList<Food>();
    ArrayList<Recipe> recipeOnMenu = new ArrayList<Recipe>();
    ArrayList<Recipe> recipe = new ArrayList<Recipe>();
    ArrayList<Equipment> equipment = new ArrayList<Equipment>();

    @Test
    public void testFoodOnMenuArrayList() {
        assertEquals(foodOnMenu, layout.getMenu().getFoodOnMenu());
    }

    @Test
    public void testEquipmentArrayList() {
        assertEquals(equipment, layout.getMenu().getEquipment());
    }

    @Test
    public void testFoodArrayList() {
        assertEquals(food, layout.getMenu().getFood());
    }

    @Test
    public void testRecipeOnMenuArrayList() {
        assertEquals(recipeOnMenu, layout.getMenu().getRecipeOnMenu());
    }

    @Test
    public void testRecipeArrayList() {
        assertEquals(recipe, layout.getMenu().getRecipe());
    }

    @Test
    public void testAddFoodToInventory() {
        assertEquals(food, layout.getMenu().getFood());
        food.add(layout.getMarket().getFood().get(0));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        assertEquals(food, layout.getMenu().getFood());
        food.add(layout.getMarket().getFood().get(1));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(1));
        assertEquals(food, layout.getMenu().getFood());
    }

    @Test
    public void testPrintFoodInInventory() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(1));
        assertEquals("Food: tomatoes, lettuce", layout.getMenu().printFoodInInventory());
    }

    @Test
    public void testAddRecipeToInventory() {
        assertEquals(recipe, layout.getMenu().getRecipe());
        recipe.add(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        assertEquals(recipe, layout.getMenu().getRecipe());
        recipe.add(layout.getMarket().getRecipe().get(1));
        layout.getMenu().addRecipeToInventory( layout.getMarket().getRecipe().get(1));
        assertEquals(recipe, layout.getMenu().getRecipe());
    }

    @Test
    public void testPrintRecipeInInventory() {
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addRecipeToInventory( layout.getMarket().getRecipe().get(1));
        assertEquals("Recipes: gyro, salad", layout.getMenu().printRecipeInInventory());
    }

    @Test
    public void testAddEquipmentToInventory() {
        assertEquals(equipment, layout.getMenu().getEquipment());
        equipment.add(layout.getMarket().getEquipment().get(0));
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(0));
        assertEquals(equipment, layout.getMenu().getEquipment());
        equipment.add(layout.getMarket().getEquipment().get(1));
        layout.getMenu().addEquipmentToInventory( layout.getMarket().getEquipment().get(1));
        assertEquals(equipment, layout.getMenu().getEquipment());
    }

    @Test
    public void testPrintEquipmentInInventory() {
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(0));
        layout.getMenu().addEquipmentToInventory( layout.getMarket().getEquipment().get(1));
        assertEquals("Equipment: spatuala, magic", layout.getMenu().printEquipmentInInventory());
    }

    @Test
    public void testIsFoodOwned() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        assertEquals(true, layout.getMenu().isFoodOwned("tomatoes"));
        assertEquals(false, layout.getMenu().isFoodOwned("pita"));
    }

    @Test
    public void testAddToMenu() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(6));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(9));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(12));
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(3));
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        foodOnMenu.add(layout.getMarket().getFood().get(0));
        recipeOnMenu.add(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addToMenu("tomatoes");
        layout.getMenu().addToMenu("gyro");
        assertEquals(foodOnMenu, layout.getMenu().getFoodOnMenu());
        assertEquals(recipeOnMenu, layout.getMenu().getRecipeOnMenu());
    }

    @Test
    public void testRemoveFromMenu() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        foodOnMenu.add(layout.getMarket().getFood().get(0));
        layout.getMenu().addToMenu("tomatoes");
        assertEquals(foodOnMenu, layout.getMenu().getFoodOnMenu());
        layout.getMenu().removeFromMenu("tomatoes");
        foodOnMenu.remove(0);
        assertEquals(foodOnMenu, layout.getMenu().getFoodOnMenu());
    }

    @Test
    public void testCheckQuanitiyInRecpie() {
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        assertEquals(true, layout.getMenu().checkQuantityInRecipes(0));
    }

    @Test
    public void testCheckPartsInRecipie() {
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addFoodToInventory(1, layout.getMarket().getFood().get(6));
        layout.getMenu().addFoodToInventory(1, layout.getMarket().getFood().get(9));
        layout.getMenu().addFoodToInventory(1, layout.getMarket().getFood().get(12));
        assertEquals(true, layout.getMenu().checkQuantityInRecipes(0));
    }

    @Test
    public void testCookItem() {
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addFoodToInventory(1, layout.getMarket().getFood().get(6));
        layout.getMenu().addFoodToInventory(3, layout.getMarket().getFood().get(9));
        layout.getMenu().addFoodToInventory(5, layout.getMarket().getFood().get(12));
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(3));
        layout.getMenu().cookRecipe("gyro", 5, layout);
        assertEquals(1, layout.getMenu().getRecipe().get(0).getQuantity());
    }

    @Test
    public void testUpkeep() {
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(3));
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(1));
        assertEquals(1.4, layout.getMenu().getUpkeep(), .01);

    }

    @Test
    public void getIndexOfRecipieMenu() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(6));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(9));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(12));
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(3));
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        recipeOnMenu.add(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addToMenu("gyro");
        assertEquals(0, layout.getMenu().getIndexOfRecipeMenu("gyro"));
    }

    @Test
    public void testGetIndexOfFoodMenu() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        foodOnMenu.add(layout.getMarket().getFood().get(0));
        layout.getMenu().addToMenu("tomatoes");
        assertEquals(0, layout.getMenu().getIndexOfFoodMenu("tomatoes"));
    }

    @Test
    public void testGetIndexOfRecipe() {
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        recipe.add(layout.getMarket().getRecipe().get(0));
        assertEquals(0, layout.getMenu().getIndexOfRecipe("gyro"));
    }

    @Test
    public void testGetIndexOfFood() {
        layout.getMenu().addFoodToInventory(5, layout.getMarket().getFood().get(0));
        food.add(layout.getMarket().getFood().get(0));
        assertEquals(0, layout.getMenu().getIndexOfFood("tomatoes"));
    }

    @Test
    public void testIsRecipeInInventory() {
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        assertEquals(true, layout.getMenu().isRecipeInInventory("gyro"));
        assertEquals(false, layout.getMenu().isRecipeInInventory("salad"));
    }

    @Test
    public void testIsFoodInInventory() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        assertEquals(true, layout.getMenu().isFoodInInventory("tomatoes"));
        assertEquals(false, layout.getMenu().isFoodInInventory("pita"));
    }

    @Test
    public void testPrintMenu() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(0));
        foodOnMenu.add(layout.getMarket().getFood().get(0));
        layout.getMenu().addToMenu("tomatoes");
        assertEquals("Menu:" + "\n" + "tomatoes" + "\t" + "$1.50" + "\n", layout.getMenu().printMenu());
    }

    @Test
    public void testPriceFromRecipie() {
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(6));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(9));
        layout.getMenu().addFoodToInventory(0, layout.getMarket().getFood().get(12));
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(3));
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        recipeOnMenu.add(layout.getMarket().getRecipe().get(0));
        layout.getMenu().addToMenu("gyro");
        assertEquals(4.8, layout.getMenu().priceFromRecipe(0), .1);

    }

    @Test
    public void testSellItem() {
        layout.getMenu().addFoodToInventory(9, layout.getMarket().getFood().get(6));
        layout.getMenu().addEquipmentToInventory(layout.getMarket().getEquipment().get(3));
        layout.getMenu().addRecipeToInventory(layout.getMarket().getRecipe().get(0));
        layout.getMenu().sellItem("turkey", 5, layout);
        layout.getMenu().sellItem("pot", 1, layout);
        layout.getMenu().sellItem("gyro", 1, layout);
        assertEquals(4, layout.getMenu().getFood().get(0).getQuantity());
        assertEquals(equipment, layout.getMenu().getEquipment());
        assertEquals(recipe, layout.getMenu().getRecipe());
    }

}
