package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultLocalDateAdapter}.
 *
 * @author Noob Master
 */
class DefaultLocalTimeAdapterTest {

    @DisplayName("toDate(LocalTime)")
    @Test
    void testToDate() {
        DefaultLocalTimeAdapter adapter = new DefaultLocalTimeAdapter(null);

        final Date expectedResult = createDate("12:30:00");
        final Date actualResult = adapter.toDate(LocalTime.of(12, 30 , 0));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("fromDate(java.util.Date)")
    @Test
    void testFromDate() {
        DefaultLocalTimeAdapter adapter = new DefaultLocalTimeAdapter(null);

        final LocalTime expectedResult = LocalTime.of(9, 30, 0);
        final LocalTime actualResult = adapter.fromDate(createDate("09:30:00"));
        assertEquals(expectedResult, actualResult);
    }

    private static Date createDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException("Cannot create date", ex);
        }
    }
}