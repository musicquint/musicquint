package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.musicquint.api.Step;

class StepTest {

    @Test
    void testC() {
        assertEquals(Step.C, Step.parse("c"));
        assertEquals(Step.C, Step.parse("C"));
    }

    @Test
    void testD() {
        assertEquals(Step.D, Step.parse("d"));
        assertEquals(Step.D, Step.parse("D"));
    }

    @Test
    void testE() {
        assertEquals(Step.E, Step.parse("e"));
        assertEquals(Step.E, Step.parse("E"));
    }

    @Test
    void testF() {
        assertEquals(Step.F, Step.parse("f"));
        assertEquals(Step.F, Step.parse("F"));
    }

    @Test
    void testG() {
        assertEquals(Step.G, Step.parse("g"));
        assertEquals(Step.G, Step.parse("G"));
    }

    @Test
    void testA() {
        assertEquals(Step.A, Step.parse("a"));
        assertEquals(Step.A, Step.parse("A"));
    }

    @Test
    void testB() {
        assertEquals(Step.B, Step.parse("b"));
        assertEquals(Step.B, Step.parse("B"));
    }

    @Test
    void testException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Step.parse("ff"));
        assertEquals("The input ff does not match the pattern (?<step>[a-g|A-G])", e.getMessage());
    }
}
