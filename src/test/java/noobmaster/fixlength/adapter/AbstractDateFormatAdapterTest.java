package noobmaster.fixlength.adapter;

import noobmaster.fixlength.annotation.FixLengthDateFormat;
import noobmaster.fixlength.exception.FixLengthException;
import noobmaster.fixlength.exception.FixLengthParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link AbstractDateFormatAdapter}.
 *
 * @author Noob Master
 */
class AbstractDateFormatAdapterTest {

    private Locale defaultLocale;

    @BeforeEach
    void init() {
        this.defaultLocale = Locale.getDefault();
        Locale.setDefault(Locale.US);
    }

    @AfterEach
    void tearDown() {
        Locale.setDefault(this.defaultLocale);
    }

    @DisplayName("Invalid locale string")
    @Test
    void testConstructor_WithInvalidLocaleString() {
        FixLengthDateFormat format = createDateFormat("yyyyMMdd", "en_US_AA");
        assertThrows(FixLengthException.class, () -> new DefaultLocalDateAdapter(format));
    }

    @DisplayName("formatToString(LocalDate)")
    @Test
    void testFormatToString() {
        DefaultLocalDateAdapter adapter = new DefaultLocalDateAdapter(createDateFormat("yyyy-MM-dd", "th_TH"));

        final String expectedResult = "2567-01-31";
        final String actualResult = adapter.formatToString(LocalDate.of(2024, 1, 31));
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("parseToObject(Date)")
    @Test
    void testParseToObject() {
        DefaultLocalDateAdapter adapter = new DefaultLocalDateAdapter(createDateFormat("yyyy-MM-dd", ""));

        final LocalDate expectedResult = LocalDate.of(2024, 2, 29);
        final LocalDate actualResult = adapter.parseToObject("2024-02-29");
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("parseToObject(Date) - Invalid date")
    @Test
    void testParseToObject_WithInvalidDate() {
        DefaultLocalDateAdapter adapter = new DefaultLocalDateAdapter(createDateFormat("yyyy-MM-dd", ""));

        try {
            adapter.parseToObject("Test");
            fail("It should throw FixLengthParseException");
        } catch (FixLengthParseException ex) {
            // Success
        }
    }

    private static FixLengthDateFormat createDateFormat(String pattern, String locale) {
        return new FixLengthDateFormat() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return FixLengthDateFormat.class;
            }

            @Override
            public String pattern() {
                return pattern;
            }

            @Override
            public String locale() {
                return locale;
            }
        };
    }
}