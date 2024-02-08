package noobmaster.fixlength;

import noobmaster.fixlength.adapter.FixLengthJavaTypeAdapter;
import noobmaster.fixlength.annotation.FixLength;
import noobmaster.fixlength.annotation.FixLengthBooleanFormat;
import noobmaster.fixlength.annotation.FixLengthIgnore;
import noobmaster.fixlength.exception.FixLengthException;
import noobmaster.fixlength.exception.FixLengthFormatException;
import noobmaster.fixlength.exception.FixLengthParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link FixLengthMapper}.
 *
 * @author Noob Master
 */
class FixLengthMapperTest {

    @DisplayName("There is no @FixLength")
    @Test
    void testNoFixLengthAnnotation() {
        class TestCase {
            private String value;
        }

        try {
            new FixLengthMapper<>(TestCase.class);
            fail("It should throw FixLengthException");
        } catch (FixLengthException ex) {
            // Success
        }
    }

    @DisplayName("@FixLength - Invalid position")
    @Test
    void testInvalidPositionValue() {
        class TestCase {
            @FixLength(position = 0, length = 0)
            private String value;
        }

        try {
            new FixLengthMapper<>(TestCase.class);
            fail("It should throw FixLengthException");
        } catch (FixLengthException ex) {
            // Success
        }
    }

    @DisplayName("@FixLength - Duplicate position")
    @Test
    void testDuplicatePositionValue() {
        class TestCase {
            @FixLength(position = 1, length = 5)
            private String value1;

            @FixLength(position = 1, length = 5)
            private String value2;
        }

        try {
            new FixLengthMapper<>(TestCase.class);
            fail("It should throw FixLengthException");
        } catch (FixLengthException ex) {
            // Success
        }
    }

    @DisplayName("@FixLength - Missing position")
    @Test
    void testMissingPositionValue() {
        class TestCase {
            @FixLength(position = 1, length = 5)
            private String value1;

            @FixLength(position = 3, length = 5)
            private String value3;
        }

        try {
            new FixLengthMapper<>(TestCase.class);
            fail("It should throw FixLengthException");
        } catch (FixLengthException ex) {
            // Success
        }
    }

    @DisplayName("@FixLength - Invalid length")
    @Test
    void testInvalidLengthValue() {
        class TestCase {
            @FixLength(position = 1, length = 0)
            private String value;
        }

        try {
            new FixLengthMapper<>(TestCase.class);
            fail("It should throw FixLengthException");
        } catch (FixLengthException ex) {
            // Success
        }
    }

    @DisplayName("format(Object)")
    @Test
    void testFormat() {
        FixLengthMapper<TestCase> mapper = new FixLengthMapper<>(TestCase.class);
        TestCase testCase = new TestCase();
        testCase.value1 = "Data";
        testCase.value3 = 255;
        testCase.value5 = null;

        final String expectedResult = "ataD .....00255_____ ";
        final String actualResult = mapper.format(testCase);
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("format(Object) - With null object")
    @Test
    void testFormat_WithNullObject() {
        FixLengthMapper<TestCase> mapper = new FixLengthMapper<>(TestCase.class);
        assertNull(mapper.format(null));
    }

    @DisplayName("format(Object) - With too long value")
    @Test
    void testFormat_WithTooLongValue() {
        FixLengthMapper<TestCase> mapper = new FixLengthMapper<>(TestCase.class);
        TestCase testCase = new TestCase();
        testCase.value1 = "Too Long Data";

        try {
            mapper.format(testCase);
            fail("It should throw FixLengthFormatException");
        } catch (FixLengthFormatException ex) {
            // Success
        }
    }

    @DisplayName("parse(String)")
    @Test
    void testParse() {
        FixLengthMapper<TestCase> mapper = new FixLengthMapper<>(TestCase.class);
        String fixedLengthString = "Name .....00000_____Y";
        TestCase testCase = mapper.parse(fixedLengthString);

        assertAll(
                () -> assertNotNull(testCase),
                () -> assertEquals("emaN", testCase.value1),
                () -> assertNull(testCase.value3),
                () -> assertEquals(true, testCase.value5)
        );
    }

    @DisplayName("parse(String) - With Null string")
    @Test
    void testParse_WithNullString() {
        FixLengthMapper<TestCase> mapper = new FixLengthMapper<>(TestCase.class);
        assertNull(mapper.parse(null));
    }

    @DisplayName("parse(String) - With Null object")
    @Test
    void testParse_WithNotEnoughStringLength() {
        FixLengthMapper<TestCase> mapper = new FixLengthMapper<>(TestCase.class);
        String fixedLengthString = "Name .....00015";
        try {
            mapper.parse(fixedLengthString);
            fail("It should throw FixLengthParseException");
        } catch (FixLengthParseException ex) {
            // Success
        }
    }

    @FixLengthIgnore({
            @FixLength(position = 2, length = 5, padChar = '.'),
            @FixLength(position = 4, length = 5, padChar = '_')
    })
    static class TestCase {

        @FixLength(position = 1, length = 5, adapter = ReverseStringAdapter.class)
        private String value1;

        @FixLength(position = 3, length = 5, padChar = '0', align = Align.RIGHT)
        private Integer value3;

        @FixLength(position = 5, length = 1)
        @FixLengthBooleanFormat(trueValue = "Y", falseValue = "N")
        private Boolean value5;
    }

    static class ReverseStringAdapter extends FixLengthJavaTypeAdapter<String> {

        @Override
        protected String formatToString(String value) {
            return reverse(value);
        }

        @Override
        protected String parseToObject(String value) {
            return reverse(value);
        }

        private String reverse(String value) {
            return new StringBuilder(value).reverse().toString();
        }
    }
}