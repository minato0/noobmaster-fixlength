package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * Default adapter to format and parse value between {@link String} and {@link LocalDate}.
 *
 * @author Noob Master
 * @see LocalDate
 */
class DefaultLocalDateAdapter extends AbstractDateFormatAdapter<LocalDate> {

    /**
     * Create Adapter with specified format.
     * If there is no specified format it will use 'yyyyMMdd' as default pattern.
     *
     * @param format annotation config
     */
    DefaultLocalDateAdapter(FixLengthDateFormat format) {
        super(format, "yyyyMMdd");
    }

    /**
     * Convert {@link LocalDate} to {@link Date}.
     *
     * @param date date value
     * @return java.util.Date value
     */
    @Override
    protected Date toDate(LocalDate date) {
        return Date.from(date.atTime(0, 0, 0).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Convert {@link Date} to {@link LocalDate}.
     *
     * @param date java.util.Date value
     * @return a date
     */
    @Override
    protected LocalDate fromDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
