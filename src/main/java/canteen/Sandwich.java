package canteen;


import java.util.Arrays;
import java.util.Comparator;

enum SandwichIngredients {
    BREAD(0.5, 190), WHOLE_GRAIN_BREAD(0.7, 194), SALAMI(0.5, 115), HAM(0.5, 46), CHICKEN(0.5, 55), BEEF(1.0, 70), VEGAN_MEET_REPLACEMENT(1.0, 117), CHEDDAR(0.5, 113), MOZARELLA(0.5, 35), EMMENTAL(1.0, 120), CREAM_CHEESE(0.5, 100), TOMATO(0.2, 7), CUCUMBER(0.2, 3), PAPRIKA(0.4, 4), SALAD(0.2, 2), KETCHUP(0.2, 20), MAYONNAISE(0.2, 68), SPICY_JOGHURT(0.6, 15);
    private final double price;
    private final int kcal;

    /**
     * Constructor for SandwichIngredients
     *
     * @param price of the sandwich
     * @param kcal  of the sandwich
     */
    SandwichIngredients(double price, int kcal) {
        this.price = price;
        this.kcal = kcal;
    }

    public int getKcal() {
        return kcal;
    }

    public double getSanIngPrice() {
        return price;
    }
}

public class Sandwich extends CanteenProduct implements Ratable {
    SandwichIngredients[] ingredients;
    private int[] ratings;
    private int numberOfRatings;

    /**
     * Constructor for Sandwich
     *
     * @param name        name of the sandwich
     * @param ingredients ingredients of the sandwich
     */
    public Sandwich(String name, SandwichIngredients... ingredients) throws SandwichHasNoBreadException, SandwichHasTooFewIngredientsException {
        super(name);
        checkForBread(ingredients); //exception check
        checkForIngredients(ingredients); //exception check
        this.ingredients = ingredients;
        this.ratings = new int[20];
        this.numberOfRatings = 0;
    }

    /**
     * checks if the sandwich has bread
     *
     * @param ingredients
     * @throws SandwichHasNoBreadException
     */
    public void checkForBread(SandwichIngredients[] ingredients) throws SandwichHasNoBreadException {
        boolean hasBread = false;
        for (SandwichIngredients ingredient : ingredients) {
            if (ingredient == SandwichIngredients.BREAD || ingredient == SandwichIngredients.WHOLE_GRAIN_BREAD) {
                hasBread = true;
                break;
            }
        }
        if (!hasBread) {
            throw new SandwichHasNoBreadException("The sandwich must have bread!");
        }
    }

    public void checkForIngredients(SandwichIngredients[] ingredients) throws SandwichHasTooFewIngredientsException {
        if (ingredients.length < 3) {
            throw new SandwichHasTooFewIngredientsException("The sandwich must have at least 3 ingredients!");
        }
    }

    /**
     * Copies the ingredients-array
     * Sorts the copy in descending order based on the price
     * gets the four highest entries
     *
     * @return the price of the sandwich
     */
    @Override
    public double getPrice() {
        double price = 0;

        SandwichIngredients[] copy = Arrays.copyOf(ingredients, ingredients.length); //first paramenter is the array to be copied, second one is the length of the new array

        Arrays.sort(copy, Comparator.comparingDouble(SandwichIngredients::getSanIngPrice).reversed());// first parameter is the array, second one is how we want it to be sorted(get the Price and sort them in a descending order

        SandwichIngredients[] highestEntries = Arrays.copyOfRange(copy, 0, 4); // Get the four highest entries from the sorted copy

        for (SandwichIngredients entry : highestEntries) {
            price += entry.getSanIngPrice(); //sum of the highest entries
        }
        return price;
    }

    /**
     * @return returns the kcal of the sandwich
     */
    public int getKcal() {
        int kcal = 0;
        for (SandwichIngredients ingredient : ingredients) {
            kcal += ingredient.getKcal(); //sum of all the ingredients kcal
        }
        return kcal;
    }

    /**
     * calls super class hash code (name and baseProduct)
     * after that, adds ingredients to the hashcode
     *
     * @return the hashcode of the product, BY OUR OWN TERMS
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Arrays.hashCode(ingredients);
        return result;
    }

    /**
     * Standard test for equality
     *
     * @param obj another object
     * @return true if the objects are truly equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Sandwich objSandwich = (Sandwich) obj;

        if (!getName().equals(objSandwich.getName())) {
            return false;
        }

        if (!Arrays.equals(ingredients, objSandwich.ingredients)) {
            return false;
        }

        return true;
    }

    /**
     * @return a string representation of the sandwich
     */
    @Override
    public String toString() {
        return getName().toUpperCase() + "(" + getKcal() + " kcal)\t\t\t\t" + getPrice();
    }

    /**
     * Function to rate the product on a scale of 1 to 5
     *
     * @param rating the given rating
     */
    @Override
    public void rateProduct(int rating) {
        if (numberOfRatings == ratings.length) { //Ratings.length = 20, if numberOfRatings reaches 20 == array full
            throw new IllegalArgumentException("Maximum number of ratings reached");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        ratings[numberOfRatings] = rating;
        numberOfRatings++;
    }

    /**
     * @return the avg rating calculated by adding all the ratings together and dividing them by the number of ratings.
     */
    @Override
    public double getAvgRating() {
        if (numberOfRatings == 0) {
            return 0.0;
        }
        double sum = 0;

        for (int rating : ratings) {
            sum += rating;
        }
        return sum / numberOfRatings;
    }

    @Override
    public int getNumberOfRatings() {
        return numberOfRatings;
    }

}