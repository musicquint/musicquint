package com.musicquint.api;

import java.util.Collection;
import java.util.Comparator;
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
 * ContentSets} and offers a variety of default methods for convenience.
 *
 */
public interface Voice extends NavigableMap<BarTime, Voice.ContentSet<PrincipalItem>> {

    Voice.ContentSet<PrincipalItem> put(BarTime key, Voice.ContentSet<PrincipalItem> value);

    void put(BarTime key, PrincipalItem item);

    void put(BarTime key, OptionalItem item);

    BarTime capacity();

    default void put(BarTime key, Collection<PrincipalItem> items) {
        Comparator<Measurable> comparator = Measurable.canonicalComparator();
        items.stream().sorted(comparator.reversed()).forEach(i -> put(key, i));
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
            BarTime endLower = BarTime.add(lowerEntry.getKey(), lowerEntry.getValue());
            return Measurable.max(BarTime.ZERO, BarTime.subtract(endLower, key));
        }
    }

    default BarTime next(BarTime key) {
        Objects.requireNonNull(key);
        BarTime higherKey = higherKey(key);
        if (higherKey != null) {
            return BarTime.subtract(higherKey, key);
        } else {
            return BarTime.subtract(capacity(), key);
        }
    }

    default BarTime length() {
        if (isEmpty()) {
            return BarTime.ZERO;
        } else {
            Entry<BarTime, ContentSet<PrincipalItem>> lastEntry = lastEntry();
            return BarTime.add(lastEntry.getKey(), lastEntry.getValue());
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
            return !isPitched();
        }

        default boolean isChord() {
            return getPitches().size() > 1;
        }
    }
}
