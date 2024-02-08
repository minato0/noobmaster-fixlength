package noobmaster.fixlength;

import noobmaster.fixlength.exception.FixLengthException;
import noobmaster.fixlength.exception.FixLengthFormatException;
import noobmaster.fixlength.exception.FixLengthParseException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * A utilities class about java reflection.
 *
 * @author Noob Master
 */
class ReflectionUtils {

    /**
     * Create a class instance using default public constructor.
     *
     * @param aClass a class
     * @return an instance of given class
     * @param <C> class type
     * @throws FixLengthException given class does not have default public constructor
     */
    <C> C newInstance(Class<C> aClass) {
        Constructor<C> constructor;
        try {
            constructor = aClass.getDeclaredConstructor();
        } catch (NoSuchMethodException | SecurityException ex) {
            throw new FixLengthException("No public default constructor found for '%s'".formatted(aClass.getName()));
        }
        try {
            return constructor.newInstance();
        } catch (Exception ex) {
            throw new FixLengthException("No public default constructor found for '%s'".formatted(aClass.getName()), ex);
        }
    }

    /**
     * Set value to the object's field.
     *
     * @param field field to set value
     * @param object object to set value
     * @param value value to set
     *
     * @throws FixLengthParseException cannot set value to field
     */
    void setFieldValue(Field field, Object object, Object value) {
        try {
            field.set(object, value);
        } catch (Exception ex) {
            throw new FixLengthParseException("Cannot set value to object on field %s, value %s".formatted(field.getName(), value), ex);
        }
    }

    /**
     * Get value from object's field.
     *
     * @param field field to get value
     * @param object object to get value
     * @return field's value
     *
     * @throws FixLengthFormatException cannot get value from field
     */
    Object getFieldValue(Field field, Object object) {
        try {
            return field.get(object);
        } catch (Exception ex) {
            throw new FixLengthFormatException("Cannot get value from object on field %s".formatted(field.getName()), ex);
        }
    }
}
