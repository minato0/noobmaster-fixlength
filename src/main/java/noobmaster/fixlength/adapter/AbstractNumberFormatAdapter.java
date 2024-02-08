package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;
import noobmaster.fixlength.exception.FixLengthParseException;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Objects;

/**
 * Abstract adapter to format and parse value between {@link String} and {@link Number}.
 *
 * @author Noob Master
 * @see Number
 */
abstract class AbstractNumberFormatAdapter<T extends Number> extends FixLengthJavaTypeAdapter<T> {

    /**
     * Number format pattern
     */
    private final String pattern;

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    protected AbstractNumberFormatAdapter(FixLengthNumberFormat format) {
        if (Objects.isNull(format)) {
            this.pattern = null;
        }
        else {
            this.pattern = format.pattern();
        }
    }

    /**
     * Format number value to string.
     *
     * @param value number value
     * @return string value
     */
    @Override
    protected String formatToString(T value) {
        if (Objects.isNull(this.pattern)) {
            return value.toString();
        }
        else {
            DecimalFormat formatter = new DecimalFormat(this.pattern);
            return formatter.format(value);
        }
    }

    /**
     * Parse string value to number.
     *
     * @param value not null string value
     * @return number value
     * @throws FixLengthParseException if parsing invalid number format
     */
    @Override
    protected T parseToObject(String value) {
        try {
            if (Objects.isNull(this.pattern)) {
                return parseNumber(value);
            } else {
                DecimalFormat formatter = new DecimalFormat(this.pattern);
                Number number = formatter.parse(value);
                return parseNumber(number.toString());
            }
        } catch (ParseException | NumberFormatException ex) {
            throw new FixLengthParseException("Cannot parse '%s' into number".formatted(value), ex);
        }
    }

    /**
     * Child class must implement how to parse string value to each number type.
     *
     * @param value number in string value
     * @return parsed number value
     */
    protected abstract T parseNumber(String value);
}
