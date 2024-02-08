package noobmaster.fixlength.adapter;

/**
 * An interface template for format and parse between string and object.
 *
 * @author Noob Master
 */
interface FixLengthAdapter {

    /**
     * Format field's value into string.
     *
     * @param value field' value
     * @return formatted string from field's value
     */
    String format(Object value);

    /**
     * Parse formatted string value into object.
     *
     * @param value formatted string value
     * @return parsed value from given string
     */
    Object parse(String value);
}
