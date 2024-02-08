package noobmaster.fixlength;

import noobmaster.fixlength.adapter.FixLengthDefaultAdapter;
import noobmaster.fixlength.exception.FixLengthException;
import noobmaster.fixlength.exception.FixLengthFormatException;
import noobmaster.fixlength.exception.FixLengthParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link ReflectionUtils}.
 *
 * @author Noob Master
 */
class ReflectionUtilsTest {

    private ReflectionUtils reflectionUtils;
    private TestCase testCase;
    private Field field;

    @BeforeEach
    void init() throws NoSuchFieldException, SecurityException {
        this.reflectionUtils = new ReflectionUtils();
        this.testCase = new TestCase();
        this.field = TestCase.class.getDeclaredField("name");
        this.field.setAccessible(true);
    }

    @DisplayName("newInstance(Class) - No public default constructor")
    @ParameterizedTest
    @ValueSource(classes = {Math.class, FixLengthDefaultAdapter.class})
    void testNewInstance_WithNoPublicDefaultConstructor(Class<?> aClass) {
        try {
            this.reflectionUtils.newInstance(aClass);
            fail("It should throw FixLengthException");
        } catch (FixLengthException ex) {
            // Success
        }
    }

    @DisplayName("setFieldValue(Field, Object, Object)")
    @Test
    void testSetFieldValue() {
        this.reflectionUtils.setFieldValue(this.field, this.testCase, "Test");

        final String expectedResult = "Test";
        final String actualResult = testCase.getName();
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("setFieldValue(Field, Object, Object) - Cannot set value")
    @Test
    void testSetFieldValue_WithCannotSetDate() {
        this.field.setAccessible(false);
        try {
            this.reflectionUtils.setFieldValue(this.field, this.testCase, "Test");
            fail("It should throw FixLengthParseException");
        } catch (FixLengthParseException ex) {
            // Success
        }
    }

    @DisplayName("getFieldValue(Field, Object)")
    @Test
    void testGetFieldValue() {
        this.testCase.setName("Name");

        final String expectedResult = "Name";
        final String actualResult = (String)this.reflectionUtils.getFieldValue(this.field, this.testCase);
        assertEquals(expectedResult, actualResult);
    }

    @DisplayName("getFieldValue(Field, Object) - Cannot get value")
    @Test
    void testGetFieldValue_WithCannotGetDate() {
        this.testCase.setName("Name");

        this.field.setAccessible(false);
        try {
            this.reflectionUtils.getFieldValue(this.field, this.testCase);
            fail("It should throw FixLengthFormatException");
        } catch (FixLengthFormatException ex) {
            // Success
        }
    }

    static class TestCase {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}