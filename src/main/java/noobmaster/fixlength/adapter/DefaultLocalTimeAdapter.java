package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Default adapter to format and parse value between {@link String} and {@link LocalTime}.
 *
 * @author Noob Master
 * @see LocalTime
 */
class DefaultLocalTimeAdapter extends AbstractDateFormatAdapter<LocalTime> {

    /**
     * Create Adapter with specified format.
     * If there is no specified format it will use 'HHmmss' as default pattern.
     *
     * @param format annotation config
     */
    DefaultLocalTimeAdapter(FixLengthDateFormat format) {
        super(format, "HHmmss");
    }

    /**
     * Convert {@link LocalTime} to {@link Date}.
     *
     * @param date date value
     * @return java.util.Date value
     */
    @Override
    protected Date toDate(LocalTime date) {
        // java.util.Date class represents a particular moment in time, with millisecond precision since the 1st of January 1970 00:00:00 GMT
        return Date.from(date.atDate(LocalDate.of(1970, 1, 1)).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Convert {@link Date} to {@link LocalTime}.
     *
     * @param date java.util.Date value
     * @return a date
     */
    @Override
    protected LocalTime fromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
    }
}
