package canteen;

/**
 * Exception thrown when a sandwich has no bread
 */
public class SandwichHasNoBreadException extends RuntimeException {
    public SandwichHasNoBreadException(String message) {
        super(message);
    }
}
