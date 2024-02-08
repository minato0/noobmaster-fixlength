package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultFloatAdapter}.
 *
 * @author Noob Master
 */
class DefaultFloatAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultFloatAdapter adapter = new DefaultFloatAdapter(null);

        final float expectedResult = 500.25f;
        final float actualResult = adapter.parseNumber("500.25");
        assertEquals(expectedResult, actualResult);
    }
}