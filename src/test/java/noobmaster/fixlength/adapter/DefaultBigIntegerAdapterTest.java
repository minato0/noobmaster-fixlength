package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultBigIntegerAdapter}.
 *
 * @author Noob Master
 */
class DefaultBigIntegerAdapterTest {

    @DisplayName("parseNumber(String)")
    @Test
    void testParseNumber() {
        DefaultBigIntegerAdapter adapter = new DefaultBigIntegerAdapter(null);

        final BigInteger expectedResult = new BigInteger("1000000");
        final BigInteger actualResult = adapter.parseNumber("1000000");
        assertEquals(expectedResult, actualResult);
    }
}