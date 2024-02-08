package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultDoubleAdapter}.
 *
 * @author Noob Master
 */
class DefaultDoubleAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultDoubleAdapter adapter = new DefaultDoubleAdapter(null);

        final double expectedResult = 500.25;
        final double actualResult = adapter.parseNumber("500.25");
        assertEquals(expectedResult, actualResult);
    }
}