package noobmaster.fixlength.adapter;

/**
 * Default adapter to format and parse value between {@link String} and {@link String}.
 *
 * @author Noob Master
 * @see String
 */
class DefaultStringAdapter extends FixLengthJavaTypeAdapter<String> {

    /**
     * Return input value immediately.
     *
     * @param value string value
     * @return same string as input
     */
    @Override
    public String formatToString(String value) {
        return value;
    }

    /**
     * Return input value immediately.
     *
     * @param value string value
     * @return same string as input
     */
    @Override
    public String parseToObject(String value) {
        return value;
    }
}
