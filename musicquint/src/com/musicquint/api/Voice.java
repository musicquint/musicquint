package com.musicquint.api;

import static com.musicquint.api.BarTime.add;
import static com.musicquint.api.BarTime.subtract;
import static com.musicquint.api.Measurable.canonicalComparator;

import java.util.Collection;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Voice is a NavigableMap which maps BarTimes to sets containing
 * PrincipalItems. As per contract of a map a specific BarTime can only be
 * mapped to one such set. Such sets are defined as {@linkplain ContentSet
 * ContentSets} and offers a variety of default methods for convenience. Another
 * way to view a voice is to see it as NavigableMap which maps a BarTime to
 * multiple {@linkplain ContentItem ContentItems} to support this view the
 * interface offers additional methods {@link #put(BarTime, PrincipalItem)}, and
 * {@link #put(BarTime, OptionalItem)}.
 * </p>
 * As a {@link ContentSet} is also a {@linkplain Measurable} object special
 * constraints are put upon all ContentItem or ContentSet with a duration
 * {@code d} that are to be added into a Voice at any given BarTime {@code t}
 * (Note that we use the {@code +} and {@code -} operator as abbreviations for
 * the {@link BarTime#add(BarTime, BarTime)} and
 * {@link BarTime#subtract(BarTime, BarTime)} methods):
 * <ul>
 * <li>After adding the item to the voice no other item can be added to the
 * voice for all BarTimes {@code t} in the interval {@code (t, t + d)}. Interval
 * boundaries are excluded from this rule. A default implementation of
 * {@link #lasting(BarTime)} is provided to check this condition.</li>
 * <li>If the voice has a higher key {@code t'} that is higher than t. Then in
 * accordance with the first constraint the BarTime {@code t + d} needs to be
 * less or equal than {@code t'} or the BarTime returned by {@link #capacity()}.
 * See also the default implementation of {@link #next(BarTime)} to help check
 * this condition.</li>
 * </ul>
 * A default implementation {@link #fits(BarTime, Measurable)} is provided to
 * check both conditions. Also we define a Voice to be consistent stuffed or
 * well-stuffed, if {@code lasting(t) == next(t)} for every {@code t} in the
 * interval [0, {@link #capacity()}].
 */
public interface Voice extends NavigableMap<BarTime, Voice.ContentSet<PrincipalItem>> {

    /**
     * Associates the key with the given ContentSet in the voice. To ensure
     * consistency of data in the voice the given implementation must ensure that
     * special constraint are always honored while adding data to the voice. Those
     * can be checked with the default implementation of
     * {@link #fits(BarTime, Measurable)}.
     *
     * @param key   the BarTime with which the specified ContentSet is to be
     *              associated
     * @param value the given ContentSet that is to be mapped to the given key
     * @return the previous ContentSet associated with {@code key}, or {@code null}
     *         if there was no mapping for {@code key}.
     * @throws NullPointerException  if the given key or value is null.
     * @throws IllegalStateException if {@link #fits(BarTime, Measurable)} is false
     *                               and for the key and value and at least one of
     *                               the constraints that a voice must obey is
     *                               violated.
     * @see #fits(BarTime, Measurable)
     */
    @Override
    Voice.ContentSet<PrincipalItem> put(BarTime key, Voice.ContentSet<PrincipalItem> value);

    /**
     * Associates the key with a ContentSet that contains the given PrincipalItem.
     * If there is also a ContentSet associated with the given key then the
     * PrincipalItem is added to the ContentSet. If no ContentSet was previously
     * associated to the key a new ContentSet is created and added. To ensure
     * consistency of data in the voice the given implementation must ensure that
     * special constraint are always honored while adding data to the voice. Those
     * can be checked with the default implementation of
     * {@link #fits(BarTime, Measurable)}.
     *
     * @param key  the BarTime with which the specified ContentSet is to be
     *             associated
     * @param item the given PrincipalItem that is to be added.
     * @throws NullPointerException     if the given key or item is null.
     * @throws IllegalStateException    if {@link #fits(BarTime, Measurable)} is
     *                                  false and for the key or item at least one
     *                                  of the constraints, that a voice must obey,
     *                                  is violated.
     * @throws IllegalArgumentException if the given key is less than zero or
     *                                  greater than the capacity of the voice.
     * @see #fits(BarTime, Measurable)
     */
    void put(BarTime key, PrincipalItem item);

    /**
     * Associates the key with ContentSet that contains the given OptionalItem. If
     * there is also a ContentSet associated with the given key then the
     * OptionalItem is added to the ContentSet. If no ContentSet was previously
     * associated to the key a new ContentSet is created and added. Note that a
     * OptionalItem does not add to the duration of the ContentSet and therefore the
     * only condition that must be satisfied is {@code lasting(key) == BarTime.ZERO}
     *
     * @param key  the BarTime with which the specified ContentSet is to be
     *             associated
     * @param item the given OptionalItem that is to be added.
     * @throws NullPointerException     if the given key or item is null.
     * @throws IllegalStateException    if {@code lasting(key) != BarTime.ZERO}
     * @throws IllegalArgumentException if the given key is less than zero or
     *                                  greater than the capacity of the voice.
     * @see #lasting(BarTime)
     * @see #capacity()
     */
    void put(BarTime key, OptionalItem item);

    /**
     * The capacity is an upper bound for all key that are to be inserted into the
     * voice. It is advised that the capacity is immutable to avoid the corruption
     * of data although it is not inconceivable that one might implement the
     * capacity in such a way, that all voice entries are removed if they exceed the
     * capacity of the voice after alteration.
     *
     * @return the capacity of the voice as BarTime
     */
    BarTime capacity();

    default void put(BarTime key, Collection<PrincipalItem> items) {
        Objects.requireNonNull(items);
        items.stream().sorted(canonicalComparator().reversed()).forEach(i -> put(key, i));
    }

    default void put(BarTime key, PrincipalItem... items) {
        put(key, Stream.of(items).collect(Collectors.toSet()));
    }

    default boolean fits(BarTime key, Measurable value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return lasting(key).equals(BarTime.ZERO) && value.isLessOrEqual(next(key));
    }

    default BarTime lasting(BarTime key) {
        Objects.requireNonNull(key);
        Entry<BarTime, ContentSet<PrincipalItem>> lowerEntry = lowerEntry(key);
        if (lowerEntry == null) {
            return BarTime.ZERO;
        } else {
            BarTime endLower = add(lowerEntry.getKey(), lowerEntry.getValue());
            return BarTime.max(BarTime.ZERO, subtract(endLower, key));
        }
    }

    default BarTime next(BarTime key) {
        Objects.requireNonNull(key);
        BarTime higherKey = higherKey(key);
        return higherKey != null ? subtract(higherKey, key) : subtract(capacity(), key);
    }

    default BarTime length() {
        if (isEmpty()) {
            return BarTime.ZERO;
        } else {
            Entry<BarTime, ContentSet<PrincipalItem>> lastEntry = lastEntry();
            return add(lastEntry.getKey(), lastEntry.getValue());
        }
    }

    interface ContentSet<T extends ContentItem> extends Set<T>, Measurable {

        default SortedSet<Pitch> getPitches() {
            return stream().map(ContentItem::getPitch).filter(Optional::isPresent).map(Optional::get)
                    .collect(Collectors.toCollection(TreeSet::new));
        }

        default BarTime getDuration() {
            return stream().max(Measurable.canonicalComparator()).map(Measurable::getDuration).orElse(BarTime.ZERO);
        }

        default boolean isPitched() {
            return stream().anyMatch(ContentItem::isPitched);
        }

        default boolean isRest() {
            return !isPitched() && !isEmpty();
        }

        default boolean isChord() {
            return getPitches().size() > 1;
        }
    }
}
