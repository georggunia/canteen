package canteen;

import java.util.ArrayList;
import java.util.List;

public class MainCanteen {
    public static void main(String[] args) {
        List<CanteenProduct> products = new ArrayList<CanteenProduct>();
        products.addAll(getCoffeeProducts());
        products.addAll(getSandwiches());
        printCoffee(products);
        printSandwiches(products);

        // Iterate over products and give 20 random ratings for all Ratable products
        for (CanteenProduct product : products) { //for each product
            if (product instanceof Ratable) { //if its a ratable
                Ratable ratableProduct = (Ratable) product; // neccessary cast
                for (int i = 0; i < 20; i++) {
                    int rating = (int) (Math.random() * 5) + 1; //random number-> (int)--> random number 0-4-> +1 --> random number 1-5
                    ratableProduct.rateProduct(rating);
                }
            }
        }

        System.out.println("Average rating of all products: " + computeAvgRatings(products));
    }

    /**
     * @param products products to be iterated over
     * @return the average rating, calculated by summing up the ratings and dividing them by the number of ratings
     */
    public static double computeAvgRatings(List<CanteenProduct> products) {
        double sum = 0;
        int count = 0;
        for (CanteenProduct product : products) { //for each product
            if (product instanceof Ratable) { //if its a ratable
                Ratable ratableProduct = (Ratable) product; // neccessary cast
                sum += ratableProduct.getAvgRating();
                count++;
            }
        }
        return sum / count;
    }

    public static List<CanteenProduct> getSandwiches() {
        List<CanteenProduct> result = new ArrayList<CanteenProduct>();
        Sandwich salami = new Sandwich("Salami", SandwichIngredients.BREAD, SandwichIngredients.SALAMI, SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        Sandwich schinken = new Sandwich("Schinken", SandwichIngredients.BREAD, SandwichIngredients.HAM, SandwichIngredients.CUCUMBER, SandwichIngredients.SALAD, SandwichIngredients.MAYONNAISE);
        Sandwich chicken = new Sandwich("Chicken", SandwichIngredients.BREAD, SandwichIngredients.CHICKEN, SandwichIngredients.CREAM_CHEESE, SandwichIngredients.EMMENTAL, SandwichIngredients.PAPRIKA, SandwichIngredients.CUCUMBER, SandwichIngredients.KETCHUP);
        Sandwich delite = new Sandwich("Delite", SandwichIngredients.WHOLE_GRAIN_BREAD, SandwichIngredients.VEGAN_MEET_REPLACEMENT, SandwichIngredients.TOMATO, SandwichIngredients.MOZARELLA, SandwichIngredients.CREAM_CHEESE, SandwichIngredients.PAPRIKA, SandwichIngredients.SPICY_JOGHURT);
        Sandwich meatLove = new Sandwich("Meat Love", SandwichIngredients.BREAD, SandwichIngredients.BEEF, SandwichIngredients.CHICKEN, SandwichIngredients.HAM, SandwichIngredients.CHEDDAR, SandwichIngredients.CUCUMBER);
        Sandwich rosalinde = new Sandwich("Rosalinde", SandwichIngredients.WHOLE_GRAIN_BREAD, SandwichIngredients.VEGAN_MEET_REPLACEMENT, SandwichIngredients.CREAM_CHEESE, SandwichIngredients.CUCUMBER);

        result.add(salami);
        result.add(schinken);
        result.add(chicken);
        result.add(delite);
        result.add(meatLove);
        result.add(rosalinde);
        return result;
    }

    public static List<CanteenProduct> getCoffeeProducts() {
        List<CanteenProduct> result = new ArrayList<CanteenProduct>();
        Coffee espresso = new Coffee("Espresso", CoffeeIngredients.ESPRESSO_SHOT);
        Coffee doubleEspresso = new Coffee("Doppelter Espresso", espresso, CoffeeIngredients.ESPRESSO_SHOT);
        Coffee cappuccino = new Coffee("Cappuccino", espresso, CoffeeIngredients.FROTHED_MILK);
        Coffee flatWhite = new Coffee("Flat White", doubleEspresso, CoffeeIngredients.FROTHED_MILK);
        Coffee latteMacchiato = new Coffee("Latte Macchiato", CoffeeIngredients.FROTHED_MILK_XXL, CoffeeIngredients.ESPRESSO_SHOT);
        Coffee hazelnutMacchiato = new Coffee("Haselnuss-Macchiato", latteMacchiato, CoffeeIngredients.HAZELNUT_SYRUP);
        Coffee americano = new Coffee("Americano", CoffeeIngredients.HOT_WATER, CoffeeIngredients.ESPRESSO_SHOT);
        Coffee caffeLatte = new Coffee("Caffe Latte", americano, CoffeeIngredients.MILK);

        result.add(espresso);
        result.add(doubleEspresso);
        result.add(cappuccino);
        result.add(flatWhite);
        result.add(latteMacchiato);
        result.add(hazelnutMacchiato);
        result.add(americano);
        result.add(caffeLatte);

        return result;
    }

    public static void printCoffee(List<CanteenProduct> products) {
        System.out.println("-------------------------\n---Kaffeespezialitäten---\n-------------------------");
        for (CanteenProduct p : products) {
            if (p instanceof Coffee) {
                System.out.println(p);
            }
        }
    }

    public static void printSandwiches(List<CanteenProduct> products) {
        System.out.println("-------------------------\n---     Sandwiches    ---\n-------------------------");
        for (CanteenProduct p : products) {
            if (p instanceof Sandwich) {
                System.out.println(p);
            }
        }
    }
}
