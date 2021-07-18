package com.musicquint.api;

import java.util.Comparator;

/**
 * A object that implements this interface is uniquely associated with
 * {@link Pitch}. This unique Pitch is called through the {@link #getPitch()}
 * method. We call object that implement this interface pitched and and the
 * associated Pitch is called the pitch of the given object. As this interface
 * also allows to be implemented through an anonymous class or a lambda it
 * fulfills the requirement of a functional interface and is annotated as such.
 * </p>
 * The Pitched interface offers a {@link Comparator} which imposes an natural
 * ordering on all Pitched objects. As this order cannot be guaranteed to be
 * consistent with equals we refrained from extending this interface from the
 * {@link Comparable} interface. Additionally some default methods are given for
 * convenience.
 * </p>
 * Trivially the {@link Pitch} class itself is Pitch. Nonetheless the given
 * comparator is <strong>not</strong> consistent with equals for this class, but
 * the comparator can be used to obtain a consistent Comparator by further
 * comparing the {@linkplain Alter alteration} of the pitch.
 *
 */
@FunctionalInterface
public interface Pitched {

    /**
     * Returns the associated {@link Pitch} to the Pitched object. The implementor
     * must ensure that null is not returned. As the interface is a functional
     * interface an anonymous or lambda implementation can be used to create an
     * instance of the Pitched class.
     *
     * @return the associated Pitch to the Pitched object.
     */
    Pitch getPitch();

    /**
     * Returns a {@link Comparator} which compares Pitched objects numerically as
     * integer numbers. Note that the comparator also allows for different
     * implementation of the pitched interface to be compared and is <strong>not
     * guaranteed to be consistent with equals</strong>.
     * </p>
     * In fact this comparator is also <strong> not consistent with equals</strong>
     * for the Pitch class as pitches that are enharmonic interchangeable are equal
     * according to this comparator but the comparator can be consistent with equals
     * by further comparing the {@linkplain Alter alteration} of two pitches.
     *
     * @return a {@code comparator} which returns the following for two pitched
     *         objects {@code p1} and {@code p2}:
     *         <ul>
     *         <li>{@code comparator(p1, p2) == 1}, if the Pitch of {@code p1} as
     *         integer is numerically bigger than the Pitch of {@code p2} as
     *         integer.
     *         <li>{@code comparator(p1, p2) == -1}, if the Pitch of {@code p1} as
     *         integer is numerically less than the Pitch of {@code p2} as integer.
     *         <li>{@code comparator(p1, p2) == 0}, if the Pitch of {@code p1} as
     *         integer is numerically equal to the Pitch of {@code p2} as integer.
     *         </ul>
     *         The comparator throws a {@link NullPointerException} if one of the
     *         arguments is null.
     */
    static Comparator<Pitched> pitchedComparator() {
        return Comparator.comparingInt(p -> p.getPitch().asInt());
    }

    /**
     * Returns true if the Pitched object {@code other} is higher according to the
     * Comparator given in {@link Pitched} otherwise false.
     *
     * @param other the other given Pitched for comparison.
     * @return {@code true} if {@code this} is higher than {@code other}, otherwise
     *         false.
     * @throws NullpointerException if other is null
     * @see Pitched#pitchedComparator()
     */
    default boolean isHigher(Pitched other) {
        return pitchedComparator().compare(this, other) > 0;
    }

    /**
     * Returns true if the Pitched object {@code other} is higher or equal according
     * to the Comparator given in {@link Pitched} otherwise false.
     *
     * @param other the other given Pitched for comparison.
     * @return {@code true} if {@code this} is higher or equal than {@code other},
     *         otherwise false.
     * @throws NullpointerException if other is null
     * @see Pitched#pitchedComparator()
     */
    default boolean isHigherOrEqual(Pitched other) {
        return pitchedComparator().compare(this, other) >= 0;
    }

    /**
     * Returns true if the Pitched object {@code other} is lower according to the
     * Comparator given in {@link Pitched} otherwise false.
     *
     * @param other the other given Pitched for comparison.
     * @return {@code true} if {@code this} is lower than {@code other}, otherwise
     *         false.
     * @throws NullpointerException if other is null
     * @see Pitched#pitchedComparator()
     */
    default boolean isLower(Pitched other) {
        return pitchedComparator().compare(this, other) < 0;
    }

    /**
     * Returns true if the Pitched object {@code other} is lower or equal according
     * to the Comparator given in {@link Pitched} otherwise false.
     *
     * @param other the other given Pitched for comparison.
     * @return {@code true} if {@code this} is lower or equal than {@code other},
     *         otherwise false.
     * @throws NullpointerException if other is null
     * @see Pitched#pitchedComparator()
     */
    default boolean isLowerOrEqual(Pitched other) {
        return pitchedComparator().compare(this, other) <= 0;
    }
}
