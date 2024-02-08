package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultLongAdapter}.
 *
 * @author Noob Master
 */
class DefaultLongAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultLongAdapter adapter = new DefaultLongAdapter(null);

        final long expectedResult = 1000L;
        final long actualResult = adapter.parseNumber("1000");
        assertEquals(expectedResult, actualResult);
    }
}