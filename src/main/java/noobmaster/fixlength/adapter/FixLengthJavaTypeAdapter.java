package noobmaster.fixlength.adapter;

import java.util.Objects;

/**
 * An abstract class for format and parse between string and object.
 *
 * @author Noob Master
 * @param <T> Generic object type
 */
public abstract class FixLengthJavaTypeAdapter<T> implements FixLengthAdapter {

    /**
     * Child class must implement logic to format generic type value to string.
     *
     * @param value generic type value
     * @return formatted string
     */
    protected abstract String formatToString(T value);

    /**
     * Child class must implement logic to parse string value to object.
     *
     * @param value not null string value
     * @return parsed object
     */
    protected abstract T parseToObject(String value);

    /**
     * Format object's value into string.
     * If the value is {@code null} it will return empty string
     * otherwise it will call {@link #formatToString(Object)} method to format generic type value.
     *
     * @param value field' value
     * @return formatted string from field's value
     */
    @Override
    public final String format(Object value) {
        if (Objects.isNull(value)) {
            return "";
        }
        else {
            return formatToString((T) value);
        }
    }

    /**
     * Parse string's value into object.
     * If the string is empty it will return {@code null}
     * otherwise it will call {@link #parseToObject(String)} method to parse string.
     *
     * @param value formatted string value
     * @return parsed object from string
     */
    @Override
    public final Object parse(String value) {
        if (value.isEmpty()) {
            return null;
        }
        else {
            return parseToObject(value);
        }
    }
}
