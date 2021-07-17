package com.musicquint.api;

import java.util.Comparator;
import java.util.Objects;

/**
 * The interface requires each objects of a class that implements it to
 * associate a unique BarTime to each object of the class. This unique BarTime
 * is called through the {@link #getDuration()} method. Objects that fulfill
 * this requirement are said to be measurable and the associated BarTime is
 * called the measurement of said object. As this interface also allows to be
 * implemented through an anonymous class or a lambda it fulfills the
 * requirement of a functional interface and is annotated as such.
 * </p>
 * The Measurable interface offers a {@linkplain #timeComparator()
 * canonical Comparator} which imposes an natural ordering on all Measurable
 * objects. As this order cannot be guaranteed to be consistent with equals we
 * refrained from extending this interface from the {@link Comparable}
 * interface. Additionally some default methods are given for convenience.
 * </p>
 * Trivially the {@link BarTime} class itself is Measurable. Also the comparator
 * induced by the Measurable interface is consistent with equals in BarTime and
 * the Implementation of the {@link Comparable} interface relies on the given
 * comparator given in the Measurable interface.
 */
@FunctionalInterface
public interface Measurable {

    /**
     * Gives back the associated BarTime to the measurable object. The BarTime is
     * also called the measurement of the measurable object. The implementor must
     * ensure that the null is not returned. Also it is advised but not necessary
     * that the BarTime returned is greater or equal to zero. As the interface is a
     * functional interface an anonymous or lambda implementation can be used to
     * create an instance of the measurable class.
     *
     * @return the measured BarTime
     * @see FunctionalInterface
     */
    BarTime getDuration();

    /**
     * Returns a {@link Comparator} which compares the measured BarTimes numerically
     * as rational numbers. Note that the comparator also allows for different
     * implementation of the measurable interface to be compared and is not
     * guaranteed to be consistent with equals. Nonetheless is the comparator
     * consistent with equals for the BarTime class and the implementation of the
     * {@link Comparable} interface in BarTime relies on this implementation.
     *
     * @return a {@code comparator} which returns the following for two measurable
     *         objects {@code m1} and {@code m2}:
     *         <ul>
     *         <li>{@code comparator(m1, m2) == 1}, if the measurement of {@code m1}
     *         is numerically greater than the measurement of {@code m2}.
     *         <li>{@code comparator(m1, m2) == -1}, if the measurement of
     *         {@code m1} is numerically less than the measurement of {@code m2}.
     *         <li>{@code comparator(m1, m2) == 0}, if the measurement of {@code m1}
     *         is numerically equal to the measurement of {@code m2}.
     *         </ul>
     *         The comparator throws a {@link NullPointerException} if one of the
     *         arguments is null.
     */
    static Comparator<Measurable> timeComparator() {
        return (m1, m2) -> {
            Objects.requireNonNull(m1, "Cannot compare. The first argument is null.");
            Objects.requireNonNull(m2, "Cannot compare. The second argument is null.");
            int numerator = m1.getDuration().getNumerator();
            int denominator = m1.getDuration().getDenominator();
            int otherNumerator = m2.getDuration().getNumerator();
            int otherDenominator = m2.getDuration().getDenominator();
            return Integer.compare(numerator * otherDenominator, otherNumerator * denominator);
        };
    }

    /**
     * Returns true if the Measurable {@code other} is greater according to the
     * canonical Comparator given in {@link Measurable} otherwise false.
     *
     * @param other the other given Measurable for comparison.
     * @return {@code true} if {@code this} is greater than {@code other}, otherwise
     *         false.
     * @throws NullpointerException if other is null
     * @see Measurable#timeComparator()
     */
    default boolean isGreater(Measurable other) {
        Objects.requireNonNull(other, "The other Measurable is null.");
        return timeComparator().compare(this, other) > 0;
    }

    /**
     * Returns true if the Measurable {@code other} is greater or equal in
     * comparison according to the canonical Comparator given in {@link Measurable}
     * otherwise false.
     *
     * @param other the other given Measurable for comparison.
     * @return {@code true} if {@code this} is greater or equal than {@code other},
     *         otherwise false.
     * @throws NullpointerException if other is null
     * @see Measurable#timeComparator()
     */
    default boolean isGreaterOrEqual(Measurable other) {
        Objects.requireNonNull(other, "The other Measurable is null.");
        return timeComparator().compare(this, other) >= 0;
    }

    /**
     * Returns true if the Measurable {@code other} is less according to the
     * canonical Comparator given in {@link Measurable} otherwise false.
     *
     * @param other the other given Measurable for comparison.
     * @return {@code true} if {@code this} is less than {@code other}, otherwise
     *         false.
     * @see Measurable#timeComparator()
     */
    default boolean isLess(Measurable other) {
        Objects.requireNonNull(other, "The other Measurable is null.");
        return timeComparator().compare(this, other) < 0;
    }

    /**
     * Returns true if the Measurable {@code other} is less or equal in comparison
     * according to the canonical Comparator given in {@link Measurable} otherwise
     * false.
     *
     * @param other the other given Measurable for comparison.
     * @return {@code true} if {@code this} is less or equal than {@code other},
     *         otherwise false.
     * @throws NullpointerException if other is null
     * @see Measurable#timeComparator()
     */
    default boolean isLessOrEqual(Measurable other) {
        Objects.requireNonNull(other, "The other Measurable is null.");
        return timeComparator().compare(this, other) <= 0;
    }
}
