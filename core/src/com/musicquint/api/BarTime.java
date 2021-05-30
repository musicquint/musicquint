package com.musicquint.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//TODO documentation
public class BarTime implements Comparable<BarTime> {

    /**
     * Object pool for caching all BarTimes.
     */
    private static final Map<Integer, Map<Integer, BarTime>> OBJECT_POOL = new HashMap<>();

    /**
     * BarTime of 0/1
     */
    public static final BarTime ZERO = BarTime.of(0, 1);

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
    public static final BarTime QUARTER = BarTime.of(1, 1);

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
    public static final BarTime HALF = BarTime.of(2, 1);

    /**
     * BarTime of 3/1
     */
    public static final BarTime THREE_QUARTER = BarTime.of(3, 1);

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
    public static final BarTime WHOLE = BarTime.of(4, 1);

    /**
     * BarTime of 4/1
     */
    public static final BarTime FOUR_QUARTER = BarTime.of(4, 1);

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

        int timeEnum, timeDenom;
        if (denominator > 0) {
            timeEnum = numerator / gcf;
            timeDenom = denominator / gcf;
        } else {
            // Change the sign of the denominator if the denominator is less than zero to
            // gain uniqueness.
            timeEnum = -numerator / gcf;
            timeDenom = -denominator / gcf;
        }

