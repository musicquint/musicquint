package com.musicquint.api;

import static com.musicquint.api.BarTime.add;
import static com.musicquint.api.BarTime.subtract;

import java.util.NavigableMap;
import java.util.Objects;

/**
 * A BarMap is generic interface with a type parameter {@code T} that must
 * implement the Measurable Interface. A BarMap is a NavigableMap which maps
 * BarTimes to {@link Measurable} items. As per contract of a map a specific
 * BarTime can only be mapped to one such object.
 * </p>
 * Special constraints are put upon all Measurable items with a duration
 * {@code d} that are to be added into a BarMap at any given BarTime {@code t}
 * (Note that we use the {@code +} and {@code -} operator as abbreviations for
 * the {@link BarTime#add(BarTime, BarTime)} and
 * {@link BarTime#subtract(BarTime, BarTime)} methods):
 * <ul>
 * <li>After adding the item to the BarMap no other item can be added to the
 * BarMap for all BarTimes {@code t} in the interval {@code (t, t + d)}.
 * Interval boundaries are excluded from this rule. A default implementation of
 * {@link #lasting(BarTime)} is provided to check this condition.</li>
 * <li>If the BarMap has a higher key {@code t'} that is higher than t. Then in
 * accordance with the first constraint the BarTime {@code t + d} needs to be
 * less or equal than {@code t'} or the BarTime returned by {@link #capacity()}.
 * See also the default implementation of {@link #next(BarTime)} to help check
 * this condition.</li>
 * </ul>
 * A default implementation {@link #fits(BarTime, Measurable)} is provided to
 * check both conditions.
 */
public interface BarMap<T extends Measurable> extends NavigableMap<BarTime, T> {

    /**
     * Associates the key with the given Measurable item in the BarMap. To
     * ensure consistency of data in the BarMap the given implementation must ensure
     * that special constraint are always honored while adding data to the BarMap.
     * Those can be checked with the default implementation of
     * {@link #fits(BarTime, Measurable)}.
     *
     * @param key   the BarTime with which the specified Measurable is to
     *              be associated
     * @param value the given Measurable that is to be mapped to the given
     *              key
     * @return the previous Measurable associated with {@code key}, or
     *         {@code null} if there was no mapping for {@code key}.
     * @throws NullPointerException          if the given key or value is null.
     * @throws IllegalStateException         if {@link #fits(BarTime, Measurable)}
     *                                       is false and for the key and value and
     *                                       at least one of the constraints that a
     *                                       BarMap must obey is violated.
     * @throws UnsupportedOperationException if the BarMap does not support the
     *                                       Operation.
     * @see #fits(BarTime, Measurable)
     */
    @Override
    T put(BarTime key, T value);

    /**
     * The capacity is an upper bound for all key that are to be inserted into the
     * BarMap. It is advised that the capacity is immutable to avoid the corruption
     * of data although it is not inconceivable that one might implement the
     * capacity in such a way, that all BarMap entries are removed if they exceed the
     * capacity of the BarMap after alteration.
     *
     * @return the capacity of the BarMap as BarTime.
     */
    BarTime capacity();

    /**
     * Returns true if the Measurable with the same measurement of the given
     * Measurable value would fit in the BarMap. Note that this method check the
     * stricter condition for PrincipalItems. For OptionalItems the weaker condition
     * {@code lasting(key} == BarTime.ZERO} suffices.
     *
     * @param key   the time at which the value would be added to the BarMap
     * @param value a measurable value with a given measurement.
     * @throws NullPointerException if the key or value is null.
     * @return true if the Measurable item would fit into the BarMap otherwise false.
     * @see #lasting(BarTime)
     */
    default boolean fits(BarTime key, Measurable value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return lasting(key).equals(BarTime.ZERO) && value.isLessOrEqual(next(key));
    }

    /**
     * Returns the duration for which a lower item has still a lasting effect on the
     * BarMap. Any item that is added to a BarMap prohibits any further entries for
     * the measured duration of the item. Therefore this methods imposes the
     * condition for all items that are to be added to the BarMap that
     * {@code lasting(key) == BarTime.ZERO}.
     *
     * @param key the time that is checked for any lasting impact of a lower item.
     * @throws NullPointerException if the key is null.
     * @return if {@code l} is the BarTime of the lower key as given by
     *         {@link #lowerEntry(BarTime)} and {@code d} the duration of the
     *         associated Measurable object then:
     *         </p>
     *         {@code max(0, l + d - key)}
     */
    default BarTime lasting(BarTime key) {
        Objects.requireNonNull(key);
        Entry<BarTime, T> lowerEntry = lowerEntry(key);
        if (lowerEntry == null) {
            return BarTime.ZERO;
        } else {
            BarTime endLower = add(lowerEntry.getKey(), lowerEntry.getValue());
            return BarTime.max(BarTime.ZERO, subtract(endLower, key));
        }
    }

    /**
     * The duration in which the next entry is occurring in the BarMap or the
     * duration in which the capacity is reached if no higher entry is present.
     *
     * @param key the time that is checked for any higher items.
     * @throws NullPointerException if the key is null.
     * @return
     */
    default BarTime next(BarTime key) {
        Objects.requireNonNull(key);
        BarTime higherKey = higherKey(key);
        return higherKey != null ? subtract(higherKey, key) : subtract(capacity(), key);
    }

    /**
     * The length of the BarMap as BarTime
     *
     * @return the last occurring BarTime plus the given measurement of the
     *         associated Measurable or zero if the BarMap is empty.
     */
    default BarTime length() {
        if (isEmpty()) {
            return BarTime.ZERO;
        } else {
            Entry<BarTime, T> lastEntry = lastEntry();
            return add(lastEntry.getKey(), lastEntry.getValue());
        }
    }
}
