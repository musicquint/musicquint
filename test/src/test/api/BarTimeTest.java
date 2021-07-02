package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.musicquint.api.BarTime;
import com.musicquint.api.Measurable;

class BarTimeTest {

    @Test
    void singeltonProperty() {
        BarTime t1 = BarTime.of(17, 23);
        BarTime t2 = BarTime.of(17, 23);
        assertTrue(t1 == t2);
    }

    @Test
    void singeltonProperty2() {
        BarTime t1 = BarTime.of(17, 23);
        BarTime t2 = BarTime.of(34, 46);
        assertTrue(t1 == t2);
    }

    @Test
    void staticFactoryException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> BarTime.of(1, 0));
        assertEquals("A BarTime cannot have denominator zero", e.getMessage());
    }

    @Test
    void staticFactory1() {
        BarTime t1 = BarTime.of(0, 1);
        BarTime t2 = BarTime.of(0, 10);

        assertEquals(t1, t2);
        assertEquals(t1.getNumerator(), 0);
        assertEquals(t1.getDenominator(), 1);
        assertEquals(t2.getNumerator(), 0);
        assertEquals(t2.getDenominator(), 1);
    }

    @Test
    void staticFactory2() {
        BarTime t1 = BarTime.of(1, -4);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(t1, t2);
        assertTrue(t1 == t2);
        assertEquals(t1.getNumerator(), -1);
        assertEquals(t1.getDenominator(), 4);
        assertEquals(t2.getNumerator(), -1);
        assertEquals(t2.getDenominator(), 4);
    }

    @Test
    void staticFactory3() {
        BarTime t1 = BarTime.of(60, 25);

        assertEquals(t1.getNumerator(), 12);
        assertEquals(t1.getDenominator(), 5);
    }

    @Test
    void testAdd() {
        BarTime t1 = BarTime.of(60, 25);
        BarTime t2 = BarTime.of(3, 5);

        assertEquals(t1.add(t2), BarTime.of(3, 1));
    }

    @Test
    void testAdd2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(t1.add(t2), BarTime.of(1, 12));
    }

    @Test
    void testSubtract() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(t1.subtract(t2), BarTime.of(4, 15));
    }

    @Test
    void testSubtract2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(t1.subtract(t2), BarTime.of(7, 12));
    }

    @Test
    void testMultiply() {
        BarTime t1 = BarTime.of(2, 3);
        BarTime t2 = BarTime.of(10, 7);

        assertEquals(t1.multiply(t2), BarTime.of(20, 21));
    }

    @Test
    void testMultiply2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(t1.multiply(t2), BarTime.of(-1, 12));
    }

    @Test
    void testDivide() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(t1.divide(t2), BarTime.of(7, 3));
    }

    @Test
    void testDivide2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(t1.divide(t2), BarTime.of(-4, 3));
    }

    @Test
    void testMax() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(t1, Measurable.max(t1, t2));
    }

    @Test
    void testMin() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(t2, Measurable.min(t1, t2));
    }

    @Test
    void testMinMaxForEqualBarTimes() {
        BarTime t1 = BarTime.of(2, 10);
        BarTime t2 = BarTime.of(2, 10);

        assertEquals(t2, Measurable.min(t1, t2));
        assertEquals(t1, Measurable.min(t1, t2));
        assertEquals(t2, Measurable.max(t1, t2));
        assertEquals(t1, Measurable.max(t1, t2));
    }

    @Test
    void testLess() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertTrue(t2.isLess(t1));
        assertFalse(t2.isLess(t2));
        assertFalse(t1.isLess(t2));
    }

    @Test
    void testLessOrEqual() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertTrue(t2.isLess(t1));
        assertTrue(t2.isLessOrEqual(t2));
    }

    @Test
    void testGreater() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertTrue(t1.isGreater(t2));
        assertFalse(t1.isGreater(t1));
        assertFalse(t2.isGreater(t1));
    }

    @Test
    void testGreaterOrEqual() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertTrue(t1.isGreaterOrEqual(t2));
        assertTrue(t1.isGreaterOrEqual(t1));
        assertFalse(t2.isGreaterOrEqual(t1));
    }
}
