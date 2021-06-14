package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Alter;
import com.musicquint.api.Octave;
import com.musicquint.api.Pitch;
import com.musicquint.api.Step;

class PitchTest {

    Pitch pitch1 = new Pitch(Step.C, Octave.ONE_LINED);
    Pitch pitch2 = new Pitch(Step.C, Octave.ONE_LINED);
    Pitch pitch3 = new Pitch(Step.D, Alter.SHARP, Octave.ONE_LINED);
    Pitch pitch4 = new Pitch(Step.D, Alter.FLAT_FLAT, Octave.ONE_LINED);

    @Test
    void test() {
        assertEquals(true, pitch1.equals(pitch2));
    }

    @Test
    void test2() {
        assertEquals(false, pitch1.equals(pitch3));
    }

    @Test
    void test3() {
        assertEquals(false, pitch1.equals(pitch4));
    }

    @Test
    void test4() {
        assertEquals(0, pitch1.compareTo(pitch2));
    }

    @Test
    void test5() {
        assertEquals(-1, pitch1.compareTo(pitch3));
    }

    @Test
    void test6() {
        assertEquals(-1, pitch1.compareTo(pitch4));
    }

}
