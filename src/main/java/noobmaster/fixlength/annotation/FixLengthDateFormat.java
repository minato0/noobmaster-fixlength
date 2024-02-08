package noobmaster.fixlength.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to set number format pattern.
 * This annotation will be used if using {@link noobmaster.fixlength.adapter.FixLengthDefaultAdapter} only
 *
 * @author Noob Master
 * @see noobmaster.fixlength.adapter.FixLengthDefaultAdapter
 * @see java.util.Date
 * @see java.sql.Date
 * @see java.sql.Time
 * @see java.sql.Timestamp
 * @see java.time.LocalDateTime
 * @see java.time.LocalTime
 * @see java.time.LocalDateTime
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FixLengthDateFormat {

    /**
     * A date format pattern.
     *
     * @return date format
     * @see java.text.SimpleDateFormat
     */
    String pattern();

    /**
     * A locale string in format {language}_{COUNTRY} eg. en_US.
     * The default value is using system locale.
     *
     * @return locale string
     * @see java.util.Locale
     */
    String locale() default "";
}
