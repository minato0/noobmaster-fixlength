package noobmaster.fixlength.annotation;

import noobmaster.fixlength.Align;
import noobmaster.fixlength.adapter.FixLengthDefaultAdapter;
import noobmaster.fixlength.adapter.FixLengthJavaTypeAdapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation to set on field that need to format or parse FixLength
 *
 * @author Noob Master
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FixLength {

    /**
     * Field's position start from one
     *
     * @return field position
     */
    int position();

    /**
     * Length of string for this field
     *
     * @return string length
     */
    int length();

    /**
     * Padding character if formatted string value length is less than specified length
     *
     * @return padding character
     */
    char padChar() default ' ';

    /**
     * Alignment of formatted string value.
     * If Left alignment the padding character will be append after formatted string
     * If Right alignment the padding character will be prepend before formatted string
     *
     * @return text alignment
     */
    Align align() default Align.LEFT;

    /**
     * Adapter class to specified how to format and parse value between string and object
     *
     * @return adapter class
     */
    Class<? extends FixLengthJavaTypeAdapter> adapter() default FixLengthDefaultAdapter.class;
}
