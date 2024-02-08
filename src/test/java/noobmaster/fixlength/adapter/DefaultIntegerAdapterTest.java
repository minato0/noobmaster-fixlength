package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link DefaultIntegerAdapter}.
 *
 * @author Noob Master
 */
class DefaultIntegerAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultIntegerAdapter adapter = new DefaultIntegerAdapter(null);

        final int expectedResult = 1000;
        final int actualResult = adapter.parseNumber("1000");
        assertEquals(expectedResult, actualResult);
    }
}