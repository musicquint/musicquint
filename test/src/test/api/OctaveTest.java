package test.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Octave;

class OctaveTest {

    @Test
    void testSmall() {
        assertEquals(Octave.SMALL, Octave.parse(""));
    }

    @Test
    void testGreat() {
        assertEquals(Octave.GREAT, Octave.parse(","));
    }

    @Test
    void testContra() {
        assertEquals(Octave.CONTRA, Octave.parse(",,"));
    }

    @Test
    void testSubContra() {
        assertEquals(Octave.SUBCONTRA, Octave.parse(",,,"));
    }

    @Test
    void testOneLined() {
        assertEquals(Octave.ONE_LINED, Octave.parse("'"));
    }

    @Test
    void testTwoLined() {
        assertEquals(Octave.TWO_LINED, Octave.parse("''"));
    }

    @Test
    void testThreeLined() {
        assertEquals(Octave.THREE_LINED, Octave.parse("'''"));
    }

    @Test
    void testFourLined() {
        assertEquals(Octave.FOUR_LINED, Octave.parse("''''"));
    }

    @Test
    void testFiveLined() {
        assertEquals(Octave.FIVE_LINED, Octave.parse("'''''"));
    }

    @Test
    void testException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Octave.parse(",'"));
        assertEquals(
                "The input ,' does not match the regular expression (?<octave>(?<subsmall>,{1,3})|(?<lined>'{1,5}))?",
                e.getMessage());
    }
}
