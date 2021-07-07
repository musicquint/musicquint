package test.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.musicquint.api.BarTime;

class BarTimeTest {

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

        assertEquals(t1, BarTime.max(t1, t2));
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
}
