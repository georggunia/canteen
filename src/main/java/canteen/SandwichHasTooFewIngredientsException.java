package canteen;

/**
 * Exception thrown when a sandwich has too few ingredients
 */
public class SandwichHasTooFewIngredientsException extends RuntimeException {
    public SandwichHasTooFewIngredientsException(String message) {
        super(message);
    }
}
