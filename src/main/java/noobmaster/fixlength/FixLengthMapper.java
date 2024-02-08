package noobmaster.fixlength;

import noobmaster.fixlength.adapter.FixLengthDefaultAdapter;
import noobmaster.fixlength.adapter.FixLengthJavaTypeAdapter;
import noobmaster.fixlength.annotation.FixLength;
import noobmaster.fixlength.annotation.FixLengthIgnore;
import noobmaster.fixlength.exception.FixLengthException;
import noobmaster.fixlength.exception.FixLengthFormatException;
import noobmaster.fixlength.exception.FixLengthParseException;

import java.lang.reflect.Field;
import java.util.*;

/**
 * A mapper class to format and parse between fixed length string and object.
 *
 * @author Noob Master
 * @param <T> object type
 */
public final class FixLengthMapper<T> {

    /**
     * Object's class.
     */
    private final Class<T> beanClass;

    /**
     * Mapping of field in class and it's adapter.
     */
    private final Map<Field, FixLengthJavaTypeAdapter<?>> adapters;

    /**
     * All fixed length metadata.
     */
    private final List<MetaData> allMetaData;

    /**
     * Calculated field to keep minimum length of string.
     */
    private final int requiredTotalLength;

    /**
     * Create a mapper with class.
     *
     * @param beanClass class to do mapping
     */
    public FixLengthMapper(Class<T> beanClass) {
        this.beanClass = beanClass;
        this.adapters = new HashMap<>();
        this.allMetaData = new LinkedList<>();

        initializeMetaData();
        validateMetaData();
        createAdapters();
        // Calculate required length from sum of length of all metadata
        this.requiredTotalLength = this.allMetaData.stream().map(MetaData::getLength).reduce(Integer::sum).orElse(0);
    }

    /**
     * Create metadata list containing all field and ignore position.
     * The metadata will be sort by position.
     */
    private void initializeMetaData() {
        FixLengthIgnore ignoreAnnotation = this.beanClass.getAnnotation(FixLengthIgnore.class);
        if (Objects.nonNull(ignoreAnnotation)) {
            this.allMetaData.addAll(Arrays.stream(ignoreAnnotation.value()).map(MetaData::new).toList());
        }

        for (Field field : this.beanClass.getDeclaredFields()) {
            FixLength annotation = field.getAnnotation(FixLength.class);
            if (Objects.nonNull(annotation)) {
                field.setAccessible(true);
                this.allMetaData.add(new MetaData(annotation, field));
            }
        }

        this.allMetaData.sort(Comparator.comparingInt(MetaData::getPosition));
    }

    /**
     * Validate the metadata list.
     */
    private void validateMetaData() {
        if (this.allMetaData.isEmpty()) {
            throw new FixLengthException("There is no @FixLength specified on class %s".formatted(this.beanClass.getName()));
        }

        int lastPosition = 0;
        for (MetaData metaData : this.allMetaData) {
            validateMetaData(metaData, lastPosition);
            lastPosition = metaData.getPosition();
        }
    }

    /**
     * Validate each metadata.
     * <ul>
     *     <li>position must > 0</li>
     *     <li>length must > 0</li>
     *     <li>no skip position</li>
     *     <li>no duplicate position</li>
     * </ul>
     */
    private void validateMetaData(MetaData metaData, int lastPosition) {
        final int position = metaData.getPosition();
        final int length = metaData.getLength();
        final String fieldName = Objects.nonNull(metaData.getField()) ? metaData.getField().getName() : "@FixLengthIgnore";
        if (position <= 0) {
            throw new FixLengthException("@FixLength.position on %s must be >= 1".formatted(fieldName));
        }
        if (length <= 0) {
            throw new FixLengthException("@FixLength.length on %s must be >= 1".formatted(fieldName));
        }
        final int expectedPosition = lastPosition + 1;
        if (expectedPosition < position) {
            throw new FixLengthException("There is no @FixLength.position = %d specified".formatted(lastPosition + 1));
        }
        else if (expectedPosition > position) {
            throw new FixLengthException("There is duplicate @FixLength.position = %d on %s with other field".formatted(position, fieldName));
        }
    }

    /**
     * Create adapter object for all fields
     */
    private void createAdapters() {
        for (MetaData metaData : this.allMetaData) {
            Field field = metaData.getField();
            if (Objects.nonNull(field)) {
                final FixLengthJavaTypeAdapter<?> adapter;
                if (FixLengthDefaultAdapter.class.equals(metaData.getAdapter())) {
                    adapter = new FixLengthDefaultAdapter(field);
                }
                else {
                    adapter = new ReflectionUtils().newInstance(metaData.getAdapter());
                }
                this.adapters.put(field, adapter);
            }
        }
    }

    /**
     * Format the object to fixed length string.
     * If the object is {@code null}, it will return {@code null}.
     *
     * @param object object to be formatted
     * @return fixed length string
     * @throws FixLengthFormatException cannot format object to fixed length string
     */
    public String format(T object) {
        if (Objects.isNull(object)) {
            return null;
        }
        StringBuilder formattedResult = new StringBuilder();
        for (MetaData metaData : this.allMetaData) {
            Field field = metaData.getField();
            String stringValue;

            if (Objects.isNull(field)) {
                stringValue = "";
            }
            else {
                Object fieldValue = new ReflectionUtils().getFieldValue(field, object);
                stringValue = this.adapters.get(field).format(fieldValue);
                if (stringValue.length() > metaData.getLength()) {
                    throw new FixLengthFormatException("Value too long expected %d length, but receive %s".formatted(metaData.getLength(), stringValue));
                }
            }
            stringValue = metaData.getAlign().addPadChar(stringValue, metaData.getPadChar(), metaData.getLength());
            formattedResult.append(stringValue);
        }
        return formattedResult.toString();
    }

    /**
     * Parse the fixed length string to object.
     * If the string is {@code null}, it will return {@code null}.
     *
     * @param fixedLengthString fixed length string to be parsed
     * @return object which contain values
     * @throws FixLengthParseException cannot parse fixed length string to object
     */
    public T parse(final String fixedLengthString) {
        if (Objects.isNull(fixedLengthString)) {
            return null;
        }
        if (fixedLengthString.length() < this.requiredTotalLength) {
            throw new FixLengthParseException("Required at least %d fixed length, but received '%s'".formatted(this.requiredTotalLength, fixedLengthString));
        }

        T object = new ReflectionUtils().newInstance(this.beanClass);
        char[] allCharacters = fixedLengthString.toCharArray();
        int currentIndex = 0;
        for (MetaData metaData : this.allMetaData) {
            final int length = metaData.getLength();
            Field field = metaData.getField();

            if (Objects.nonNull(field)) {
                String stringValue = getStringValue(allCharacters, currentIndex, length);
                stringValue = metaData.getAlign().removePadChar(stringValue, metaData.getPadChar());
                Object objectValue = this.adapters.get(field).parse(stringValue);
                new ReflectionUtils().setFieldValue(field, object, objectValue);
            }
            currentIndex += length;
        }
        return object;
    }

    /**
     * Get string value from char[] by given start index and length.
     *
     * @param characters array of character
     * @param startIndex start index to get string
     * @param length length of string to be retrieved
     * @return part of string value from characters
     */
    private String getStringValue(char[] characters, int startIndex, int length) {
        StringBuilder stringValue = new StringBuilder();
        final int endIndex = startIndex + length;
        for (int i=startIndex ; i<endIndex ; i++) {
            stringValue.append(characters[i]);
        }
        return stringValue.toString();
    }
}