        if (OBJECT_POOL.containsKey(timeDenom) && OBJECT_POOL.get(timeDenom).containsKey(timeEnum)) {
            return OBJECT_POOL.get(timeDenom).get(timeEnum);
        } else {
            BarTime t = new BarTime(timeEnum, timeDenom);
            if (OBJECT_POOL.containsKey(timeDenom)) {
                OBJECT_POOL.get(timeDenom).put(timeEnum, t);
            } else {
                HashMap<Integer, BarTime> barTimeMap = new HashMap<>();
                barTimeMap.put(timeEnum, t);
                OBJECT_POOL.put(timeDenom, barTimeMap);
            }
            return t;
        }
    }

    /**
     * Static factory method that returns a BarTime with a completely shortened
     * fraction representation that is equal to the fraction of
     * {@code enumerator/1}. If such a {@code BarTime} has already been
     * created the same object is returned. Otherwise such a {@code BarTime} is
     * created and cached.
     *
     * @return a BarTime in a completely shortened form.
     */
    public static BarTime of(int i) {
        return of(i,1);
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
     * @param time1
     * @param time2
     * @return the sum of time1 and time2.
     */
    public static BarTime add(BarTime time1, BarTime time2) {
        Objects.requireNonNull(time1, "Cannot add the BarTimes. The first argument is null");
        Objects.requireNonNull(time2, "Cannot add the BarTimes. The second argument is null");
        int lcm = leastCommonMultiple(time1.getDenominator(), time2.getDenominator());

        int factor1 = lcm / time1.getDenominator();
        int factor2 = lcm / time2.getDenominator();
        int time1ExpandedEnum = time1.getNumerator() * factor1;
        int time2ExpandedEnum = time2.getNumerator() * factor2;

        int sumEnumerators = time1ExpandedEnum + time2ExpandedEnum;
        return BarTime.of(sumEnumerators, lcm);
    }

    /**
     * Returns a BarTime equal to the sum of this BarTime and the given argument.
     * The method makes use of the static {@code add} method and therefore
     *
     * @param other
     * @return the sum of {@code this} and {@code other}
     */
    public BarTime add(BarTime other) {
        return BarTime.add(this, other);
    }

    /**
     * Returns a BarTime equal to the difference of both BarTimes
     *
     * @param time1
     * @param time2
     * @return the difference of time1 and time2.
     */
    public static BarTime subtract(BarTime time1, BarTime time2) {
        Objects.requireNonNull(time1, "Cannot subtract the BarTimes. The first argument is null");
        Objects.requireNonNull(time2, "Cannot subtract the BarTimes. The second argument is null");
        int lcm = leastCommonMultiple(time1.getDenominator(), time2.getDenominator());

        int factor1 = lcm / time1.getDenominator();
        int factor2 = lcm / time2.getDenominator();
        int time1ExpandedEnum = time1.getNumerator() * factor1;
        int time2ExpandedEnum = time2.getNumerator() * factor2;

        int enumerator = time1ExpandedEnum - time2ExpandedEnum;
        return BarTime.of(enumerator, lcm);
    }

    /**
     * Returns a BarTime equal to the difference of this BarTime and the given
     * argument. The method makes use of the static {@code add} method and therefore
     *
     * @param other
     * @return the difference of {@code this} and {@code other}
     */
    public BarTime subtract(BarTime other) {
        return BarTime.subtract(this, other);
    }

    /**
     * Returns a BarTime equal to the fraction of both BarTimes
     *
     * @param time1
     * @param time2
     * @return the fraction of time1 and time2.
     */
    public static BarTime divide(BarTime time1, BarTime time2) {
        Objects.requireNonNull(time1, "Cannot divide the BarTimes. The first argument is null");
        Objects.requireNonNull(time2, "Cannot divide the BarTimes. The second argument is null");
        int enumerator = time1.getNumerator() * time2.getDenominator();
        int denominator = time1.getDenominator() * time2.getNumerator();
        return BarTime.of(enumerator, denominator);
    }

    /**
     * Returns a BarTime equal to the fractions of this BarTime and the given
     * argument. The method makes use of the static {@code add} method and therefore
     *
     * @param other
     * @return the fraction of {@code this} and {@code other}
     */
    public BarTime divide(BarTime other) {
        return BarTime.divide(this, other);
    }

    /**
     * Returns a BarTime equal to the multiplication of both BarTimes
     *
     * @param time1
     * @param time2
     * @return the product of time1 and time2
     */
    public static BarTime multiply(BarTime time1, BarTime time2) {
        Objects.requireNonNull(time1, "Cannot multiply the BarTimes. The first argument is null");
        Objects.requireNonNull(time2, "Cannot multiply the BarTimes. The second argument is null");
        int enumerator = time1.getNumerator() * time2.getNumerator();
        int denominator = time1.getDenominator() * time2.getDenominator();
        return BarTime.of(enumerator, denominator);
    }

    /**
     * Returns a BarTime equal to the multiplication of both BarTimes
     *
     * @param other
     * @return the product of {@code this} and {@code other}
     */
    public BarTime multiply(BarTime other) {
        return BarTime.multiply(this, other);
    }

    /**
     * Returns the maximal BarTime.
     *
     * @return the maximum of both BarTimes.
     */
    public static BarTime max(BarTime time1, BarTime time2) {
        Objects.requireNonNull(time1, "Cannot compare the BarTimes. The first argument is null");
        Objects.requireNonNull(time2, "Cannot compare the BarTimes. The second argument is null");
        if (time1.isGreater(time2)) {
            return time1;
        } else if (time1.isLess(time2)) {
            return time2;
        } else {
            // Both are equal
            return time1;
        }
    }

    /**
     * Returns the minimal BarTime.
     *
     * @return the minimum of both BarTimes.
     */
    public static BarTime min(BarTime time1, BarTime time2) {
        Objects.requireNonNull(time1, "Cannot compare the BarTimes. The first argument is null");
        Objects.requireNonNull(time2, "Cannot compare the BarTimes. The second argument is null");
        if (time1.isGreater(time2)) {
            return time2;
        } else if (time1.isLess(time2)) {
            return time1;
        } else {
            // Both are equal
            return time1;
        }
    }

    /**
     * Returns true if the instance is greater than the other BarTime.
     *
     * @return true if other occurs later than {@code this}.
     */
    public boolean isGreater(BarTime other) {
        return compareTo(other) > 0;
    }

    /**
     * Returns true if the instance is greater than or equal to the other BarTime.
     *
     * @return true if other occurs later than {@code this} or is equal.
     */
    public boolean isGreaterOrEqual(BarTime other) {
        return compareTo(other) >= 0;
    }

    /**
     * Returns true if the instance is less than the other BarTime.
     *
     * @return true if other occurs sooner than {@code this} or is equal.
     */
    public boolean isLess(BarTime other) {
        return compareTo(other) < 0;
    }

    /**
     * Returns true if the instance is less than or equal to the other BarTime.
     *
     * @return true if other occurs sooner than {@code this}.
     */
    public boolean isLessOrEqual(BarTime other) {
        return compareTo(other) <= 0;
    }

    /**
     * Compares both BarTimes as fractions.
     *
     * @return 1 if {@code this} is bigger, -1 if {@code other} is bigger and zero
     *         if both are equal.
     */
    @Override
    public int compareTo(BarTime other) {
        Objects.requireNonNull(other, "Cannot compare to null.");
        if (numerator * other.getDenominator() > other.getNumerator() * denominator) {
            return 1;
        } else if (numerator * other.getDenominator() < other.getNumerator() * denominator) {
            return -1;
        } else {
            return 0;
        }
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