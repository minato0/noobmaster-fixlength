package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthNumberFormat;
import noobmaster.fixlength.exception.FixLengthParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link AbstractNumberFormatAdapter}.
 *
 * @author Noob Master
 */
class AbstractNumberFormatAdapterTest {


    @DisplayName("formatToString(Number)")
    @ParameterizedTest
    @MethodSource("testFormatToStringTestCases")
    void testFormatToString(double value, FixLengthNumberFormat format, final String expectedResult) {
        DefaultDoubleAdapter adapter = new DefaultDoubleAdapter(format);

        final String actualResult = adapter.formatToString(value);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> testFormatToStringTestCases() {
        return Stream.of(
                Arguments.of(1000.567, null, "1000.567"),
                Arguments.of(1000.567, createNumberFormat("#,##0.00"), "1,000.57")
        );
    }

    @DisplayName("parseToObject(String)")
    @ParameterizedTest
    @MethodSource("testParseToObjectTestCases")
    void testParseToObject(String value, FixLengthNumberFormat format, final double expectedResult) {
        DefaultDoubleAdapter adapter = new DefaultDoubleAdapter(format);

        final double actualResult = adapter.parseToObject(value);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> testParseToObjectTestCases() {
        return Stream.of(
                Arguments.of("1000.567", null, 1000.567),
                Arguments.of("1,000.56", createNumberFormat("#,##0.00"), 1000.56)
        );
    }

    @DisplayName("parseToObject(String) - Invalid number format")
    @ParameterizedTest
    @MethodSource("testParseToObject_WithInvalidFormatTestCases")
    void testParseToObject_WithInvalidFormat(String value, FixLengthNumberFormat format) {
        DefaultDoubleAdapter adapter = new DefaultDoubleAdapter(format);

        try {
            adapter.parseToObject(value);
            fail("It should throw FixLengthParseException");
        } catch (FixLengthParseException ex) {
            // Success
        }
    }

    private static Stream<Arguments> testParseToObject_WithInvalidFormatTestCases() {
        return Stream.of(
                Arguments.of("A", null),
                Arguments.of("A1", createNumberFormat("0.00"))
        );
    }

    private static FixLengthNumberFormat createNumberFormat(String pattern) {
        return new FixLengthNumberFormat() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return FixLengthNumberFormat.class;
            }

            @Override
            public String pattern() {
                return pattern;
            }
        };
    }
}