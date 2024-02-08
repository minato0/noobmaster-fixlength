package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultSqlTimestampAdapter}.
 *
 * @author Noob Master
 */
class DefaultSqlTimestampAdapterTest {

    @DisplayName("toDate(java.sql.Timestamp)")
    @Test
    void testToDate() {
        DefaultSqlTimestampAdapter adapter = new DefaultSqlTimestampAdapter(null);

        final Date expectedResult = createDate("2024-02-29 12:00:00");
        final Date actualResult = adapter.toDate(createSqlTimestamp("2024-02-29 12:00:00"));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("fromDate(java.util.Date)")
    @Test
    void testFromDate() {
        DefaultSqlTimestampAdapter adapter = new DefaultSqlTimestampAdapter(null);

        final Timestamp expectedResult = createSqlTimestamp("2024-01-31 09:45:00");
        final Timestamp actualResult = adapter.fromDate(createDate("2024-01-31 09:45:00"));
        assertEquals(expectedResult, actualResult);
    }

    private static Date createDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Cannot create date", ex);
        }
    }

    private static Timestamp createSqlTimestamp(String date) {
        return new Timestamp(createDate(date).getTime());
    }
}