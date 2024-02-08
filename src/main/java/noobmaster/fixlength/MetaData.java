package noobmaster.fixlength;

import noobmaster.fixlength.adapter.FixLengthJavaTypeAdapter;
import noobmaster.fixlength.annotation.FixLength;

import java.lang.reflect.Field;

/**
 * A metadata to keep field, and it's fixed length configuration.
 *
 * @author Noob Master
 */
class MetaData {

    private final int position;

    private final int length;

    private final char padChar;

    private final Align align;

    private final Class<? extends FixLengthJavaTypeAdapter> adapter;

    private final Field field;

    /**
     * Create metadata with only annotation.
     *
     * @param annotation configuration annotation
     */
    MetaData(FixLength annotation) {
        this(annotation, null);
    }

    /**
     * Create metadata with annotation and field.
     *
     * @param annotation configuration annotation
     * @param field java field
     */
    MetaData(FixLength annotation, Field field) {
        this.position = annotation.position();
        this.length = annotation.length();
        this.padChar = annotation.padChar();
        this.align = annotation.align();
        this.adapter = annotation.adapter();
        this.field = field;
    }

    int getPosition() {
        return this.position;
    }

    int getLength() {
        return this.length;
    }

    char getPadChar() {
        return this.padChar;
    }

    Align getAlign() {
        return this.align;
    }

    Class<? extends FixLengthJavaTypeAdapter> getAdapter() {
        return this.adapter;
    }

    Field getField() {
        return this.field;
    }
}
