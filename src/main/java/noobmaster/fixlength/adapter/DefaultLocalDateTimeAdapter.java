package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Default adapter to format and parse value between {@link String} and {@link LocalDateTime}.
 *
 * @author Noob Master
 * @see LocalDateTime
 */
class DefaultLocalDateTimeAdapter extends AbstractDateFormatAdapter<LocalDateTime> {

    /**
     * Create Adapter with specified format.
     * If there is no specified format it will use 'yyyyMMdd:HHmmss' as default pattern.
     *
     * @param format annotation config
     */
    DefaultLocalDateTimeAdapter(FixLengthDateFormat format) {
        super(format, "yyyyMMdd:HHmmss");
    }

    /**
     * Convert {@link LocalDateTime} to {@link Date}.
     *
     * @param date date value
     * @return java.util.Date value
     */
    @Override
    protected Date toDate(LocalDateTime date) {
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Convert {@link Date} to {@link LocalDateTime}.
     *
     * @param date java.util.Date value
     * @return a date
     */
    @Override
    protected LocalDateTime fromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
