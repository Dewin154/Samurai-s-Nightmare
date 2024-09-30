package thd.game.level;


/**
 * Exception to indicate that there are no more levels.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {

    /**
     * Creates new Exception.
     *
     * @param message the message to be displayed.
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}
