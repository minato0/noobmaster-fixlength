package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link DefaultStringAdapter}.
 *
 * @author Noob Master
 */
class DefaultStringAdapterTest {

    @DisplayName("formatToString(String)")
    @Test
    void testFormatToString() {
        DefaultStringAdapter adapter = new DefaultStringAdapter();

        final String expectedResult = "A";
        final String actualResult = adapter.formatToString("A");
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("parseToObject(String)")
    @Test
    void testParseToObject() {
        DefaultStringAdapter adapter = new DefaultStringAdapter();

        final String expectedResult = "B";
        final String actualResult = adapter.parseToObject("B");
        assertEquals(expectedResult, actualResult);
    }
}