package test.api;

import static com.musicquint.api.Measurable.timeComparator;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.musicquint.api.BarTime;
import com.musicquint.api.Measurable;
import com.musicquint.api.PrincipalItem;

class MeasurableTest {

    Measurable m1 = new PrincipalItem() {

        @Override
        public BarTime getDuration() {
            return BarTime.HALF;
        }
    };

    Measurable m2 = () -> BarTime.EIGHTH_DOT;

    Measurable m3 = () -> BarTime.HALF;

    @Test
    void testCanonicalComparatorComparesEqual() {
        Measurable zero1 = BarTime.ZERO;
        Measurable zero2 = () -> BarTime.ZERO;
        assertEquals(0, timeComparator().compare(zero1, zero2));
        assertFalse(zero1.equals(zero2));
    }

    @Test
    void testCanonicalComparatorComparesAsLess() {
        assertEquals(-1, timeComparator().compare(BarTime.ZERO, BarTime.EIGHTH));
    }

    @Test
    void testCanonicalComparatorComparesAsGreater() {
        assertEquals(1, timeComparator().compare(BarTime.ZERO, BarTime.of(-1)));
    }

    @Test
    void testCanonicalComparatorSymmetry() {
        assertEquals(-1, timeComparator().compare(BarTime.ZERO, BarTime.EIGHTH));
        assertEquals(1, timeComparator().compare(BarTime.EIGHTH, BarTime.ZERO));
    }

    @Test
    void testCanonicalComparatorTransivity() {
        assertEquals(1, timeComparator().compare(BarTime.EIGHTH, BarTime.ZERO));
        assertEquals(1, timeComparator().compare(BarTime.QUARTER, BarTime.EIGHTH));
        assertEquals(1, timeComparator().compare(BarTime.QUARTER, BarTime.ZERO));
    }

    @Test
    void testCanonicalComparatorDifferentMeasurables() {
        assertEquals(1, timeComparator().compare(m1, m2));
    }

    @Test
    void testCanonicalComparatorNullPointerExceptions() {
        NullPointerException e1 = assertThrows(NullPointerException.class,
                () -> timeComparator().compare(null, BarTime.ZERO));
        NullPointerException e2 = assertThrows(NullPointerException.class,
                () -> timeComparator().compare(BarTime.ZERO, null));

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
}
