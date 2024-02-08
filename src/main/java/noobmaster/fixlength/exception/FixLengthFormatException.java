package noobmaster.fixlength.exception;

/**
 * An specified exception about format to string only
 * eg. the value length is more than specified fixed length.
 *
 * @author Noob Master
 */
public class FixLengthFormatException extends FixLengthException {

    /**
     * Create {@link FixLengthFormatException} with specify error message.
     *
     * @param message error message
     */
    public FixLengthFormatException(String message) {
        super(message);
    }

    /**
     * Create {@link FixLengthFormatException} with specify error message and cause.
     *
     * @param message error message
     * @param cause exception cause
     */
    public FixLengthFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
