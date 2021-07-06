package test.api;

import static com.musicquint.api.Measurable.canonicalComparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.musicquint.api.BarTime;
import com.musicquint.api.Measurable;
import com.musicquint.api.NoteAttribute;
import com.musicquint.api.Pitch;
import com.musicquint.api.PrincipalItem;
import com.musicquint.api.Type;

class MeasurableTest {

    Measurable m1 = new PrincipalItem() {

        @Override
        public void setPitch(Pitch pitch) {
        }

        @Override
        public Type getType() {
            return null;
        }

        @Override
        public Optional<Pitch> getPitch() {
            return null;
        }

        @Override
        public <T extends NoteAttribute> T getNoteAttribute(Class<T> key) {
            return null;
        }

        @Override
        public BarTime getDuration() {
            return BarTime.HALF;
        }

        @Override
        public int getDots() {
            return 0;
        }

        @Override
        public void addNoteAttribute(NoteAttribute attribute) {
        }
    };

    Measurable m2 = () -> BarTime.EIGHTH_DOT;

    Measurable m3 = () -> BarTime.HALF;

    @Test
    void testCanonicalComparatorComparesEqual() {
        Measurable zero1 = BarTime.ZERO;
        Measurable zero2 = () -> BarTime.ZERO;
        assertEquals(0, canonicalComparator().compare(zero1, zero2));
        assertFalse(zero1.equals(zero2));
    }

    @Test
    void testCanonicalComparatorComparesAsLess() {
        assertEquals(-1, canonicalComparator().compare(BarTime.ZERO, BarTime.EIGHTH));
    }

    @Test
    void testCanonicalComparatorComparesAsGreater() {
        assertEquals(1, canonicalComparator().compare(BarTime.ZERO, BarTime.of(-1)));
    }

    @Test
    void testCanonicalComparatorSymmetry() {
        assertEquals(-1, canonicalComparator().compare(BarTime.ZERO, BarTime.EIGHTH));
        assertEquals(1, canonicalComparator().compare(BarTime.EIGHTH, BarTime.ZERO));
    }

    @Test
    void testCanonicalComparatorTransivity() {
        assertEquals(1, canonicalComparator().compare(BarTime.EIGHTH, BarTime.ZERO));
        assertEquals(1, canonicalComparator().compare(BarTime.QUARTER, BarTime.EIGHTH));
        assertEquals(1, canonicalComparator().compare(BarTime.QUARTER, BarTime.ZERO));
    }

    @Test
    void testCanonicalComparatorDifferentMeasurables() {
        assertEquals(1, canonicalComparator().compare(m1, m2));
    }

    @Test
    void testCanonicalComparatorNullPointerExceptions() {
        NullPointerException e1 = assertThrows(NullPointerException.class,
                () -> canonicalComparator().compare(null, BarTime.ZERO));
        NullPointerException e2 = assertThrows(NullPointerException.class,
                () -> canonicalComparator().compare(BarTime.ZERO, null));

        assertEquals("Cannot compare. The first argument is null.", e1.getMessage());
        assertEquals("Cannot compare. The second argument is null.", e2.getMessage());
    }

    @Test
    void testIsGreaterTrue() {
        assertTrue(m1.isGreater(m2));
    }

    @Test
    void testIsGreaterBothEqual() {
        assertFalse(m1.isGreater(m3));
    }

    @Test
    void testIsGreaterFalse() {
        assertFalse(m2.isGreater(m1));
    }

    @Test
    void testIsGreaterNullPointerException() {
        NullPointerException e = assertThrows(NullPointerException.class,() ->  m2.isGreater(null));
        assertEquals("The other Measurable is null.", e.getMessage());
    }

    @Test
    void testIsGreaterOrEqualTrue() {
        assertTrue(m1.isGreaterOrEqual(m2));
    }

    @Test
    void testIsGreaterOrEqualBothEqual() {
        assertTrue(m1.isGreaterOrEqual(m3));
    }

    @Test
    void testIsGreaterOrEqualFalse() {
        assertFalse(m2.isGreaterOrEqual(m1));
    }

    @Test
    void testIsGreaterOrEqualNullPointerException() {
        NullPointerException e = assertThrows(NullPointerException.class,() ->  m2.isGreaterOrEqual(null));
        assertEquals("The other Measurable is null.", e.getMessage());
    }

    @Test
    void testIsLessTrue() {
        assertTrue(m2.isLess(m1));
    }

    @Test
    void testIsLessBothEqual() {
        assertFalse(m1.isLess(m3));
    }

    @Test
    void testIsLessFalse() {
        assertFalse(m1.isLess(m2));
    }

    @Test
    void testIsLessNullPointerException() {
        NullPointerException e = assertThrows(NullPointerException.class,() ->  m2.isLess(null));
        assertEquals("The other Measurable is null.", e.getMessage());
    }

    @Test
    void testIsLessOrEqualTrue() {
        assertTrue(m2.isLessOrEqual(m1));
    }

    @Test
    void testIsLessOrEqualBothEqual() {
        assertTrue(m1.isLessOrEqual(m3));
    }

    @Test
    void testIsLessOrEqualFalse() {
        assertFalse(m1.isLessOrEqual(m2));
    }

    @Test
    void testIsLessOrEqualNullPointerException() {
        NullPointerException e = assertThrows(NullPointerException.class,() ->  m2.isLessOrEqual(null));
        assertEquals("The other Measurable is null.", e.getMessage());
    }

    @Test
    void testMaxFirstBigger() {
        assertEquals(m1, Measurable.max(m1, m2));
    }

    @Test
    void testMaxSecondBigger() {
        assertEquals(m1, Measurable.max(m2, m1));
    }

    @Test
    void testMaxBothEqual() {
        assertEquals(m3, Measurable.max(m3, m1));
    }

    @Test
    void testMaxNullPointerException() {
        NullPointerException e1 = assertThrows(NullPointerException.class,() ->  Measurable.max(null, m1));
        NullPointerException e2 = assertThrows(NullPointerException.class,() ->  Measurable.max(m1, null));
        assertEquals("Cannot compare. The first argument is null", e1.getMessage());
        assertEquals("Cannot compare. The second argument is null", e2.getMessage());
    }

    @Test
    void testMinFirstBigger() {
        assertEquals(m2, Measurable.min(m1, m2));
    }

    @Test
    void testMinSecondBigger() {
        assertEquals(m2, Measurable.min(m2, m1));
    }

    @Test
    void testMinBothEqual() {
        assertEquals(m3, Measurable.min(m3, m1));
    }

    @Test
    void testMinNullPointerException() {
        NullPointerException e1 = assertThrows(NullPointerException.class,() ->  Measurable.min(null, m1));
        NullPointerException e2 = assertThrows(NullPointerException.class,() ->  Measurable.min(m1, null));
        assertEquals("Cannot compare. The first argument is null", e1.getMessage());
        assertEquals("Cannot compare. The second argument is null", e2.getMessage());
    }
}
