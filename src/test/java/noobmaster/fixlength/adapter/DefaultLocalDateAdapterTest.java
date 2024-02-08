package noobmaster.fixlength.adapter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test cases about {@link DefaultLocalDateAdapter}.
 *
 * @author Noob Master
 */
class DefaultLocalDateAdapterTest {

    @DisplayName("toDate(LocalDate)")
    @Test
    void testToDate() {
        DefaultLocalDateAdapter adapter = new DefaultLocalDateAdapter(null);

        final Date expectedResult = createDate("2024-02-29");
        final Date actualResult = adapter.toDate(LocalDate.of(2024, 2 , 29));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("fromDate(java.util.Date)")
    @Test
    void testFromDate() {
        DefaultLocalDateAdapter adapter = new DefaultLocalDateAdapter(null);

        final LocalDate expectedResult = LocalDate.of(2024, 1, 31);
        final LocalDate actualResult = adapter.fromDate(createDate("2024-01-31"));
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