import com.example.*;
import com.google.gson.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
public class LayoutTest {
    Gson gson = new Gson();
    Simulation test = new Simulation();
    Layout layout = test.layoutJson();

    @Test
    public void testGetMoney() {
        assertEquals(150.0, layout.getMoney(), .1);
    }

    @Test
    public void testSubtractMoney() {
        layout.subtractMoney(10.0);
        assertEquals(140.0, layout.getMoney(), .1);
    }

    @Test
    public void testGetDays() {
        assertEquals(0, layout.getDays());
    }

    @Test
    public void testAddDays() {
        layout.addDays();
        assertEquals(1, layout.getDays());
    }

    @Test
    public void testFindRecipeInMarket() {
        assertEquals(layout.getMarket().getRecipe().get(0), layout.recipeInMarket("gyro"));
    }

    @Test
    public void testFindFoodInMarket() {
        assertEquals(layout.getMarket().getFood().get(0), layout.foodInMarket("tomatoes"));
    }

    @Test
    public void testFindEquipmentInMarket() {
        assertEquals(layout.getMarket().getEquipment().get(3), layout.equipmentInMarket("pot"));
    }

    @Test
    public void testPassTime() {
        layout.passTime(360);
        assertEquals(6, layout.getHours(), .1);

    }

    @Test
    public void testPrintFoodInRecipe() {
        Recipe recipe = layout.getMarket().getRecipe().get(0);
        assertEquals("turkey, beef, pita", layout.getMarket().getRecipe().get(0).printFood());
    }

    @Test
    public void testPrintEquipmentInRecipe() {
        Recipe recipe = layout.getMarket().getRecipe().get(0);
        assertEquals("pot", layout.getMarket().getRecipe().get(0).printEquipment());
    }
}
