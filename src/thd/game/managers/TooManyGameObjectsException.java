package thd.game.managers;

/**
 * Public class that represents an unchecked Exception if too many objects are displayed.
 */
public class TooManyGameObjectsException extends RuntimeException {
    /**
     * Constructs new exception with a message.
     *
     * @param message detailed message.
     */
    public TooManyGameObjectsException(String message) {
        super(message);
    }
}
