import com.example.*;
import com.google.gson.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class MarketTest {
    Gson gson = new Gson();
    Simulation test = new Simulation();
    Layout layout = test.layoutJson();
    @Test
    public void testFoodArrayList() {
        assertEquals(14, layout.getMarket().getFood().size());
    }

    @Test
    public void testGetEquipmentArrayList() {
        assertEquals(4, layout.getMarket().getEquipment().size());
    }

    @Test
    public void testGetRecipeArrayList() {
        assertEquals(6, layout.getMarket().getRecipe().size());
    }

    @Test
    public void testPrintFoodItme() {
        assertEquals("Name: tomatoes" + "\n" + "Base price: 1.0" + "\n" + "Quantity owned: 0",
                layout.getMarket().printFoodItem(layout.getMarket().getFood().get(0)));
    }

    @Test
    public void testPrintRecipeItem() {
        assertEquals("Name: pot" + "\n" + "Base Price: 1.65" + "\n" + "Upkeep Price: 0.4",
                layout.getMarket().printEquipmentItems(layout.getMarket().getEquipment().get(3)));
    }

    @Test
    public void testFindFoodInMarket() {
        assertEquals(layout.getMarket().getFood().get(0), layout.getMarket().findFoodInMarket("tomatoes"));
    }

    @Test
    public void testFindEquipmantInMarket() {
        assertEquals(layout.getMarket().getEquipment().get(3), layout.getMarket().findEquipmentInMarket("pot"));
    }

    @Test
    public void testFindRecipeInMarket() {
        assertEquals(layout.getMarket().getRecipe().get(0), layout.getMarket().findRecipeInMarket("gyro"));
    }

    @Test
    public void testPrintEquipmantInMarket() {
        assertEquals("Equipment: spatuala, magic, pan, pot", layout.getMarket().printEquipmentInMarket());
    }

}