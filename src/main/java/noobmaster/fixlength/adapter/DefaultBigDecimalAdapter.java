package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

import java.math.BigDecimal;

/**
 * Default adapter to format and parse value between {@link String} and {@link BigDecimal}.
 *
 * @author Noob Master
 * @see BigDecimal
 */
class DefaultBigDecimalAdapter extends AbstractNumberFormatAdapter<BigDecimal> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultBigDecimalAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link BigDecimal}
     *
     * @param value number in string value
     * @return big decimal value
     */
    @Override
    protected BigDecimal parseNumber(String value) {
        return new BigDecimal(value);
    }
}
