package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;

import java.sql.Date;

/**
 * Default adapter to format and parse value between {@link String} and {@link Date}.
 *
 * @author Noob Master
 * @see Date
 */
class DefaultSqlDateAdapter extends AbstractDateFormatAdapter<Date> {

    /**
     * Create Adapter with specified format.
     * If there is no specified format it will use 'yyyyMMdd' as default pattern.
     *
     * @param format annotation config
     */
    DefaultSqlDateAdapter(FixLengthDateFormat format) {
        super(format, "yyyyMMdd");
    }

    /**
     * Convert {@link Date} to {@link java.util.Date}.
     *
     * @param date date value
     * @return java.util.Date value
     */
    @Override
    protected java.util.Date toDate(Date date) {
        return new java.util.Date(date.getTime());
    }

    /**
     * Convert {@link java.util.Date} to {@link Date}.
     *
     * @param date java.util.Date value
     * @return a date
     */
    @Override
    protected Date fromDate(java.util.Date date) {
        return new Date(date.getTime());
    }
}
