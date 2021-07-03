package com.musicquint.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
 * Additionally the class also implements the Comparable interface. Naturally
 * the isomorphism between BarTimes and rational numbers induces a canonical
 * order on all BarTimes which is represented by the fact, that the class
 * implements the {@link Comparable} interface. This order is consistent with
 * equals.
 * </p>
 * Tightly link to BarTimes is the concept of {@link Measurable} objects. Such
 * objects are associated with a unique BarTime which represent the duration of
 * such an element. The duration of such an Element is accessed through the
 * {@code getDuration()} method and is only required to give back a BarTime.
 * Naturally any BarTime itself fulfills this requirement and for convenience
 * implements the {@link Measurable} interface. Additionally this class is the
 * only where the comparator given in Measurable is guaranteed to be consistent
 * with equals and therefore implements the {@link Comparable} interface with
 * the implementation given in Measurable.
 *
 * @see Measurable
 * @see Comparable
 */
public class BarTime implements Measurable, Comparable<BarTime> {

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
     * Static factory method that returns a BarTime with a completely shortened
     * fraction representation that is equal to the fraction of
     * {@code enumerator/denominator}. If such a {@code BarTime} has already been
     * created the same object is returned. Otherwise such a {@code BarTime} is
     * created and cached.
     *
     * @return a BarTime in a completely shortened form.
     * @throws IllegalArgumentException if the denominator is zero.
     */
    public static BarTime of(int numerator, int denominator) {
        // The denominator cannot be zero
        if (denominator == 0) {
            throw new IllegalArgumentException("A BarTime cannot have denominator zero");
        }
        // Calculate the greatest common factor. The gcf cannot be zero as both numbers
        // cannot be zero
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
     * of this representation
     *
     * @return the denominator of the BarTime.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Returns a BarTime equal to the sum of both BarTimes
     *
     * @param t1
     * @param t2
     * @return the sum of time1 and time2.
     */
    public static BarTime add(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot add the BarTimes. The first argument is null");
        Objects.requireNonNull(t2, "Cannot add the BarTimes. The second argument is null");
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
     * The method makes use of the static {@code add} method and therefore does not
     * modify the given instances in any form.
     *
     * @param other the given BarTime
     * @return the sum of {@code this} and {@code other}
     */
    public BarTime add(BarTime other) {
        return add(this, other);
    }

    /**
     * Returns a BarTime that is equal to the sum of both measurements of t1 and t2.
     *
     * @param t1 the first TimeMeasurable object
     * @param t2 the second TimeMeasurable object
     * @return the sum of both associated BarTimes.
     */
    public static BarTime add(Measurable t1, Measurable t2) {
        return add(t1.getDuration(), t2.getDuration());
    }

    /**
     * Returns a BarTime equal to the difference of both BarTimes
     *
     * @param t1
     * @param t2
     * @return the difference of time1 and time2.
     */
    public static BarTime subtract(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot subtract the BarTimes. The first argument is null");
        Objects.requireNonNull(t2, "Cannot subtract the BarTimes. The second argument is null");
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
     * argument. The method makes use of the static {@code subtract} method and
     * therefore does not modify the given instances in any form.
     *
     * @param other
     * @return the difference of {@code this} and {@code other}
     */
    public BarTime subtract(BarTime other) {
        return subtract(this, other);
    }

    /**
     * Returns a BarTime equal to the difference of the measuraed BarTimes of t1 and
     * t2. The method makes use of the static {@code subtract} method and therefore
     * does not modify the given instances in any form.
     *
     * @param other
     * @return the difference of {@code t1} and {@code t2}
     */
    public static BarTime subtract(Measurable t1, Measurable t2) {
        return subtract(t1.getDuration(), t2.getDuration());
    }

    /**
     * Returns a BarTime equal to the fraction of both BarTimes
     *
     * @param t1
     * @param t2
     * @return the fraction of
     * @return the difference of
     */
    public static BarTime divide(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot divide the BarTimes. The first argument is null");
        Objects.requireNonNull(t2, "Cannot divide the BarTimes. The second argument is null");
        int enumerator = t1.getNumerator() * t2.getDenominator();
        int denominator = t1.getDenominator() * t2.getNumerator();
        return BarTime.of(enumerator, denominator);
    }

    /**
     * Returns a BarTime equal to the fractions of this BarTime and the given
     * argument. The method makes use of the static {@code divide} method and
     * therefore does not modify the given instances in any form.
     *
     * @param other
     * @return the fraction of {@code this} and {@code other}
     */
    public BarTime divide(BarTime other) {
        return divide(this, other);
    }

    /**
     * Returns a BarTime equal to the fractions of the measured BarTimes of t1 and
     * t2. The method makes use of the static {@code divide} method and therefore
     * does not modify the given instances in any form.
     *
     * @param t1
     * @param t2
     * @return the fraction of {@code t1} and {@code t2}
     */
    public BarTime divide(Measurable t1, Measurable t2) {
        return divide(t1.getDuration(), t2.getDuration());
    }

    /**
     * Returns a BarTime equal to the multiplication of both BarTimes
     *
     * @param t1
     * @param t2
     * @return the product of time1 and time2
     */
    public static BarTime multiply(BarTime t1, BarTime t2) {
        Objects.requireNonNull(t1, "Cannot multiply the BarTimes. The first argument is null");
        Objects.requireNonNull(t2, "Cannot multiply the BarTimes. The second argument is null");
        int enumerator = t1.getNumerator() * t2.getNumerator();
        int denominator = t1.getDenominator() * t2.getDenominator();
        return BarTime.of(enumerator, denominator);
    }

    /**
     * Returns a BarTime equal to the multiplication of both BarTimes
     *
     * @param other
     * @return the product of {@code this} and {@code other}
     */
    public BarTime multiply(BarTime other) {
        return multiply(this, other);
    }

    /**
     * Returns a BarTime equal to the multiplication of the measured BarTimes of t1
     * and t2. The method makes use of the static {@code divide} method and
     * therefore does not modify the given instances in any form.
     *
     * @param t1
     * @param t2
     * @return the fraction of {@code t1} and {@code t2}
     */
    public static BarTime multiply(Measurable t1, Measurable t2) {
        return multiply(t1.getDuration(), t2.getDuration());
    }

    /**
     * Returns the minimal BarTime.
     *
     * @return the minimum of both BarTimes.
     */
    public static BarTime min(BarTime t1, BarTime t2) {
        return Measurable.min(t1, t2);
    }

    /**
     * Returns the maximum BarTime.
     *
     * @return the maximum of both BarTimes.
     */
    public static BarTime max(BarTime t1, BarTime t2) {
        return Measurable.max(t1, t2);
    }

    @Override
    public BarTime getDuration() {
        return this;
    }

    @Override
    public int compareTo(BarTime o) {
        return Measurable.canonicalComparator().compare(this, o);
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
     * Euclidean algorithm.
     *
     * @return the greatest common factor
     */
    private static int greatestCommonFactor(int i, int j) {
        if (i == 0 || j == 0) {
            return Math.max(Math.abs(i), Math.abs(j));
        } else {
            int r0 = Math.max(Math.abs(i), Math.abs(j));
            int r1 = Math.min(Math.abs(i), Math.abs(j));
            while ((r0 % r1) != 0) {
                int newR0 = r1;
                r1 = (r0 % r1);
                r0 = newR0;
            }
            return Math.abs(r1);
        }
    }

    /**
     * Calculates the least common multiple of i and j
     *
     * @param i
     * @param j
     * @return the least common multiple of i and j.
     */
    private static int leastCommonMultiple(int i, int j) {
        return (i * j) / greatestCommonFactor(i, j);
    }

    @Override
    public int hashCode() {
        if (numerator == 0) {
            return 0;
        } else {
            final int prime = 31;
            int result = 1;
            result = prime * result + denominator;
            result = prime * result + numerator;
            return result;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BarTime other = (BarTime) obj;
        if (numerator * other.denominator != other.numerator * denominator) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}