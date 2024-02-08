package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

/**
 * Default adapter to format and parse value between {@link String} and {@link Long}.
 *
 * @author Noob Master
 * @see Long
 */
class DefaultLongAdapter extends AbstractNumberFormatAdapter<Long> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultLongAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link Long}
     *
     * @param value number in string value
     * @return long value
     */
    @Override
    protected Long parseNumber(String value) {
        return Long.parseLong(value);
    }
}
