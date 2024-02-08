package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

import java.math.BigInteger;

/**
 * Default adapter to format and parse value between {@link String} and {@link BigInteger}.
 *
 * @author Noob Master
 * @see BigInteger
 */
class DefaultBigIntegerAdapter extends AbstractNumberFormatAdapter<BigInteger> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultBigIntegerAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link BigInteger}
     *
     * @param value number in string value
     * @return big integer value
     */
    @Override
    protected BigInteger parseNumber(String value) {
        return new BigInteger(value);
    }
}
