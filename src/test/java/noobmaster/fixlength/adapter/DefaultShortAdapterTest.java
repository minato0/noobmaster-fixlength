package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultShortAdapter}.
 *
 * @author Noob Master
 */
class DefaultShortAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultShortAdapter adapter = new DefaultShortAdapter(null);

        final short expectedResult = (short)1000;
        final short actualResult = adapter.parseNumber("1000");
        assertEquals(expectedResult, actualResult);
    }
}