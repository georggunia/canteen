package canteen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SandwichTest {


    private Sandwich schinken;
    private Sandwich chicken;
    private Sandwich delite;
    private Sandwich meatLove;
    private Sandwich rosalinde;

    @Before
    public void init() {

        
        schinken = new Sandwich("Schinken", SandwichIngredients.BREAD, SandwichIngredients.HAM,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        chicken = new Sandwich("Chicken", SandwichIngredients.BREAD, SandwichIngredients.CHICKEN,
                SandwichIngredients.CREAM_CHEESE, SandwichIngredients.EMMENTAL, SandwichIngredients.PAPRIKA,
                SandwichIngredients.CUCUMBER, SandwichIngredients.KETCHUP);
        delite = new Sandwich("Delite", SandwichIngredients.WHOLE_GRAIN_BREAD,
                SandwichIngredients.VEGAN_MEET_REPLACEMENT, SandwichIngredients.TOMATO, SandwichIngredients.MOZARELLA,
                SandwichIngredients.CREAM_CHEESE, SandwichIngredients.PAPRIKA, SandwichIngredients.SPICY_JOGHURT);
        meatLove = new Sandwich("Meat Love", SandwichIngredients.BREAD, SandwichIngredients.BEEF,
                SandwichIngredients.CHICKEN, SandwichIngredients.HAM, SandwichIngredients.CHEDDAR,
                SandwichIngredients.CUCUMBER);
        rosalinde = new Sandwich("Rosalinde", SandwichIngredients.WHOLE_GRAIN_BREAD,
                SandwichIngredients.VEGAN_MEET_REPLACEMENT, SandwichIngredients.CREAM_CHEESE,
                SandwichIngredients.CUCUMBER);

    }

    @Test
    public void testNameConstructor() {
        Sandwich salami = new Sandwich("Salami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        Sandwich salamiWS = new Sandwich("   Salami   ", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);

        assertEquals("constructor must set name", "Salami", salami.getName());
        assertEquals("constructor must trim name", "Salami", salamiWS.getName());

        assertThrows("must throw IllegalArgumentException on null name", IllegalArgumentException.class,
                () -> new Sandwich(null, SandwichIngredients.BREAD, SandwichIngredients.TOMATO,
                        SandwichIngredients.SALAD));
        assertThrows("must throw IllegalArgumentException on name length < 4", IllegalArgumentException.class,
                () -> new Sandwich("O2", SandwichIngredients.BREAD, SandwichIngredients.TOMATO,
                        SandwichIngredients.SALAD));
        assertThrows("must throw IllegalArgumentException on name length < 4", IllegalArgumentException.class,
                () -> new Sandwich("   O2   ", SandwichIngredients.BREAD, SandwichIngredients.TOMATO,
                        SandwichIngredients.SALAD));

    }

    @Test
    public void testIngredientsConstructor() {
        assertThrows("must throw SandwichHasTooFewIngredientsException on less than 2 ingredients",
                SandwichHasTooFewIngredientsException.class,
                () -> new Sandwich("Häftling", SandwichIngredients.BREAD));

        assertThrows("must throw SandwichHasNoBreadException when sandwhich has no bread",
                SandwichHasNoBreadException.class,
                () -> new Sandwich("Frutarier", SandwichIngredients.TOMATO, SandwichIngredients.CUCUMBER));

    }

    @Test
    public void testGetPrice(){
        Sandwich expensiveOne = new Sandwich("ProllSpecial", SandwichIngredients.BREAD, SandwichIngredients.SALAD, SandwichIngredients.CUCUMBER, SandwichIngredients.PAPRIKA, SandwichIngredients.BEEF, SandwichIngredients.CHICKEN, SandwichIngredients.BEEF, SandwichIngredients.EMMENTAL);
        assertEquals("price of \"expensiveOne\" must be 3.50",3.5,expensiveOne.getPrice(), 0.01);
    }

    @Test
    public void testGetKcal(){
        Sandwich expensiveOne = new Sandwich("ProllSpecial", SandwichIngredients.BREAD, SandwichIngredients.SALAD, SandwichIngredients.CUCUMBER, SandwichIngredients.PAPRIKA, SandwichIngredients.BEEF, SandwichIngredients.CHICKEN, SandwichIngredients.BEEF, SandwichIngredients.EMMENTAL);
        assertEquals("kcal of \"expensiveOne\" must be 514",514,expensiveOne.getKcal());
    }

    @Test
    public void testRateProduct(){
        Sandwich salami = new Sandwich("Salami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        assertThrows("rating must be between 1 and 5 points",IllegalArgumentException.class,() -> salami.rateProduct(0));
        assertThrows("rating must be between 1 and 5 points",IllegalArgumentException.class,() -> salami.rateProduct(6));
        salami.rateProduct(3);
        salami.rateProduct(2);
        salami.rateProduct(4);
    }

    @Test 
    public void testGetAvgRating(){
        Sandwich salami = new Sandwich("Salami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        salami.rateProduct(3);
        salami.rateProduct(2);
        salami.rateProduct(4);
        assertEquals("avgRating must be 3.0", 3.0, salami.getAvgRating(), 0.01);

    }

    @Test 
    public void testNumberOfRatings(){
        Sandwich salami = new Sandwich("Salami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        salami.rateProduct(3);
        salami.rateProduct(2);
        salami.rateProduct(4);
        assertEquals("getNumberOfRatings must be 3", 3, salami.getNumberOfRatings());
        
    }

    @Test
    public void testEquals(){
        Sandwich salami1 = new Sandwich("Salami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
                Sandwich salami2 = new Sandwich("Salami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
                SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        assertTrue("equals must return true on identical objects",salami1.equals(salami2));
        assertFalse("equals must return false on null",salami1.equals(null));
        assertFalse("equals must return false on different types", salami1.equals("This is a String"));
        
        Sandwich salami3 = new Sandwich("Samami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI,
        SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        assertFalse("equals must return false on different names",salami1.equals(salami3));
        assertTrue("equals must return true on equal objects", salami1.equals(salami2));
    }

}
