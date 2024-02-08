package noobmaster.fixlength;

/**
 * An alignment to fixed length string.
 *
 * @author Noob Master
 */
public enum Align {

    /**
     * The value will be on the left of fixed length string
     */
    LEFT,
    /**
     * The value will be on the right of fixed length string
     */
    RIGHT;


    /**
     * Add padding character to string until value's length equals to expected length.
     *
     * @param str string value
     * @param padChar padding character
     * @param length expected length
     * @return string value with padding character added
     */
    String addPadChar(final String str, final char padChar, final int length) {
        final int strLength = str.length();
        if (strLength == length) {
            return str;
        }
        final String padString = (padChar + "").repeat(length - strLength);
        if (LEFT.equals(this)) {
            return str + padString;
        }
        else {
            return padString + str;
        }
    }

    /**
     * Remove all padding characters from a string.
     *
     * @param str string value
     * @param padChar padding character
     * @return string with remove padding characters
     */
    String removePadChar(final String str, final char padChar) {
        char[] characters = str.toCharArray();
        if (LEFT.equals(this)) {
            int lastIndex = characters.length;
            while (--lastIndex >= 0 && padChar == characters[lastIndex]);
            return str.substring(0, Math.min(characters.length, lastIndex+1));
        }
        else {
            int fistIndex = -1;
            while (++fistIndex <= characters.length-1 && padChar == characters[fistIndex]);
            return str.substring(fistIndex);
        }
    }
}
