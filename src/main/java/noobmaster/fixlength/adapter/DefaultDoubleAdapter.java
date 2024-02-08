package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

/**
 * Default adapter to format and parse value between {@link String} and {@link Double}.
 *
 * @author Noob Master
 * @see Double
 */
class DefaultDoubleAdapter extends AbstractNumberFormatAdapter<Double> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultDoubleAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link Double}
     *
     * @param value number in string value
     * @return double value
     */
    @Override
    protected Double parseNumber(String value) {
        return Double.parseDouble(value);
    }
}
