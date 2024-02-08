package noobmaster.fixlength.exception;

/**
 * An specified exception about parsing from string only
 * eg. cannot parse alphabet to number.
 *
 * @author Noob Master
 */
public class FixLengthParseException extends FixLengthException {

    /**
     * Create {@link FixLengthParseException} with specify error message.
     *
     * @param message error message
     */
    public FixLengthParseException(String message) {
        super(message);
    }

    /**
     * Create {@link FixLengthParseException} with specify error message and cause.
     *
     * @param message error message
     * @param cause exception cause
     */
    public FixLengthParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
