package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;
import noobmaster.fixlength.exception.FixLengthException;
import noobmaster.fixlength.exception.FixLengthParseException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * Abstract adapter to format and parse value between {@link String} and date.
 *
 * @author Noob Master
 */
abstract class AbstractDateFormatAdapter<T> extends FixLengthJavaTypeAdapter<T> {

    /**
     * Date format pattern.
     */
    private final String pattern;

    /**
     * Locale to create date format.
     */
    private final Locale locale;

    /**
     * Create Adapter with specified format.
     *
     * @param format annotation config
     */
    protected AbstractDateFormatAdapter(FixLengthDateFormat format, final String defaultDatePattern) {
        if (Objects.isNull(format)) {
            this.pattern = defaultDatePattern;
            this.locale = Locale.getDefault();
        }
        else {
            this.pattern = format.pattern();
            String localeConfig = format.locale();
            if (localeConfig.isEmpty()) {
                this.locale = Locale.getDefault();
            }
            else {
                String[] localeString = localeConfig.split("_");
                if (localeString.length != 2) {
                    throw new FixLengthException("Invalid locale value '%s'".formatted(localeConfig));
                }
                this.locale = new Locale(localeString[0], localeString[1]);
            }
        }
    }

    /**
     * Format date value to string.
     *
     * @param value date value
     * @return string value
     */
    @Override
    protected String formatToString(T value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.pattern, this.locale);
        return dateFormat.format(toDate(value));
    }

    /**
     * Parse string value to date.
     *
     * @param value not null string value
     * @return date value
     * @throws FixLengthParseException if parsing invalid date format
     */
    @Override
    protected T parseToObject(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(this.pattern, this.locale);
        try {
            Date aDate = dateFormat.parse(value);
            return fromDate(aDate);
        } catch (ParseException ex) {
            throw new FixLengthParseException("Cannot parse '%s' into date".formatted(value), ex);
        }
    }

    /**
     * Child class must implement how to cast given date into {@link Date}.
     *
     * @param date date to be cast
     * @return a java util date value
     */
    protected abstract Date toDate(T date);

    /**
     * Child class must implement how to cast {@link Date} value into generic type.
     *
     * @param date date to be cast
     * @return generic type date value
     */
    protected abstract T fromDate(Date date);
}
