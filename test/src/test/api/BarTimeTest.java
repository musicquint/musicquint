package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.musicquint.api.BarTime;

class BarTimeTest {

    @Test
    void greatestCommonFactor() {
        assertEquals(6, BarTime.greatestCommonFactor(24, 594));
    }

    @Test
    void greatestCommonFactor2() {
        assertEquals(1, BarTime.greatestCommonFactor(31, 101));
    }

    @Test
    void greatestCommonFactor3() {
        assertEquals(31, BarTime.greatestCommonFactor(31, 0));
    }

    @Test
    void leastCommonMultiple() {
        assertEquals(0, BarTime.leastCommonMultiple(31, 0));
    }

    @Test
    void leastCommonMultiple2() {
        assertEquals(30, BarTime.leastCommonMultiple(6, 15));
    }

    @Test
    void leastCommonMultiple3() {
        assertEquals(252, BarTime.leastCommonMultiple(21, 36));
    }

    @Test
    void testStaticFactoryShortenedForm() {
        BarTime t = BarTime.of(18, 45);
        assertEquals(2, t.getNumerator());
        assertEquals(5, t.getDenominator());
    }

    @Test
    void testStaticFactorySingelton() {
        BarTime t1 = BarTime.of(1, 2);
        BarTime t2 = BarTime.of(6, 12);
        assertTrue(t1 == t2);
    }

    @Test
    void singeltonStaticFactoryProperty() {
        BarTime t1 = BarTime.of(17, 23);
        BarTime t2 = BarTime.of(17, 23);
        assertTrue(t1 == t2);
    }

    @Test
    void singeltonStaticFactoryProperty2() {
        BarTime t1 = BarTime.of(17, 23);
        BarTime t2 = BarTime.of(34, 46);
        assertTrue(t1 == t2);
    }

    @Test
    void testStaticFactoryException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> BarTime.of(1, 0));
        assertEquals("A BarTime cannot have denominator zero.", e.getMessage());
    }

    @Test
    void staticFactory1() {
        BarTime t1 = BarTime.of(0, 1);
        BarTime t2 = BarTime.of(0, 10);

        assertEquals(t1, t2);
        assertEquals(0, t1.getNumerator());
        assertEquals(1, t1.getDenominator());
        assertEquals(0, t2.getNumerator());
        assertEquals(1, t2.getDenominator());
    }

    @Test
    void staticFactory2() {
        BarTime t1 = BarTime.of(1, -4);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(t1, t2);
        assertTrue(t1 == t2);
        assertEquals(-1, t1.getNumerator());
        assertEquals(4, t1.getDenominator());
        assertEquals(-1, t2.getNumerator());
        assertEquals(4, t2.getDenominator());
    }

    @Test
    void staticFactory3() {
        BarTime t1 = BarTime.of(60, 25);

        assertEquals(12, t1.getNumerator());
        assertEquals(5, t1.getDenominator());
    }

    @Test
    void testAdd() {
        BarTime t1 = BarTime.of(60, 25);
        BarTime t2 = BarTime.of(3, 5);

        assertEquals(BarTime.of(3, 1), t1.add(t2));
    }

    @Test
    void testAdd2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(1, 12), t1.add(t2));
    }

    @Test
    void testAddMeasurables() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(1, 12), BarTime.add(() -> t1, () -> t2));
    }

    @Test
    void testAddZero() {
        assertEquals(BarTime.QUARTER, BarTime.add(BarTime.QUARTER, BarTime.ZERO));
    }

    @Test
    void testAddNullpointerException() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.add(null, BarTime.ZERO));

        assertEquals("Cannot add the BarTimes. The first argument is null.", e.getMessage());
    }

    @Test
    void testAddNullpointerException2() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.add(BarTime.ZERO, null));

        assertEquals("Cannot add the BarTimes. The second argument is null.", e.getMessage());
    }

    @Test
    void testSubtract() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(BarTime.of(4, 15), t1.subtract(t2));
    }

    @Test
    void testSubtract2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(7, 12), t1.subtract(t2));
    }

    @Test
    void testSubtractMeasurables() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(7, 12), BarTime.subtract(() -> t1, () -> t2));
    }

    @Test
    void testSubtractNullpointerException() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.subtract(null, BarTime.ZERO));

        assertEquals("Cannot subtract the BarTimes. The first argument is null.", e.getMessage());
    }

    @Test
    void testSubtractNullpointerException2() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.subtract(BarTime.ZERO, null));

        assertEquals("Cannot subtract the BarTimes. The second argument is null.", e.getMessage());
    }

    @Test
    void testMultiply() {
        BarTime t1 = BarTime.of(2, 3);
        BarTime t2 = BarTime.of(10, 7);

        assertEquals(BarTime.of(20, 21), t1.multiply(t2));
    }

    @Test
    void testMultiply2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(-1, 12), t1.multiply(t2));
    }

    @Test
    void testMultiplyMeasurables() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(-1, 12), BarTime.multiply(() -> t1, t2));
    }

    @Test
    void testMultiplyNullpointerException() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.multiply(null, BarTime.ZERO));

        assertEquals("Cannot multiply the BarTimes. The first argument is null.", e.getMessage());
    }

    @Test
    void testMultiplyNullpointerException2() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.multiply(BarTime.ZERO, null));

        assertEquals("Cannot multiply the BarTimes. The second argument is null.", e.getMessage());
    }

    @Test
    void testDivide() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(BarTime.of(7, 3), t1.divide(t2));
    }

    @Test
    void testDivide2() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(-4, 3), t1.divide(t2));
    }

    @Test
    void testDivideMeasurable() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(-1, 4);

        assertEquals(BarTime.of(-4, 3), BarTime.divide(() -> t1, t2));
    }

    @Test
    void testDivideNullpointerException() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.divide(null, BarTime.QUARTER));

        assertEquals("Cannot divide the BarTimes. The first argument is null.", e.getMessage());
    }

    @Test
    void testDivideNullpointerException2() {
        NullPointerException e = assertThrows(NullPointerException.class, () -> BarTime.divide(BarTime.ZERO, null));

        assertEquals("Cannot divide the BarTimes. The second argument is null.", e.getMessage());
    }

    @Test
    void testDivideArithmeticException() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> BarTime.divide(BarTime.FOUR_QUARTER, BarTime.ZERO));

        assertEquals("Cannot divide by zero.", e.getMessage());
    }

    @Test
    void testMax() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(t1, BarTime.max(t1, t2));
        assertEquals(t1, BarTime.max(t2, t1));
    }

    @Test
    void testMin() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);

        assertEquals(t2, BarTime.min(t1, t2));
    }

    @Test
    void testMinMaxForEqualBarTimes() {
        BarTime t1 = BarTime.of(2, 10);
        BarTime t2 = BarTime.of(2, 10);

        assertEquals(t2, BarTime.min(t1, t2));
        assertEquals(t1, BarTime.min(t1, t2));
        assertEquals(t2, BarTime.max(t1, t2));
        assertEquals(t1, BarTime.max(t1, t2));
    }

    @Test
    void testCompare() {
        BarTime t1 = BarTime.of(7, 15);
        BarTime t2 = BarTime.of(1, 5);
        BarTime t3 = BarTime.of(2, 10);

        assertEquals(true, t2.equals(t3));
        assertEquals(0, t2.compareTo(t3));
        assertEquals(1, t1.compareTo(t3));
        assertEquals(-1, t2.compareTo(t1));
    }
}
