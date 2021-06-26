package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Alter;
import com.musicquint.api.Octave;
import com.musicquint.api.Pitch;
import com.musicquint.api.Step;

class PitchTest {

    Pitch pitch1 = new Pitch.Builder().step(Step.C).octave(Octave.ONE_LINED).build();
    Pitch pitch2 = new Pitch.Builder().step(Step.C).octave(Octave.ONE_LINED).build();
    Pitch pitch3 = new Pitch.Builder().step(Step.D).alter(Alter.SHARP).octave(Octave.ONE_LINED).build();
    Pitch pitch4 = new Pitch.Builder().step(Step.D).alter(Alter.FLAT_FLAT).octave(Octave.ONE_LINED).build();

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
