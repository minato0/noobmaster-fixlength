package noobmaster.fixlength.adapter;

import noobmaster.fixlength.Align;
import noobmaster.fixlength.exception.FixLengthException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link FixLengthDefaultAdapterFactory}.
 *
 * @author Noob Master
 */
class FixLengthDefaultAdapterFactoryTest {

    @DisplayName("createAdapter(Field)")
    @ParameterizedTest
    @MethodSource("testCreateAdapterTestCases")
    void testCreateAdapter(Field field, final Class<? extends FixLengthJavaTypeAdapter<?>> expectedResult) {
        final FixLengthAdapter adapter = FixLengthDefaultAdapterFactory.getInstance().createAdapter(field);

        assertEquals(expectedResult, adapter.getClass());
    }

    private static Stream<Arguments> testCreateAdapterTestCases() throws NoSuchFieldException, SecurityException {
        return Stream.of(
                Arguments.of(getField("stringField"), DefaultStringAdapter.class),
                Arguments.of(getField("characterField"), DefaultCharacterAdapter.class),
                Arguments.of(getField("booleanField"), DefaultBooleanAdapter.class),
                Arguments.of(getField("byteField"), DefaultByteAdapter.class),
                Arguments.of(getField("shortField"), DefaultShortAdapter.class),
                Arguments.of(getField("integerField"), DefaultIntegerAdapter.class),
                Arguments.of(getField("longField"), DefaultLongAdapter.class),
                Arguments.of(getField("floatField"), DefaultFloatAdapter.class),
                Arguments.of(getField("doubleField"), DefaultDoubleAdapter.class),
                Arguments.of(getField("bigIntegerField"), DefaultBigIntegerAdapter.class),
                Arguments.of(getField("bigDecimalField"), DefaultBigDecimalAdapter.class),
                Arguments.of(getField("dateField"), DefaultDateAdapter.class),
                Arguments.of(getField("sqlDateField"), DefaultSqlDateAdapter.class),
                Arguments.of(getField("sqlTimeField"), DefaultSqlTimeAdapter.class),
                Arguments.of(getField("sqlTimestampField"), DefaultSqlTimestampAdapter.class),
                Arguments.of(getField("localDateField"), DefaultLocalDateAdapter.class),
                Arguments.of(getField("localTimeField"), DefaultLocalTimeAdapter.class),
                Arguments.of(getField("localDateTimeField"), DefaultLocalDateTimeAdapter.class),
                Arguments.of(getField("enumField"), DefaultEnumAdapter.class)
        );
    }

    @DisplayName("createAdapter(Field) - Support default support type")
    @Test
    void testCreateAdapter_WithNotDefaultSupportType() throws NoSuchFieldException, SecurityException {
        FixLengthDefaultAdapterFactory factory = FixLengthDefaultAdapterFactory.getInstance();
        Field field = getField("listField");

        assertThrows(FixLengthException.class, () -> factory.createAdapter(field));
    }

    private static Field getField(String name) throws NoSuchFieldException, SecurityException {
        return TestCase.class.getDeclaredField(name);
    }

    static class TestCase {
        private String stringField;
        private Character characterField;
        private Boolean booleanField;
        private Byte byteField;
        private Short shortField;
        private Integer integerField;
        private Long longField;
        private Float floatField;
        private Double doubleField;
        private BigInteger bigIntegerField;
        private BigDecimal bigDecimalField;
        private java.util.Date dateField;
        private Date sqlDateField;
        private Time sqlTimeField;
        private Timestamp sqlTimestampField;
        private LocalDate localDateField;
        private LocalTime localTimeField;
        private LocalDateTime localDateTimeField;
        private Align enumField;
        private List<String> listField;
    }
}