package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.musicquint.api.BarTime;
import com.musicquint.api.Note;
import com.musicquint.api.Octave;
import com.musicquint.api.Pitch;
import com.musicquint.api.Step;
import com.musicquint.api.Type;
import com.musicquint.api.Voice;
import com.musicquint.api.Voice.PrincipalSet;

class VoiceTest {

    Note quarter = new Note(new Pitch(Step.C, Octave.ONE_LINED), BarTime.QUARTER, 0, Type.QUARTER);
    Note eight = new Note(new Pitch(Step.C, Octave.ONE_LINED), BarTime.EIGHTH, 0, Type.EIGHTH);
    Note half = new Note(new Pitch(Step.C, Octave.ONE_LINED), BarTime.HALF, 0, Type.HALF);

    @Test
    void test1() {
        PrincipalSet itemSet = PrincipalSet.of(quarter, half, eight);
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, itemSet);
        assertEquals(BarTime.HALF, voice.length());
    }

    @Test
    void test2() {
        PrincipalSet itemSet = PrincipalSet.of(quarter, half, eight);
        Voice voice = Voice.create();
        voice.put(BarTime.ZERO, itemSet);
        assertEquals(BarTime.HALF, voice.length());
    }

}
