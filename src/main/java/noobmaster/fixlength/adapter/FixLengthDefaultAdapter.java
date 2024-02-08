package noobmaster.fixlength.adapter;

import java.lang.reflect.Field;
import java.math.BigDecimal;

/**
 * Default adapter for given {@link Field}.
 * It will create default adapter by field's type and formatting or parsing value using implemented behavior.
 *
 * @author Noob Master
 * @see BigDecimal
 */
public class FixLengthDefaultAdapter extends FixLengthJavaTypeAdapter<Object> {

    /**
     * Default adapter by field.
     */
    private final FixLengthAdapter adapter;

    /**
     * Create default adapter by field.
     *
     * @param field class's field
     */
    public FixLengthDefaultAdapter(Field field) {
        FixLengthDefaultAdapterFactory factory = FixLengthDefaultAdapterFactory.getInstance();
        this.adapter = factory.createAdapter(field);
    }

    /**
     * Format value to string using default adapter behavior.
     *
     * @param value field's value
     * @return string value
     */
    @Override
    protected String formatToString(Object value) {
        return this.adapter.format(value);
    }

    /**
     * Parse string value using default adapter behavior.
     *
     * @param value not null string value
     * @return object value
     */
    @Override
    protected Object parseToObject(String value) {
        return this.adapter.parse(value);
    }
}
