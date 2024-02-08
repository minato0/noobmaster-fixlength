package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;

/**
 * Default adapter to format and parse value between {@link String} and {@link Byte}.
 *
 * @author Noob Master
 * @see Byte
 */
class DefaultByteAdapter extends AbstractNumberFormatAdapter<Byte> {

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    DefaultByteAdapter(FixLengthNumberFormat format) {
        super(format);
    }

    /**
     * Parse string value into {@link Byte}
     *
     * @param value number in string value
     * @return byte value
     */
    @Override
    protected Byte parseNumber(String value) {
        return Byte.parseByte(value);
    }
}
