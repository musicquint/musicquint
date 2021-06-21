package com.musicquint.api;

import java.util.Collection;
import java.util.List;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.musicquint.impl.MQOptionalSet;
import com.musicquint.impl.MQPrincipalSet;
import com.musicquint.impl.MQVoice;

public interface Voice extends NavigableMap<BarTime, Voice.PrincipalSet> {

    public static Voice create() {
        return new MQVoice();
    }

    public static Voice create(BarTime capacity) {
        return new MQVoice(capacity);
    }

    @Override
    PrincipalSet put(BarTime key, Voice.PrincipalSet value);

    BarTime capacity();

    default void put(BarTime key, PrincipalItem item) {
        if (containsKey(key)) {
            get(key).add(item);
        } else {
            put(key, PrincipalSet.of(item));
        }
    }

    default void put(BarTime key, PrincipalItem... item) {
        Stream.of(item).sorted((i1, i2) -> BarTime.compareTo(i2, i1)).forEach(i -> put(key, i));
    }

    default void put(BarTime key, OptionalItem item) {
        OptionalSet optionalSet = OptionalSet.of(item);
        if (containsKey(key)) {
            get(key).appendOptional(optionalSet);
        } else {
            PrincipalSet prinicpalSet = PrincipalSet.create(next(key));
            prinicpalSet.appendOptional(optionalSet);
            put(key, prinicpalSet);
        }
    }

    default boolean fits(BarTime key, TimeMeasurable value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return lasting(key).equals(BarTime.ZERO) && value.getDuration().isLessOrEqual(next(key));
    }

    default BarTime lasting(BarTime key) {
        Objects.requireNonNull(key);
        Entry<BarTime, PrincipalSet> lowerEntry = lowerEntry(key);
        if (lowerEntry == null) {
            return BarTime.ZERO;
        } else {
            BarTime endLower = BarTime.add(lowerEntry::getKey, lowerEntry.getValue());
            return BarTime.max(BarTime.ZERO, BarTime.subtract(endLower, key));
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
            Entry<BarTime, PrincipalSet> lastEntry = lastEntry();
            return BarTime.add(lastEntry::getKey, lastEntry.getValue());
        }
    }

    interface ContentSet<T extends ContentItem> extends SortedSet<T>, TimeMeasurable {

        default SortedSet<Pitch> getPitches() {
            return stream().map(ContentItem::getPitch).filter(Optional::isPresent).map(Optional::get)
                    .collect(Collectors.toCollection(TreeSet::new));
        }

        default BarTime getDuration() {
            return stream().map(ContentItem::getDuration).max(BarTime::compareTo).orElse(BarTime.ZERO);
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

    interface PrincipalSet extends ContentSet<PrincipalItem> {

        public static PrincipalSet create(BarTime capacity) {
            return new MQPrincipalSet(capacity);
        }

        public static PrincipalSet of(Collection<PrincipalItem> collection) {
            return new MQPrincipalSet(collection);
        }

        public static PrincipalSet of(PrincipalItem... items) {
            return new MQPrincipalSet(items);
        }

        @Override
        boolean add(PrincipalItem e);

        BarTime capacity();

        @Override
        default BarTime getDuration() {
            if (isEmpty()) {
                return capacity();
            } else {
                return ContentSet.super.getDuration();
            }
        }

        void appendOptional(OptionalSet optional);

        void insertOptional(int i, OptionalSet optional);

        OptionalSet removeOptional(int i);

        void clearOptionalList();

        List<OptionalSet> getOptionalList();
    }

    interface OptionalSet extends ContentSet<OptionalItem> {

        public static OptionalSet create() {
            return new MQOptionalSet();
        }

        public static OptionalSet of(Collection<OptionalItem> collection) {
            return new MQOptionalSet(collection);
        }

        public static OptionalSet of(OptionalItem... items) {
            return new MQOptionalSet(items);
        }
    }
}
