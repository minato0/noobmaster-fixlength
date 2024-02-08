package noobmaster.fixlength.exception;

/**
 * A generic exception about FixLength is usually happen when config invalid value.
 * Eg. does not specify custom adapter to not support field type.
 *
 * @author Noob Master
 */
public class FixLengthException extends RuntimeException {

    /**
     * Create {@link FixLengthException} with specify error message.
     *
     * @param message error message
     */
    public FixLengthException(String message) {
        super(message);
    }

    /**
     * Create {@link FixLengthException} with specify error message and cause.
     *
     * @param message error message
     * @param cause exception cause
     */
    public FixLengthException(String message, Throwable cause) {
        super(message, cause);
    }
}
