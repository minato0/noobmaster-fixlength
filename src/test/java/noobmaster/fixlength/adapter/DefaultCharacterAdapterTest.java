package noobmaster.fixlength.adapter;

import noobmaster.fixlength.exception.FixLengthParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test cases about {@link DefaultCharacterAdapter}.
 *
 * @author Noob Master
 */
class DefaultCharacterAdapterTest {

    @DisplayName("formatToString(String)")
    @Test
    void testFormatToString() {
        DefaultCharacterAdapter adapter = new DefaultCharacterAdapter();

        final String expectedResult = "A";
        final String actualResult = adapter.formatToString('A');
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("parseToObject(String)")
    @Test
    void testParseToObject() {
        DefaultCharacterAdapter adapter = new DefaultCharacterAdapter();

        final char expectedResult = 'B';
        final char actualResult = adapter.parseToObject("B");
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("parseToObject(String) - Too long string")
    @Test
    void testParseToObject_WithTooLongString() {
        DefaultCharacterAdapter adapter = new DefaultCharacterAdapter();

        try {
            adapter.parseToObject("Test");
            fail("It should throw FixLengthParseException");
        } catch (FixLengthParseException ex) {
            // Success
        }
    }
}