package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Default adapter to format and parse value between {@link String} and {@link Timestamp}.
 *
 * @author Noob Master
 * @see Timestamp
 */
class DefaultSqlTimestampAdapter extends AbstractDateFormatAdapter<Timestamp> {

    /**
     * Create Adapter with specified format.
     * If there is no specified format it will use 'yyyyMMdd:HHmmss' as default pattern.
     *
     * @param format annotation config
     */
    DefaultSqlTimestampAdapter(FixLengthDateFormat format) {
        super(format, "yyyyMMdd:HHmmss");
    }

    /**
     * Convert {@link Timestamp} to {@link Date}.
     *
     * @param date date value
     * @return java.util.Date value
     */
    @Override
    protected Date toDate(Timestamp date) {
        return new Date(date.getTime());
    }

    /**
     * Convert {@link Date} to {@link Timestamp}.
     *
     * @param date java.util.Date value
     * @return a date
     */
    @Override
    protected Timestamp fromDate(Date date) {
        return new Timestamp(date.getTime());
    }
}
