package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

/**
 * Default adapter to format and parse value between {@link String} and {@link Short}.
 *
 * @author Noob Master
 * @see Short
 */
class DefaultShortAdapter extends AbstractNumberFormatAdapter<Short> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultShortAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link Short}
     *
     * @param value number in string value
     * @return short value
     */
    @Override
    protected Short parseNumber(String value) {
        return Short.parseShort(value);
    }
}
