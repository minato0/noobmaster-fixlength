package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthBooleanFormat;
import noobmaster.fixlength.exception.FixLengthParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.annotation.Annotation;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link DefaultBooleanAdapter}.
 *
 * @author Noob Master
 */
class DefaultBooleanAdapterTest {

    @DisplayName("formatToString(Boolean)")
    @ParameterizedTest
    @MethodSource("testFormatToStringTestCases")
    void testFormatToString(Boolean value, FixLengthBooleanFormat format, final String expectedResult) {
        DefaultBooleanAdapter adapter = new DefaultBooleanAdapter(format);

        final String actualResult = adapter.formatToString(value);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> testFormatToStringTestCases() {
        return Stream.of(
                Arguments.of(true, null, "1"),
                Arguments.of(false, null, "0"),
                Arguments.of(true, createYNFormat(), "Y"),
                Arguments.of(false, createYNFormat(), "N")
        );
    }

    @DisplayName("parseToObject(String)")
    @ParameterizedTest
    @MethodSource("testParseToObjectTestCases")
    void testParseToObject(String value, FixLengthBooleanFormat format, final Boolean expectedResult) {
        DefaultBooleanAdapter adapter = new DefaultBooleanAdapter(format);

        final Boolean actualResult = adapter.parseToObject(value);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> testParseToObjectTestCases() {
        return Stream.of(
                Arguments.of("1", null, true),
                Arguments.of("0", null, false),
                Arguments.of("y", createYNFormat(), true),
                Arguments.of("n", createYNFormat(), false)
        );
    }

    @DisplayName("parseToObject(String) - Unknown string value")
    @Test
    void testParseToObject_WithUnknownStringValue() {
        DefaultBooleanAdapter adapter = new DefaultBooleanAdapter(createYNFormat());

        try {
            adapter.parse("A");
            fail("It should throw FixLengthParseException");
        } catch (FixLengthParseException ex) {
            // Success
        }
    }

    private static FixLengthBooleanFormat createYNFormat() {
        return new FixLengthBooleanFormat() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return FixLengthBooleanFormat.class;
            }

            @Override
            public String trueValue() {
                return "Y";
            }

            @Override
            public String falseValue() {
                return "N";
            }

            @Override
            public boolean caseSensitive() {
                return false;
            }
        };
    }
}