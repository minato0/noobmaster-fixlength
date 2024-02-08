package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthBooleanFormat;
import noobmaster.fixlength.annotation.FixLengthDateFormat;
import noobmaster.fixlength.annotation.FixLengthNumberFormat;
import noobmaster.fixlength.exception.FixLengthException;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A factory class to create {@link FixLengthAdapter} from {@link Field}.
 * This class will contain all classes which have supported default adapter
 *
 * @author Noob Master
 * @see String
 * @see Character
 * @see Byte
 * @see Short
 * @see Integer
 * @see Long
 * @see Float
 * @see Double
 * @see BigInteger
 * @see BigDecimal
 * @see java.util.Date
 * @see java.sql.Date
 * @see java.sql.Time
 * @see java.sql.Timestamp
 * @see java.time.LocalDateTime
 * @see java.time.LocalTime
 * @see java.time.LocalDateTime
 */
final class FixLengthDefaultAdapterFactory {

    /**
     * Singleton instance of this factory.
     */
    private static FixLengthDefaultAdapterFactory instance;

    /**
     * A dummy object for singleton thread safe.
     */
    private static final Object mutex = new Object();

    /**
     * All adapters for support classes.
     */
    private final Map<Class<?>, FixLengthDefaultAdapterConsumer> adapters;

    /**
     * Create factory object which specified default supported classes.
     */
    private FixLengthDefaultAdapterFactory() {
        this.adapters = new HashMap<>();

        // Text
        this.adapters.put(String.class, f -> new DefaultStringAdapter());
        this.adapters.put(Character.class, f -> new DefaultCharacterAdapter());

        // Boolean
        this.adapters.put(Boolean.class, f -> new DefaultBooleanAdapter(f.getAnnotation(FixLengthBooleanFormat.class)));

        // Number
        this.adapters.put(Byte.class, f -> new DefaultByteAdapter(f.getAnnotation(FixLengthNumberFormat.class)));
        this.adapters.put(Short.class, f -> new DefaultShortAdapter(f.getAnnotation(FixLengthNumberFormat.class)));
        this.adapters.put(Integer.class, f -> new DefaultIntegerAdapter(f.getAnnotation(FixLengthNumberFormat.class)));
        this.adapters.put(Long.class, f -> new DefaultLongAdapter(f.getAnnotation(FixLengthNumberFormat.class)));
        this.adapters.put(Float.class, f -> new DefaultFloatAdapter(f.getAnnotation(FixLengthNumberFormat.class)));
        this.adapters.put(Double.class, f -> new DefaultDoubleAdapter(f.getAnnotation(FixLengthNumberFormat.class)));
        this.adapters.put(BigInteger.class, f -> new DefaultBigIntegerAdapter(f.getAnnotation(FixLengthNumberFormat.class)));
        this.adapters.put(BigDecimal.class, f -> new DefaultBigDecimalAdapter(f.getAnnotation(FixLengthNumberFormat.class)));

        // Date and Time
        this.adapters.put(java.util.Date.class, f -> new DefaultDateAdapter(f.getAnnotation(FixLengthDateFormat.class)));
        this.adapters.put(Date.class, f -> new DefaultSqlDateAdapter(f.getAnnotation(FixLengthDateFormat.class)));
        this.adapters.put(Time.class, f -> new DefaultSqlTimeAdapter(f.getAnnotation(FixLengthDateFormat.class)));
        this.adapters.put(Timestamp.class, f -> new DefaultSqlTimestampAdapter(f.getAnnotation(FixLengthDateFormat.class)));
        this.adapters.put(LocalDate.class, f -> new DefaultLocalDateAdapter(f.getAnnotation(FixLengthDateFormat.class)));
        this.adapters.put(LocalTime.class, f -> new DefaultLocalTimeAdapter(f.getAnnotation(FixLengthDateFormat.class)));
        this.adapters.put(LocalDateTime.class, f -> new DefaultLocalDateTimeAdapter(f.getAnnotation(FixLengthDateFormat.class)));
    }

    /**
     * Getting a single instance of this factory.
     *
     * @return singleton instance of this factory
     */
    public static FixLengthDefaultAdapterFactory getInstance() {
        FixLengthDefaultAdapterFactory result = instance;
        if (Objects.isNull(result)) {
            synchronized (mutex) {
                result = instance;
                if (Objects.isNull(result))
                    instance = result = new FixLengthDefaultAdapterFactory();
            }
        }
        return result;
    }

    /**
     * Creating {@link FixLengthAdapter} from given {@link Field}.
     *
     * @param field class's field
     * @return given field's adapter
     */
    public FixLengthAdapter createAdapter(Field field) {
        FixLengthDefaultAdapterConsumer adapterConsumer = this.adapters.get(field.getType());
        if (Objects.isNull(adapterConsumer)) {
            throw new FixLengthException("Field type '%s' does not support by default.".formatted(field.getType().getName()));
        }
        else {
            return adapterConsumer.accept(field);
        }
    }
}
