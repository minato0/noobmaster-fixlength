package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

/**
 * Default adapter to format and parse value between {@link String} and {@link Float}.
 *
 * @author Noob Master
 * @see Float
 */
class DefaultFloatAdapter extends AbstractNumberFormatAdapter<Float> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultFloatAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link Float}
     *
     * @param value number in string value
     * @return float value
     */
    @Override
    protected Float parseNumber(String value) {
        return Float.parseFloat(value);
    }
}
