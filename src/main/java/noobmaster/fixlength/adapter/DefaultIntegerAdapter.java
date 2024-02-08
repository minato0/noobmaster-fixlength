package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

/**
 * Default adapter to format and parse value between {@link String} and {@link Integer}.
 *
 * @author Noob Master
 * @see Integer
 */
class DefaultIntegerAdapter extends AbstractNumberFormatAdapter<Integer> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultIntegerAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link Integer}
     *
     * @param value number in string value
     * @return integer value
     */
    @Override
    protected Integer parseNumber(String value) {
        return Integer.parseInt(value);
    }
}
