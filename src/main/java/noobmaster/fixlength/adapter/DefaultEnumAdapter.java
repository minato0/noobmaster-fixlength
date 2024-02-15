package noobmaster.fixlength.adapter;

import noobmaster.fixlength.exception.FixLengthParseException;

/**
 * Default adapter to format and parse value between {@link String} and {@link Enum}.
 *
 * @author Noob Master
 * @see Enum
 */
class DefaultEnumAdapter extends FixLengthJavaTypeAdapter<Object> {

    /**
     * Enumeration class
     */
    private final Class<? extends Enum> enumClass;

    /**
     * Create Adapter with specified enumeration class.
     *
     * @param enumClass enumeration class
     */
    DefaultEnumAdapter(Class<? extends Enum> enumClass) {
        this.enumClass = enumClass;
    }

    /**
     * Stringify enum value.
     *
     * @param value enum value
     * @return string value of enum
     */
    @Override
    protected String formatToString(Object value) {
        return value.toString();
    }

    /**
     * Parse string value to enum.
     *
     * @param value not null string value
     * @return enum value of string
     */
    @Override
    protected Object parseToObject(String value) {
        try {
            return Enum.valueOf(this.enumClass, value);
        } catch (IllegalArgumentException ex) {
            throw new FixLengthParseException("Cannot parse '%s' into enum, unknown enum value".formatted(value));
        }
    }
}
