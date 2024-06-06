package canteen;

import java.util.Arrays;

enum CoffeeIngredients {
    FROTHED_MILK(1.5), FROTHED_MILK_XXL(2.0), MILK(1.0), SUGAR(0.0), HAZELNUT_SYRUP(0.8), VANILLA_SYRUP(1.2), CARAMEL_SYRUP(0.8), CHOCOLATE(1.0), CHOCOLATE_POWDER(0.0), ESPRESSO_SHOT(1.2), HOT_WATER(0.0);
    private final double price;

    CoffeeIngredients(double price) { //Constructor for CoffeeIngredients
        this.price = price;
    }

    public double getCofIngPrice() {
        return price;
    }

}

public class Coffee extends CanteenProduct {
    private CoffeeIngredients[] ingredients;

    /**
     * Constructor for Coffee
     *
     * @param name        name of the product
     * @param baseProduct baseProduct coffee
     * @param ingredients ingredients of the Coffee
     */
    public Coffee(String name, Coffee baseProduct, CoffeeIngredients... ingredients) {
        super(name, baseProduct); //super constructor, calls super class(CanteenProduct) constructor

        if (ingredients == null || ingredients.length == 0) {
            throw new IllegalArgumentException("Ingredients can not be null or empty");
        }
        this.ingredients = ingredients;

    }

    /**
     * Constructor for Coffee  WITHOUT baseProduct
     *
     * @param name        name of the product
     * @param ingredients ingredients of the Coffee
     */
    public Coffee(String name, CoffeeIngredients... ingredients) {
        this(name, null, ingredients); //calls Coffee Constructor without baseProduct
    }

    /**
     * @return the calculated Price (sum of all ingredients+baseProduct)
     */
    @Override
    public double getPrice() {
        double ingPrice = 0.0;

        if (getBaseProduct() != null) {
            ingPrice += getBaseProduct().getPrice();
        }

        for (CoffeeIngredients ing : ingredients) {
            ingPrice += ing.getCofIngPrice(); //Adds together all ingredient prices
        }

        return ingPrice;
    }

    /**
     * Standard test for equality
     *
     * @param obj another object
     * @return returns true if the objects are truly equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        Coffee objCoffee = (Coffee) obj;

        if (!getName().equals(objCoffee.getName())) {
            return false;
        }

        try {
            if (!(getBaseProduct().equals(objCoffee.getBaseProduct()))) { //Tries to compare base products
                return false;
            }
        } catch (NullPointerException e) { //one of the base products is null
            if (!(getBaseProduct() == null) && objCoffee.getBaseProduct() == null||getBaseProduct()==null&&!(objCoffee.getBaseProduct()==null)) //check if both are null
                return false;
        }

        if (!Arrays.equals(ingredients, objCoffee.ingredients)) {
            return false;
        }

        return true;
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
     * @return a string representation of the coffee
     */
    @Override
    public String toString() {
        return getName().toUpperCase() + "\t\t\t\t\t\t" + getPrice();
    }

}