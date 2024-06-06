package canteen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CoffeeTest {

    private Coffee espresso;
    private Coffee doubleEspresso;
    private Coffee cappuccino;
    private Coffee flatWhite;
    private Coffee latteMacchiato;

    @Before
    public void init() {

        espresso = new Coffee("Espresso", CoffeeIngredients.ESPRESSO_SHOT);
        doubleEspresso = new Coffee("Doppelter Espresso", espresso, CoffeeIngredients.ESPRESSO_SHOT);
        cappuccino = new Coffee("Cappuccino", espresso, CoffeeIngredients.FROTHED_MILK);
        flatWhite = new Coffee("Flat White", doubleEspresso, CoffeeIngredients.FROTHED_MILK);
        latteMacchiato = new Coffee("Latte Macchiato", CoffeeIngredients.FROTHED_MILK_XXL, CoffeeIngredients.ESPRESSO_SHOT);
    }

    @Test
    public void testConstructor() {
        

        assertSame("The baseProduct is not set correctly", espresso, cappuccino.getBaseProduct());
        assertSame("The baseProduct is not set correctly", espresso, flatWhite.getBaseProduct().getBaseProduct());

        assertThrows("must throw IllegalArgumentException on null name", IllegalArgumentException.class,
                () -> new Coffee(null, CoffeeIngredients.HOT_WATER));
        assertThrows("must throw IllegalArgumentException on name length < 4", IllegalArgumentException.class,
                () -> new Coffee("H2", CoffeeIngredients.HOT_WATER));
        assertThrows("must throw IllegalArgumentException on empty ingredients Array", IllegalArgumentException.class,
                () -> new Coffee("Luftikus"));

    }

    @Test
    public void testPrice() {
        assertEquals("The price is not calculated correctly", 1.2, espresso.getPrice(), 0.01);
        assertEquals("The price is not calculated correctly", 3.9, flatWhite.getPrice(), 0.01);
    }

    @Test
    public void testEquals() {
        assertTrue("equals must return true on identical objects",espresso.equals(espresso));
        assertFalse("equals must return false on null",espresso.equals(null));
      //  assertFalse("equals must return false on different types", espresso.equals(new Sandwich("dryBread", SandwichIngredients.BREAD, SandwichIngredients.SALAD, SandwichIngredients.TOMATO)));
        
        Coffee espresso1 = new Coffee("Expresso", CoffeeIngredients.ESPRESSO_SHOT);
        Coffee espresso2 = new Coffee("Espresso", CoffeeIngredients.ESPRESSO_SHOT);
        assertFalse("equals must return false on different names",espresso1.equals(espresso2));
        assertTrue("equals must return true on equal objects", espresso.equals(espresso2));
        
        Coffee latteMacchiato1 = new Coffee("Latte Macchiato", CoffeeIngredients.FROTHED_MILK_XXL, CoffeeIngredients.ESPRESSO_SHOT);
        Coffee latteMacchiato2 = new Coffee("Latte Macchiato", CoffeeIngredients.ESPRESSO_SHOT, CoffeeIngredients.FROTHED_MILK_XXL);
        assertFalse("equals muss return false if the ingredients are in a different order", latteMacchiato1.equals(latteMacchiato2));
        assertTrue("equals must return true on equal objects with equal Arrays", latteMacchiato1.equals(latteMacchiato));
    }
}
