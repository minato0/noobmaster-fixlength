package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultByteAdapter}.
 *
 * @author Noob Master
 */
class DefaultByteAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultByteAdapter adapter = new DefaultByteAdapter(null);

        final byte expectedResult = (byte)100;
        final byte actualResult = adapter.parseNumber("100");
        assertEquals(expectedResult, actualResult);
    }
}