package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Alter;

class AlterTest {

    @Test
    void test() {
        assertEquals(Alter.NATURAL, Alter.parse(""));
    }

    @Test
    void testSharp() {
        assertEquals(Alter.SHARP, Alter.parse("s"));
    }

    @Test
    void testDoubleSharp() {
        assertEquals(Alter.DOUBLE_SHARP, Alter.parse("ss"));
    }

    @Test
    void testFlat() {
        assertEquals(Alter.FLAT, Alter.parse("f"));
    }

    @Test
    void testFlatFlat() {
        assertEquals(Alter.FLAT_FLAT, Alter.parse("ff"));
    }

    @Test
    void testException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Alter.parse("sf"));
        assertEquals("The input sf does not match the regular expression (?<alter>(?<sharp>s{1,2})|(?<flat>f{1,2}))?",
                e.getMessage());
    }
}
