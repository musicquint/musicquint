package com.musicquint.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A BarTime represents a duration of an BarItem or are specific time event in a
 * Bar. Every BarTime is represented by a unique rational number and there is a
 * natural isomorphism between the field of rational numbers and BarTimes. We
 * define that a BarTime of 1 represents the duration of a quarter note or to be
 * an event in a Bar or Voice that occurs exactly after the duration of one
 * quarter note. Both, BarTimes and rational numbers are are often used
 * interchangeably.
 * </p>
 * A BarTime is immutable and stateless. Therefore to ensure uniqueness each
 * BarTime is given in a irreducible form with a strictly positive denominator.
 * Furthermore each BarTime has the singleton property. In particular for every
 * BarTime t1, t2 with {@code t1.equals(t2)} we have {@code t1 == t2}.
 * </p>
 * Tightly link to BarTimes is the concept of {@link Measurable} objects. Such
 * objects are associated with a unique BarTime which represent the duration of
 * such an element. The duration of such an Element is accessed through the
 * {@link Measurable#getDuration()} method and is only required to give back a
 * BarTime. Naturally any BarTime itself fulfills this requirement and for
 * convenience implements the {@link Measurable} interface. Additionally this
 * class is the only where the comparator given in Measurable is guaranteed to
 * be consistent with equals and therefore implements the {@link Comparable}
 * interface with the implementation given in Measurable.
 *
 * @see Measurable
 * @see Comparable
 */
public final class BarTime implements Measurable, Comparable<BarTime> {

    /**
     * Object pool for caching all BarTimes.
     */
    private static final Map<Integer, Map<Integer, BarTime>> OBJECT_POOL = new HashMap<>();

    /**
     * BarTime of 0/1
     */
    public static final BarTime ZERO = BarTime.of(0);

    /**
     * BarTime of 1/32
     */
    public static final BarTime ONE_HUNDERED_TWENTHY_EIGHTH = BarTime.of(1, 32);

    /**
     * BarTime of 1/16
     */
    public static final BarTime SIXTHY_FOURTH = BarTime.of(1, 16);

    /**
     * BarTime of 1/8
     */
    public static final BarTime THIRTY_SECOND = BarTime.of(1, 8);

    /**
     * BarTime of 1/4
     */
    public static final BarTime SIXTEENTH = BarTime.of(1, 4);

    /**
     * BarTime of 3/8
     */
    public static final BarTime SIXTEENTH_DOT = BarTime.of(3, 8);

    /**
     * BarTime of 7/16
     */
    public static final BarTime SIXTEENTH_DOUBLE_DOT = BarTime.of(3, 8);

    /**
     * BarTime of 1/2
     */
    public static final BarTime EIGHTH = BarTime.of(1, 2);

    /**
     * BarTime of 3/4
     */
    public static final BarTime EIGHTH_DOT = BarTime.of(3, 4);

    /**
     * BarTime of 7/8
     */
    public static final BarTime EIGHTH_DOUBLE_DOT = BarTime.of(7, 8);

    /**
     * BarTime of 3/2
     */
    public static final BarTime THREE_EIGHTH = BarTime.of(3, 2);

    /**
     * BarTime of 1/1
     */
    public static final BarTime QUARTER = BarTime.of(1);

    /**
     * BarTime of 3/2
     */
    public static final BarTime QUARTER_DOT = THREE_EIGHTH;

    /**
     * BarTime of 7/4
     */
    public static final BarTime QUARTER_DOUBLE_DOT = BarTime.of(7, 4);

    /**
     * BarTime of 2/1
     */
    public static final BarTime HALF = BarTime.of(2);

    /**
     * BarTime of 3/1
     */
    public static final BarTime THREE_QUARTER = BarTime.of(3);

    /**
     * BarTime of 3/1
     */
    public static final BarTime HALF_DOT = THREE_QUARTER;

    /**
     * BarTime of 7/2
     */
    public static final BarTime HALF_DOUBLE_DOT = BarTime.of(7, 2);

    /**
     * BarTime of 4/1
     */
    public static final BarTime WHOLE = BarTime.of(4);

    /**
     * BarTime of 4/1
     */
    public static final BarTime FOUR_QUARTER = WHOLE;

    /**
     * BarTime of 6/1
     */
    public static final BarTime WHOLE_DOT = BarTime.of(6);

    /**
     * BarTime of 8/1
     */
    public static final BarTime BREVE = BarTime.of(8, 1);

    // Class fields
    private final int numerator;

    private final int denominator;

    /**
     * Private Constructor should not be used outside of the static factory method.
     * As there is no validity check and no caching.
     */
    private BarTime(int enumerator, int denominator) {
        this.numerator = enumerator;
        this.denominator = denominator;
    }

    /**
     * Static factory method that returns a BarTime with a completely irreducible
     * fraction representation that is equal to the fraction of
     * {@code enumerator/denominator}. If such a {@code BarTime} has already been
     * created this same object is returned. Otherwise such a {@code BarTime} is
     * created and cached.
     *
     * @return a BarTime in a completely irreducible form.
     * @throws IllegalArgumentException if the denominator is zero.
     */
    public static BarTime of(int numerator, int denominator) {
        // The denominator cannot be zero.
        if (denominator == 0) {
            throw new IllegalArgumentException("A BarTime cannot have denominator zero.");
        }
        // Calculate the greatest common factor. The gcf cannot be zero as both numbers
        // cannot be zero.
        int gcf = greatestCommonFactor(numerator, denominator);

        int timeNum, timeDenom;
        if (denominator > 0) {
            timeNum = numerator / gcf;
            timeDenom = denominator / gcf;
        } else {
            // Change the sign of the denominator if the denominator is less than zero to
            // gain uniqueness.
            timeNum = -numerator / gcf;
            timeDenom = -denominator / gcf;
        }

        if (OBJECT_POOL.containsKey(timeDenom) && OBJECT_POOL.get(timeDenom).containsKey(timeNum)) {
            return OBJECT_POOL.get(timeDenom).get(timeNum);
        } else {
            BarTime t = new BarTime(timeNum, timeDenom);
            if (OBJECT_POOL.containsKey(timeDenom)) {
                OBJECT_POOL.get(timeDenom).put(timeNum, t);
            } else {
                HashMap<Integer, BarTime> barTimeMap = new HashMap<>();
                barTimeMap.put(timeNum, t);
                OBJECT_POOL.put(timeDenom, barTimeMap);
            }
            return t;
        }
    }

    /**
     * Static factory method that returns a BarTime with a completely shortened
     * fraction representation that is equal to the fraction of
     * {@code enumerator/1}. If such a {@code BarTime} has already been created the
     * same object is returned. Otherwise such a {@code BarTime} is created and
     * cached.
     *
     * @return a BarTime in a completely shortened form.
     */
    public static BarTime of(int i) {
        return BarTime.of(i, 1);
    }

    /**
     * A BarTime is represented by a fraction. This method returns the numerator of
     * this representation.
     *
     * @return the numerator of the BarTime.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * A BarTime is represented by a fraction. This method returns the denominator
     * of this representation.
     *
     * @return the denominator of the BarTime.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Returns a BarTime equal to the sum of both BarTimes.
     *
     * @param t1 the first summand.
     * @param t2 the second summand.
     * @return the sum of {@code t1} and {@code t2}.
     * @throws NullPointerException if {@code t1} or {@code t2} is null.
     */
    public static BarTime add(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot add the BarTimes. The first argument is null.");
        Objects.requireNonNull(t2, "Cannot add the BarTimes. The second argument is null.");
        int lcm = leastCommonMultiple(t1.getDenominator(), t2.getDenominator());

        int factor1 = lcm / t1.getDenominator();
        int factor2 = lcm / t2.getDenominator();
        int time1ExpandedEnum = t1.getNumerator() * factor1;
        int time2ExpandedEnum = t2.getNumerator() * factor2;

        int sumEnumerators = time1ExpandedEnum + time2ExpandedEnum;
        return BarTime.of(sumEnumerators, lcm);
    }

    /**
     * Returns a BarTime equal to the sum of this BarTime and the given argument.
     * The method makes use of the static {@link #add(BarTime, BarTime)} method and
     * therefore does not modify the given instances in any form.
     *
     * @param other the given BarTime.
     * @return the sum of {@code this} and {@code other}.
     * @throws NullPointerException if {@code other} is null.
     */
    public BarTime add(BarTime other) {
        Objects.requireNonNull(other, "The given BarTime is null.");
        return add(this, other);
    }

    /**
     * Returns a BarTime that is equal to the sum of both measurements of {@code m1}
     * and {@code m2}.
     *
     * @param m1 the first Measurable object.
     * @param m2 the second Measurable object.
     * @return the sum of both associated BarTimes.
     * @throws NullPointerException if {@code m1} or {@code m2} is null.
     */
    public static BarTime add(Measurable m1, Measurable m2) {
        Objects.requireNonNull(m1, "Cannot add the Measurables. The first argument is null.");
        Objects.requireNonNull(m2, "Cannot add the Measurables. The second argument is null.");
        return add(m1.getDuration(), m2.getDuration());
    }

    /**
     * Returns a BarTime equal to the difference of both BarTimes.
     *
     * @param t1 the first differentiator.
     * @param t2 the second differentiator.
     * @return the difference of {@codet1} and {@code t2}.
     * @throws NullPointerException if {@codet1} or {@code t2} is null.
     */
    public static BarTime subtract(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot subtract the BarTimes. The first argument is null.");
        Objects.requireNonNull(t2, "Cannot subtract the BarTimes. The second argument is null.");
        int lcm = leastCommonMultiple(t1.getDenominator(), t2.getDenominator());

        int factor1 = lcm / t1.getDenominator();
        int factor2 = lcm / t2.getDenominator();
        int time1ExpandedEnum = t1.getNumerator() * factor1;
        int time2ExpandedEnum = t2.getNumerator() * factor2;

        int enumerator = time1ExpandedEnum - time2ExpandedEnum;
        return BarTime.of(enumerator, lcm);
    }

    /**
     * Returns a BarTime equal to the difference of this BarTime and the given
     * argument. The method makes use of the static
     * {@link #subtract(BarTime, BarTime)} method and therefore does not modify the
     * given instances in any form.
     *
     * @param other the other given differentiator.
     * @return the difference of {@code this} and {@code other}.
     * @throws NullPointerException if {@code other} is null.
     */
    public BarTime subtract(BarTime other) {
        Objects.requireNonNull(other, "The given BarTime is null.");
        return subtract(this, other);
    }

    /**
     * Returns a BarTime equal to the difference of the measured BarTimes of
     * {@code m1} and {@code m2}. The method makes use of the static
     * {@code subtract} method and therefore does not modify the given instances in
     * any form.
     *
     * @param m1 the first Measurable object.
     * @param m2 the second Measurable object.
     * @return the difference of both associated BarTimes.
     * @throws NullPointerException if {@code m1} or {@code m2} is null.
     */
    public static BarTime subtract(Measurable m1, Measurable m2) {
        Objects.requireNonNull(m1, "Cannot subtract the Measurables. The first argument is null.");
        Objects.requireNonNull(m2, "Cannot subtract the Measurables. The second argument is null.");
        return subtract(m1.getDuration(), m2.getDuration());
    }

    /**
     * Returns a BarTime equal to the multiplication of both BarTimes.
     *
     * @param t1 the first multiplicand.
     * @param t2 the first multiplicand.
     * @return the product of {@code t1} and {@code t2}.
     * @throws NullPointerException if {@code t1} or {@code t2} is null.
     */
    public static BarTime multiply(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot multiply the BarTimes. The first argument is null.");
        Objects.requireNonNull(t2, "Cannot multiply the BarTimes. The second argument is null.");
        int enumerator = t1.getNumerator() * t2.getNumerator();
        int denominator = t1.getDenominator() * t2.getDenominator();
        return BarTime.of(enumerator, denominator);
    }

    /**
     * Returns a BarTime equal to the multiplication of both BarTimes.
     *
     * @param other the other given multiplicand.
     * @return the product of {@code this} and {@code other}.
     * @throws NullPointerException if {@code other} is null..
     */
    public BarTime multiply(BarTime other) {
        Objects.requireNonNull(other, "The given BarTime is null.");
        return multiply(this, other);
    }

    /**
     * Returns a BarTime equal to the multiplication of the measured BarTimes of
     * {@code m1} and {@code m2}. The method makes use of the static {@code divide}
     * method and therefore does not modify the given instances in any form.
     *
     * @param m1 the first Measurable object.
     * @param m2 the second Measurable object.
     * @return the product of {@code m1} and {@code m2}
     * @throws NullPointerException if {@code m1} or {@code m2} is null.
     */
    public static BarTime multiply(Measurable m1, Measurable m2) {
        Objects.requireNonNull(m1, "Cannot multiply the Measurables. The first argument is null.");
        Objects.requireNonNull(m2, "Cannot multiply the Measurables. The second argument is null.");
        return multiply(m1.getDuration(), m2.getDuration());
    }

    /**
     * Returns a BarTime equal to the fraction of both BarTimes.
     *
     * @param t1 the first factor.
     * @param t2 the second factor.
     * @return the fraction of {@code t1} and {@code t2}.
     * @throws NullPointerException if {@code t1} or {@code t2} is null.
     * @throws ArithmeticException  if {@code t2} equals {@code BarTime.ZERO}.
     */
    public static BarTime divide(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot divide the BarTimes. The first argument is null.");
        Objects.requireNonNull(t2, "Cannot divide the BarTimes. The second argument is null.");
        if (t2.equals(BarTime.ZERO)) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        int enumerator = t1.getNumerator() * t2.getDenominator();
        int denominator = t1.getDenominator() * t2.getNumerator();
        return BarTime.of(enumerator, denominator);
    }

    /**
     * Returns a BarTime equal to the fractions of {@code this} BarTime and the
     * given argument called {@code other}. The method makes use of the static
     * {@code divide} method and therefore does not modify the given instances in
     * any form.
     *
     * @param other the other given factor.
     * @return the fraction of {@code this} and {@code other}.
     * @throws NullPointerException if {@code other} is null.
     * @throws ArithmeticException  if {@code other} equals {@code BarTime.ZERO}.
     */
    public BarTime divide(BarTime other) {
        Objects.requireNonNull(other, "The given BarTime is null.");
        return divide(this, other);
    }

    /**
     * Returns a BarTime equal to the fractions of the measured BarTimes of
     * {@code m1} and {@code m2}. The method makes use of the static {@code divide}
     * method and therefore does not modify the given instances in any form.
     *
     * @param m1 the first Measurable object.
     * @param m2 the second Measurable object.
     * @return the fraction of {@code m1} and {@code m2}
     * @throws NullPointerException if {@code m1} or {@code m2} is null.
     * @throws ArithmeticException  if the measurement of {@code m2}
     *                              {@code BarTime.ZERO}.
     */
    public static BarTime divide(Measurable m1, Measurable m2) {
        Objects.requireNonNull(m1, "Cannot add the Measurables. The first argument is null.");
        Objects.requireNonNull(m2, "Cannot add the Measurables. The second argument is null.");
        return divide(m1.getDuration(), m2.getDuration());
    }

    /**
     * Returns the minimal BarTime.
     *
     * @return the minimum of both BarTimes.
     */
    public static BarTime min(BarTime t1, BarTime t2) {
        return Stream.of(t1, t2).min(Measurable.timeComparator()).get();
    }

    /**
     * Returns the maximum BarTime.
     *
     * @return the maximum of both BarTimes.
     */
    public static BarTime max(BarTime t1, BarTime t2) {
        return Stream.of(t1, t2).max(Measurable.timeComparator()).get();
    }

    @Override
    public BarTime getDuration() {
        return this;
    }

    @Override
    public int compareTo(BarTime o) {
        return Measurable.timeComparator().compare(this, o);
    }

    /**
     * Returns an integer array of size two containing the numerator and
     * denominator.
     *
     * @return {@code numerator} and {@code denominator} as integer array
     */
    public int[] toInt() {
        return new int[] { numerator, denominator };
    }

    /**
     * Calculates the greatest common factor of i and j. The method relies on the
     * Euclidean algorithm. If one of the integers is zero the biggest absolute
     * value of both integers is returned.
     *
     * @param i the first integer
     * @param j the second integer
     * @return the absolute value of the greatest common factor
     */
    public static int greatestCommonFactor(int i, int j) {
        if (i == 0 || j == 0) {
            return Math.max(Math.abs(i), Math.abs(j));
        } else {
            int r0 = Math.max(Math.abs(i), Math.abs(j));
            int r1 = Math.min(Math.abs(i), Math.abs(j));
            int newR0;
            while ((r0 % r1) != 0) {
                newR0 = r1;
                r1 = (r0 % r1);
                r0 = newR0;
            }
            return Math.abs(r1);
        }
    }

    /**
     * Calculates the absolute value of the least common multiple of {@code i} and
     * {@code j}.
     *
     * @param i the first integer.
     * @param j the second integer.
     * @return the absolute value of the least common multiple of {@code i} and
     *         {@code j}.
     */
    public static int leastCommonMultiple(int i, int j) {
        if (i == 0 || j == 0) {
            return 0;
        } else {
            return Math.abs((i * j) / greatestCommonFactor(i, j));
        }
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}