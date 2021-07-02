package com.musicquint.api;

import java.util.Comparator;
import java.util.Objects;

/**
 *
 *
 */
@FunctionalInterface
public interface Measurable {

    BarTime getDuration();

    static Comparator<Measurable> canonicalComparator() {
        return (m1, m2) -> {
            int numerator = m1.getDuration().getNumerator();
            int denominator = m1.getDuration().getDenominator();
            int otherNumerator = m2.getDuration().getNumerator();
            int otherDenominator = m2.getDuration().getDenominator();
            if (numerator * otherDenominator > otherNumerator * denominator) {
                return 1;
            } else if (numerator * otherDenominator < otherNumerator * denominator) {
                return -1;
            } else {
                return 0;
            }
        };
    }

    default boolean isGreater(Measurable other) {
        return canonicalComparator().compare(this, other) > 0;
    }

    default boolean isGreaterOrEqual(Measurable other) {
        return canonicalComparator().compare(this, other) >= 0;
    }

    default boolean isLess(Measurable other) {
        return canonicalComparator().compare(this, other) < 0;
    }

    default boolean isLessOrEqual(Measurable other) {
        return canonicalComparator().compare(this, other) <= 0;
    }

    /**
     * Returns the minimal Measurable according to the order induced. If both are
     * equal according to the comparator the first argument is given back.
     *
     * @return the minimum of both Measurables.
     */
    static <T extends Measurable> T min(T time1, T time2) {
        Objects.requireNonNull(time1, "Cannot compare. The first argument is null");
        Objects.requireNonNull(time2, "Cannot compare. The second argument is null");
        if (time1.isGreater(time2)) {
            return time2;
        } else if (time1.isLess(time2)) {
            return time1;
        } else {
            // Both are equal according to the comparator
            return time1;
        }
    }

    /**
     * Returns the maximal Measurable according to the order induced. If both are
     * equal according to the comparator the first argument is given back.
     *
     * @return the maximum of both Measurables.
     */
    static <T extends Measurable> T max(T time1, T time2) {
        Objects.requireNonNull(time1, "Cannot compare. The first argument is null");
        Objects.requireNonNull(time2, "Cannot compare. The second argument is null");
        if (time1.isGreater(time2)) {
            return time1;
        } else if (time1.isLess(time2)) {
            return time2;
        } else {
            // Both are equal according to the comparator
            return time1;
        }
    }
}
