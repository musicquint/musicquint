package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Alter;
import com.musicquint.api.BarTime;
import com.musicquint.api.GraceNote;
import com.musicquint.api.Note;
import com.musicquint.api.Octave;
import com.musicquint.api.Pitch;
import com.musicquint.api.Step;
import com.musicquint.api.Type;
import com.musicquint.api.Voice;
import com.musicquint.api.Voice.PrincipalSet;

class VoiceTest {

    Note quarter = new Note.Builder().pitch(new Pitch(Step.C, Octave.ONE_LINED)).duration(BarTime.QUARTER)
            .type(Type.QUARTER).build();
    Note eight = new Note.Builder().pitch(new Pitch(Step.D, Alter.SHARP, Octave.ONE_LINED)).duration(BarTime.EIGHTH)
            .type(Type.EIGHTH).build();
    Note eight2 = new Note.Builder().pitch(new Pitch(Step.D, Alter.SHARP, Octave.ONE_LINED)).duration(BarTime.EIGHTH)
            .type(Type.EIGHTH).build();
    Note half = new Note.Builder().pitch(new Pitch(Step.D, Alter.FLAT_FLAT, Octave.ONE_LINED)).duration(BarTime.HALF)
            .type(Type.HALF).build();

    GraceNote gNote = new GraceNote.Builder().pitch(new Pitch(Step.C, Octave.ONE_LINED)).duration(BarTime.QUARTER)
            .type(Type.QUARTER).build();

    @Test
    void test1() {
        PrincipalSet itemSet = PrincipalSet.of(quarter, half, eight);
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, itemSet);
        assertEquals(BarTime.HALF, voice.length());
    }

    @Test
    void test2() {
        // eight1 and eight2 are equal
        PrincipalSet itemSet = PrincipalSet.of(quarter, half, eight, eight2);
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, itemSet);
        assertEquals(BarTime.HALF, voice.length());
        assertEquals(3, itemSet.size());
        assertEquals(true, quarter.isPitched());
        assertEquals(true, eight.isPitched());
        assertEquals(true, half.isPitched());
        assertEquals(Set.of(new Pitch(Step.C, Octave.ONE_LINED), new Pitch(Step.D, Alter.FLAT_FLAT, Octave.ONE_LINED),
                new Pitch(Step.D, Alter.SHARP, Octave.ONE_LINED)), itemSet.getPitches());
    }

    @Test
    void test3() {
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, quarter);
        voice.put(BarTime.QUARTER, half);
        voice.put(BarTime.THREE_QUARTER, eight);
        voice.put(BarTime.of(7, 2), eight);
        assertEquals(BarTime.FOUR_QUARTER, voice.length());
        assertEquals(BarTime.of(8, 7), voice.lasting(BarTime.of(13, 7)));
        assertEquals(BarTime.of(8, 7), voice.next(BarTime.of(13, 7)));
    }

    @Test
    void test4() {
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, quarter);
        voice.put(BarTime.QUARTER, half);
        voice.put(BarTime.THREE_QUARTER, eight);
        voice.put(BarTime.of(7, 2), eight);
        assertEquals(BarTime.FOUR_QUARTER, voice.length());
        assertEquals(BarTime.of(8, 7), voice.lasting(BarTime.of(13, 7)));
        assertEquals(BarTime.of(8, 7), voice.next(BarTime.of(13, 7)));
    }

    @Test
    void test5() {
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, quarter);
        voice.put(BarTime.HALF, half);
        assertEquals(BarTime.FOUR_QUARTER, voice.length(), "The length is not four quarter");
        assertEquals(BarTime.ZERO, voice.lasting(BarTime.of(8, 7)),
                "The Item in the voice has lasting effect after the duration of the item");
        assertEquals(BarTime.of(6, 7), voice.next(BarTime.of(8, 7)),
                "The next item should oocur in " + BarTime.of(6, 7));
        assertEquals(true, voice.fits(BarTime.QUARTER, quarter));
        assertEquals(false, voice.fits(BarTime.of(8, 7), quarter));
    }

    @Test
    void test6() {
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, quarter);
        voice.put(BarTime.QUARTER, gNote);
        assertEquals(BarTime.FOUR_QUARTER, voice.length());
        assertEquals(BarTime.THREE_QUARTER, voice.get(BarTime.QUARTER).getDuration());
        voice.put(BarTime.QUARTER, eight);
        assertEquals(BarTime.THREE_EIGHTH, voice.length());
        assertEquals(BarTime.EIGHTH, voice.get(BarTime.QUARTER).getDuration());
    }
}
