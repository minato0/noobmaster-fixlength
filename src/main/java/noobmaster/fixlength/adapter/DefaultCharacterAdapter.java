package noobmaster.fixlength.adapter;

import noobmaster.fixlength.exception.FixLengthParseException;

/**
 * Default adapter to format and parse value between {@link String} and {@link Character}.
 *
 * @author Noob Master
 * @see Character
 */
class DefaultCharacterAdapter extends FixLengthJavaTypeAdapter<Character> {

    /**
     * Format character value to string.
     * It must be 1 character string.
     *
     * @param value character value
     * @return string value
     */
    @Override
    protected String formatToString(Character value) {
        return value.toString();
    }

    /**
     * Parse string value to character.
     * The given string must have only one character.
     *
     * @param value not null string value
     * @return character value
     * @throws FixLengthParseException if string's length more than 1
     */
    @Override
    protected Character parseToObject(String value) {
        if (value.length() > 1) {
            throw new FixLengthParseException("Cannot parse '%s' into a character".formatted(value));
        }
        return value.charAt(0);
    }
}
