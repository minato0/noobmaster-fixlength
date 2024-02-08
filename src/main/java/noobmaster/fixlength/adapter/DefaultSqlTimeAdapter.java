package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;

import java.util.Date;
import java.sql.Time;

/**
 * Default adapter to format and parse value between {@link String} and {@link Time}.
 *
 * @author Noob Master
 * @see Time
 */
class DefaultSqlTimeAdapter extends AbstractDateFormatAdapter<Time> {

    /**
     * Create Adapter with specified format.
     * If there is no specified format it will use 'HHmmss' as default pattern.
     *
     * @param format annotation config
     */
    DefaultSqlTimeAdapter(FixLengthDateFormat format) {
        super(format, "HHmmss");
    }

    /**
     * Convert {@link Time} to {@link Date}.
     *
     * @param date date value
     * @return java.util.Date value
     */
    @Override
    protected Date toDate(Time date) {
        return new Date(date.getTime());
    }

    /**
     * Convert {@link Date} to {@link Time}.
     *
     * @param date java.util.Date value
     * @return a date
     */
    @Override
    protected Time fromDate(Date date) {
        return new Time(date.getTime());
    }
}
