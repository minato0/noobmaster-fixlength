package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link DefaultDateAdapter}.
 *
 * @author Noob Master
 */
class DefaultDateAdapterTest {

    @DisplayName("toDate(java.util.Date)")
    @Test
    void testToDate() {
        DefaultDateAdapter adapter = new DefaultDateAdapter(null);

        final Date expectedResult = createDate("2024-02-29");
        final Date actualResult = adapter.toDate(createDate("2024-02-29"));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("fromDate(java.util.Date)")
    @Test
    void testFromDate() {
        DefaultDateAdapter adapter = new DefaultDateAdapter(null);

        final Date expectedResult = createDate("2024-01-31");
        final Date actualResult = adapter.fromDate(createDate("2024-01-31"));
        assertEquals(expectedResult, actualResult);
    }

    private static Date createDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Cannot create date", ex);
        }
    }
}