package app.exceptions;

/**
 * Exception thrown when valiappion fails
 */
public class ValiappionException extends Exception {
    
    /**
     * Constructs a new ValiappionException with the specified detail message.
     * @param message the detail message
     */
    public ValiappionException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new ValiappionException with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public ValiappionException(String message, Throwable cause) {
        super(message, cause);
    }
}