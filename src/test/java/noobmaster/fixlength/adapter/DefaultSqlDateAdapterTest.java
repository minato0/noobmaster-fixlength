package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultSqlDateAdapter}.
 *
 * @author Noob Master
 */
class DefaultSqlDateAdapterTest {

    @DisplayName("toDate(java.sql.Date)")
    @Test
    void testToDate() {
        DefaultSqlDateAdapter adapter = new DefaultSqlDateAdapter(null);

        final java.util.Date expectedResult = createDate("2024-02-29");
        final java.util.Date actualResult = adapter.toDate(createSqlDate("2024-02-29"));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("fromDate(java.util.Date)")
    @Test
    void testFromDate() {
        DefaultSqlDateAdapter adapter = new DefaultSqlDateAdapter(null);

        final Date expectedResult = createSqlDate("2024-01-31");
        final Date actualResult = adapter.fromDate(createDate("2024-01-31"));
        assertEquals(expectedResult, actualResult);
    }

    private static java.util.Date createDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Cannot create date", ex);
        }
    }

    private static Date createSqlDate(String date) {
        return new Date(createDate(date).getTime());
    }
}