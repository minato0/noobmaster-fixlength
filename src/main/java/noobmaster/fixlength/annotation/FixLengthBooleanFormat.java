package noobmaster.fixlength.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * An annotation to set boolean value.
 * This annotation will be used if using {@link noobmaster.fixlength.adapter.FixLengthDefaultAdapter} only
 *
 * @author Noob Master
 * @see noobmaster.fixlength.adapter.FixLengthDefaultAdapter
 * @see Boolean
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FixLengthBooleanFormat {

    /**
     * String value when boolean is {@code true}.
     *
     * @return true string value
     */
    String trueValue();

    /**
     * String value when boolean is {@code false}.
     *
     * @return false string value
     */
    String falseValue();

    /**
     * While parsing string to boolean checking string with case-sensitive or not.
     *
     * @return case-sensitive for checking value
     */
    boolean caseSensitive() default true;
}
