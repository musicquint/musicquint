package test.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Alter;
import com.musicquint.api.Octave;
import com.musicquint.api.Pitch;
import com.musicquint.api.Step;

class PitchTest {

    @Test
    void test() {
        assertEquals(new Pitch(Step.C, Alter.NATURAL, Octave.SMALL), Pitch.parse("c"));
    }

    @Test
    void test2() {
        assertEquals(new Pitch(Step.F, Alter.SHARP, Octave.TWO_LINED), Pitch.parse("fs''"));
    }

    @Test
    void test3() {
        assertEquals(new Pitch(Step.F, Alter.FLAT, Octave.GREAT), Pitch.parse("ff,"));
    }

    @Test
    void test4() {
        assertEquals(new Pitch(Step.F, Alter.FLAT_FLAT, Octave.GREAT), Pitch.parse("fff,"));
    }

    @Test
    void test5() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Pitch.parse("ffs,"));
        assertEquals("The input ffs, does not match the pattern "
                + "(?<pitch>(?<step>[a-g|A-G])(?<alter>(?<sharp>s{1,2})|(?<flat>f{1,2}))?(?<octave>(?<subsmall>,{1,3})|(?<lined>'{1,5}))?)",
                e.getMessage());
    }
}
