package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultSqlTimeAdapter}.
 *
 * @author Noob Master
 */
class DefaultSqlTimeAdapterTest {

    @DisplayName("toDate(java.sql.Time)")
    @Test
    void testToDate() {
        DefaultSqlTimeAdapter adapter = new DefaultSqlTimeAdapter(null);

        final Date expectedResult = createDate("2024-02-29 12:00:00");
        final Date actualResult = adapter.toDate(createSqlTime("2024-02-29 12:00:00"));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("fromDate(java.util.Date)")
    @Test
    void testFromDate() {
        DefaultSqlTimeAdapter adapter = new DefaultSqlTimeAdapter(null);

        final Time expectedResult = createSqlTime("2024-01-31 09:45:00");
        final Time actualResult = adapter.fromDate(createDate("2024-01-31 09:45:00"));
        assertEquals(expectedResult, actualResult);
    }

    private static java.util.Date createDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Cannot create date", ex);
        }
    }

    private static Time createSqlTime(String date) {
        return new Time(createDate(date).getTime());
    }
}