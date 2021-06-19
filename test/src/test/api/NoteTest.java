package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Alter;
import com.musicquint.api.BarTime;
import com.musicquint.api.Note;
import com.musicquint.api.Step;
import com.musicquint.api.Type;

class NoteTest {

    Note quarter = new Note.Builder().step(Step.C).duration(BarTime.QUARTER).type(Type.QUARTER).build();
    Note eight = new Note.Builder().step(Step.D).alter(Alter.SHARP).duration(BarTime.EIGHTH).type(Type.EIGHTH).build();
    Note eight2 = new Note.Builder().step(Step.D).alter(Alter.SHARP).duration(BarTime.EIGHTH).type(Type.EIGHTH).build();
    Note half = new Note.Builder().step(Step.D).alter(Alter.FLAT_FLAT).duration(BarTime.HALF).type(Type.HALF).build();

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
