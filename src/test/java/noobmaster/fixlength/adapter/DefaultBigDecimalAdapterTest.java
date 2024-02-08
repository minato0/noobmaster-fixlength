package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultBigDecimalAdapter}.
 *
 * @author Noob Master
 */
class DefaultBigDecimalAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultBigDecimalAdapter adapter = new DefaultBigDecimalAdapter(null);

        final BigDecimal expectedResult = new BigDecimal("1000000.123456789");
        final BigDecimal actualResult = adapter.parseNumber("1000000.123456789");
        assertEquals(expectedResult, actualResult);
    }
}