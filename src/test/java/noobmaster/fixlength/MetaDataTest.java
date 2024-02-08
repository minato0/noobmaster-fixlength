package noobmaster.fixlength;

import noobmaster.fixlength.adapter.FixLengthDefaultAdapter;
import noobmaster.fixlength.annotation.FixLength;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link MetaData}.
 *
 * @author Noob Master
 */
class MetaDataTest {

    private FixLength annotation;
    private Field field;

    @BeforeEach
    void init() throws NoSuchFieldException, SecurityException {
        this.field = TestCase.class.getDeclaredField("name");
        this.annotation = field.getAnnotation(FixLength.class);
    }

    @DisplayName("getPosition()")
    @Test
    void testGetPosition() {
        MetaData metaData = new MetaData(this.annotation);
        final int expectedResult = 1;
        final int actualResult = metaData.getPosition();
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("getLength()")
    @Test
    void testGetLength() {
        MetaData metaData = new MetaData(this.annotation);
        final int expectedResult = 5;
        final int actualResult = metaData.getLength();
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("getPadChar()")
    @Test
    void testGetPadChar() {
        MetaData metaData = new MetaData(this.annotation);
        final char expectedResult = ' ';
        final char actualResult = metaData.getPadChar();
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("getAlign()")
    @Test
    void testGetAlign() {
        MetaData metaData = new MetaData(this.annotation);
        final Align expectedResult = Align.LEFT;
        final Align actualResult = metaData.getAlign();
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("getAdapter()")
    @Test
    void testGetAdapter() {
        MetaData metaData = new MetaData(this.annotation);
        final Class<?> expectedResult = FixLengthDefaultAdapter.class;
        final Class<?> actualResult = metaData.getAdapter();
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("getField()")
    @Test
    void testGetField() throws NoSuchFieldException, SecurityException {
        MetaData metaData = new MetaData(this.annotation, this.field);
        final Field expectedResult = TestCase.class.getDeclaredField("name");
        final Field actualResult = metaData.getField();
        assertEquals(expectedResult, actualResult);
    }

    static class TestCase {
        @FixLength(position = 1, length = 5)
        private final String name = "Test";
    }
}