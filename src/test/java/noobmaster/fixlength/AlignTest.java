package noobmaster.fixlength;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases about {@link Align}.
 *
 * @author Noob Master
 */
class AlignTest {

    @DisplayName("addPadChar(String, char, int)")
    @ParameterizedTest
    @MethodSource("testAddPadCharTestCases")
    void testAddPadChar(Align align, String str, char padChar, int length, final String expectedResult) {
        final String actualResult = align.addPadChar(str, padChar, length);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> testAddPadCharTestCases() {
        return Stream.of(
                Arguments.of(Align.LEFT, "1", ' ', 5, "1    "),
                Arguments.of(Align.RIGHT, "20", '0', 10, "0000000020"),
                Arguments.of(Align.LEFT, "TEST", '0', 4, "TEST")
        );
    }

    @DisplayName("removePadChar(String, char)")
    @ParameterizedTest
    @MethodSource("testRemovePadCharTestCases")
    void testRemovePadChar(Align align, String str, char padChar, final String expectedResult) {
        final String actualResult = align.removePadChar(str, padChar);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> testRemovePadCharTestCases() {
        return Stream.of(
                Arguments.of(Align.LEFT, "  A  ", ' ', "  A"),
                Arguments.of(Align.RIGHT, "00500", '0', "500"),
                Arguments.of(Align.LEFT, "TEST", 'S', "TEST"),
                Arguments.of(Align.LEFT, "AA", 'A', "")
        );
    }
}