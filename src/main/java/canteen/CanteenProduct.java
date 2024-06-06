package canteen;

public abstract class CanteenProduct {
    private final String name;
    private CanteenProduct baseProduct;

    /**
     * Konstructor for CanteenProduct
     *
     * @param name        Name of product
     * @param baseProduct Base product
     */
    public CanteenProduct(String name, CanteenProduct baseProduct) {
        if (name == null || name.trim().length() < 3) //trims the whitespaces @ beginning and end of the string, checks if it's at least 3 characters long
            throw new IllegalArgumentException("Name must be at least 3 characters long");
        this.name = name.trim();
        this.baseProduct = baseProduct;
    }

    /**
     * Konstructor for CanteenProduct WITHOUT baseProduct
     *
     * @param name Name of product
     */
    public CanteenProduct(String name) {
        this(name, null); //default constructor call, but with null for baseProduct
    }

    /**
     * @return returns the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * @return returns the base product of the product
     */
    public CanteenProduct getBaseProduct() {
        return baseProduct;
    }

    /**
     * @return the price of the product
     */
    public abstract double getPrice(); //Abstract methods don't have to be implemented

    /**
     * Standard test for equality
     *
     * @param obj another object
     * @return returns true if the objects are truly equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CanteenProduct other = (CanteenProduct) obj;

        if (!name.equals(other.getName())) {
            return false;
        }

        try {
            if (!baseProduct.equals(other.getBaseProduct()))  //tries to compare base products
                return false;
        } catch (NullPointerException e) { //gets exception if one of the base products is null
            if (!(baseProduct == null && other.getBaseProduct() == null)) //sees if both are null
                return false;
        }

        return true;
    }

    /**
     * first adds the name hashcode, then adds the baseProduct hashcode
     *
     * @return the hashcode of the product, BY OUR OWN TERMS
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + name.hashCode();
        result = prime * result + ((baseProduct == null) ? 0 : baseProduct.hashCode());
        return result;
    }
}

