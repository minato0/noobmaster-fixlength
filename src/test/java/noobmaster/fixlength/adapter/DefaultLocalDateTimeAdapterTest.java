package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultLocalDateTimeAdapter}.
 *
 * @author Noob Master
 */
class DefaultLocalDateTimeAdapterTest {

    @DisplayName("toDate(LocalDateTime)")
    @Test
    void testToDate() {
        DefaultLocalDateTimeAdapter adapter = new DefaultLocalDateTimeAdapter(null);

        final Date expectedResult = createDate("2024-02-29 12:30:00");
        final Date actualResult = adapter.toDate(LocalDateTime.of(2024, 2 , 29, 12, 30, 0));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("fromDate(java.util.Date)")
    @Test
    void testFromDate() {
        DefaultLocalDateTimeAdapter adapter = new DefaultLocalDateTimeAdapter(null);

        final LocalDateTime expectedResult = LocalDateTime.of(2024, 1, 31, 9, 30, 0);
        final LocalDateTime actualResult = adapter.fromDate(createDate("2024-01-31 09:30:00"));
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
}