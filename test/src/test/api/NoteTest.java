package test.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Alter;
import com.musicquint.api.BarTime;
import com.musicquint.api.Note;
import com.musicquint.api.Octave;
import com.musicquint.api.Pitch;
import com.musicquint.api.Step;
import com.musicquint.api.Type;

class NoteTest {

    Note quarter = new Note(new Pitch(Step.C, Octave.ONE_LINED), BarTime.QUARTER, 0, Type.QUARTER);
    Note eight = new Note(new Pitch(Step.D, Alter.SHARP, Octave.ONE_LINED), BarTime.EIGHTH, 0, Type.EIGHTH);
    Note eight2 = new Note(new Pitch(Step.D, Alter.SHARP, Octave.ONE_LINED), BarTime.EIGHTH, 0, Type.EIGHTH);
    Note half = new Note(new Pitch(Step.D, Alter.FLAT_FLAT, Octave.ONE_LINED), BarTime.HALF, 0, Type.HALF);

    @Test
    void test() {
        assertEquals(false, quarter.equals(eight));
    }

    @Test
    void test2() {
        assertEquals(false, quarter.equals(eight2));
    }

    @Test
    void test3() {
        assertEquals(false, quarter.equals(half));
    }

    @Test
    void test4() {
        assertEquals(true, eight.equals(eight2));
    }

}
