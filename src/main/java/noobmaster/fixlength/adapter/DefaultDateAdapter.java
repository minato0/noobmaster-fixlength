package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;

import java.util.Date;

/**
 * Default adapter to format and parse value between {@link String} and {@link Date}.
 *
 * @author Noob Master
 * @see Date
 */
class DefaultDateAdapter extends AbstractDateFormatAdapter<Date> {

    /**
     * Create Adapter with specified format.
     * If there is no specified format it will use 'yyyyMMdd' as default pattern.
     *
     * @param format annotation config
     */
    DefaultDateAdapter(FixLengthDateFormat format) {
        super(format, "yyyyMMdd");
    }

    /**
     * Return input value immediately.
     *
     * @param date date value
     * @return same date as input
     */
    @Override
    protected Date toDate(Date date) {
        return date;
    }

    /**
     * Return input value immediately.
     *
     * @param date date value
     * @return same date as input
     */
    @Override
    protected Date fromDate(Date date) {
        return date;
    }
}
