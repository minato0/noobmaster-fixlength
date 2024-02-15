package noobmaster.fixlength.adapter;

import noobmaster.fixlength.Align;
import noobmaster.fixlength.exception.FixLengthParseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link DefaultEnumAdapter}.
 *
 * @author Noob Master
 */
class DefaultEnumAdapterTest {

    @DisplayName("formatToString(Enum)")
    @Test
    void testFormatToString() {
        DefaultEnumAdapter adapter = new DefaultEnumAdapter(Align.class);
        final String expectedResult = "LEFT";
        final String actualResult = adapter.formatToString(Align.LEFT);
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("parseToObject(String)")
    @Test
    void testParseToObject() {
        DefaultEnumAdapter adapter = new DefaultEnumAdapter(Align.class);
        final Align expectedResult = Align.RIGHT;
        final Object actualResult = adapter.parseToObject("RIGHT");
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("parseToObject(String) - Unknown value")
    @Test
    void testParseToObject_WithUnknownValue() {
        DefaultEnumAdapter adapter = new DefaultEnumAdapter(Align.class);
        try {
            adapter.parseToObject("TEST");
            fail("It should throw FixLengthParseException");
        } catch (FixLengthParseException ex) {
            // Success
        }
    }
}