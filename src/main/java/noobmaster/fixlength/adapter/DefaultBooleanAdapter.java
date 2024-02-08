package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthBooleanFormat;
import noobmaster.fixlength.exception.FixLengthParseException;

import java.util.Objects;

/**
 * Default adapter to format and parse value between {@link String} and {@link Boolean}.
 *
 * @author Noob Master
 * @see Boolean
 */
class DefaultBooleanAdapter extends FixLengthJavaTypeAdapter<Boolean> {

    /**
     * Format string value if boolean value is {@code true}.
     */
    private final String trueValue;

    /**
     * Format string value if boolean value is {@code false}.
     */
    private final String falseValue;

    /**
     * Flag to compare two string using case-sensitive check or not.
     */
    private final boolean caseSensitive;

    /**
     * Create Adapter with specified format.
     * If there is no annotation config about format,
     * it will use '1' as true value, '0' as false value.
     *
     * @param format annotation config
     */
    DefaultBooleanAdapter(FixLengthBooleanFormat format) {
        if (Objects.isNull(format)) {
            this.trueValue = "1";
            this.falseValue = "0";
            this.caseSensitive = true;
        }
        else {
            this.trueValue = format.trueValue();
            this.falseValue = format.falseValue();
            this.caseSensitive = format.caseSensitive();
        }
    }

    /**
     * Format character value to string.
     * It must be 1 character string.
     *
     * @param value character value
     * @return string value
     */
    @Override
    protected String formatToString(Boolean value) {
        return Boolean.TRUE.equals(value) ? this.trueValue : this.falseValue;
    }

    /**
     * Parse string value to boolean.
     * The given string must have only 1 character.
     *
     * @param value not null string value
     * @return boolean value
     * @throws FixLengthParseException if parsing unknown string value
     */
    @Override
    protected Boolean parseToObject(String value) {
        if (equals(this.trueValue, value)) {
            return Boolean.TRUE;
        }
        else if (equals(this.falseValue, value)) {
            return Boolean.FALSE;
        }
        else {
            throw new FixLengthParseException("Cannot parse '%s' into boolean, expected only '%s' and '%s'".formatted(value, this.trueValue, this.falseValue));
        }
    }

    /**
     * Comparing two strings with checking case-sensitive or not.
     *
     * @param value1 string value 1
     * @param value2 string value 2
     * @return {@code true} if both string are same value
     */
    private boolean equals(String value1, String value2) {
        if (this.caseSensitive) {
            return value1.equals(value2);
        }
        else {
            return value1.equalsIgnoreCase(value2);
        }
    }
}
