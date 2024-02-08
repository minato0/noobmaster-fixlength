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
 * @see Byte
 * @see Short
 * @see Integer
 * @see Long
 * @see Float
 * @see Double
 * @see java.math.BigInteger
 * @see java.math.BigDecimal
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FixLengthNumberFormat {

    /**
     * A number format pattern.
     *
     * @return number format
     * @see java.text.DecimalFormat
     */
    String pattern();
}
