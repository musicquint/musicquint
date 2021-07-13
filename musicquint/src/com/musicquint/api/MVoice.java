package com.musicquint.api;

import static com.musicquint.api.ContentItem.pitchComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.TreeSet;

import com.musicquint.util.ForwardingNavigabgleMap;
import com.musicquint.util.ForwardingSortedSet;

public class MVoice extends ForwardingNavigabgleMap<BarTime, Voice.ContentSet<PrincipalItem>> implements Voice {

    private final BarTime capacity;

    public MVoice() {
        super(TreeMap::new);
        this.capacity = BarTime.FOUR_QUARTER;
    }

    @Override
    public ContentSet<PrincipalItem> put(BarTime key, ContentSet<PrincipalItem> value) {
        Objects.requireNonNull(key, "The given key is null.");
        Objects.requireNonNull(value, "The given value is null.");
        checkRangeOfKey(key);
        if (fits(key, value)) {
            return super.put(key, value);
        } else {
            throw new IllegalStateException("The given value " + value + "does not fit into the voice at " + key);
        }
    }

    private void checkRangeOfKey(BarTime key) {
        if (key.isLess(BarTime.ZERO) || key.isGreater(capacity)) {
            throw new IllegalArgumentException("The given key " + key + "is out of range. All key must be greater than "
                    + BarTime.ZERO + " or less than " + capacity);
        }
    }

    @Override
    public void put(BarTime key, PrincipalItem item) {
        Objects.requireNonNull(key, "The given key is null.");
        Objects.requireNonNull(item, "The given item is null.");
        checkRangeOfKey(key);
        if (fits(key, item)) {
            if (containsKey(key)) {
                get(key).add(item);
            } else {
                PrincipalSet set = new PrincipalSet(item);
                put(key, set);
            }
        }
    }

    @Override
    public void put(BarTime key, OptionalItem item) {
        Objects.requireNonNull(key, "The given key is null.");
        Objects.requireNonNull(item, "The given item is null.");
        checkRangeOfKey(key);
        if (lasting(key).equals(BarTime.ZERO)) {
            if (containsKey(key)) {
                get(key).addOptional(new OptionalSet(item));
            } else {
                OptionalSet optionalSet = new OptionalSet(item);
                PrincipalSet principalSet = new PrincipalSet(next(key));
                principalSet.addOptional(optionalSet);
                put(key, principalSet);
            }
        } else {
            throw new IllegalStateException(
                    "The OptionalItem canot be added as previous item has still a lasting effect.");
        }
    }

    @Override
    public BarTime capacity() {
        return capacity;
    }

    private static abstract class MContentSet<T extends ContentItem> extends ForwardingSortedSet<T> implements ContentSet<T> {

        protected BarTime capacity;

        private List<ContentSet<OptionalItem>> decoration;

        protected MContentSet() {
            super(() -> new TreeSet<>(pitchComparator()));
        }

        protected MContentSet(BarTime capacity) {
            super(() -> new TreeSet<>(pitchComparator()));
            this.capacity = capacity;
            this.decoration = new ArrayList<>();
        }

        @Override
        public List<ContentSet<OptionalItem>> getDecoration() {
            return decoration;
        }
    }

    public static class PrincipalSet extends MContentSet<PrincipalItem> {

        public PrincipalSet(BarTime capacity) {
            super(capacity);
        }

        public PrincipalSet(PrincipalItem item) {
            this(item.getDuration());
            add(item);
        }

        @Override
        public boolean add(PrincipalItem e) {
            Objects.requireNonNull(e, "The given item is null.");
            if (e.isGreater(capacity)) {
                throw new IllegalStateException("The measurement of the item " + e.getDuration()
                        + " exceeds the capacity " + capacity + " of the set");
            }
            if (isEmpty() && e.isLess(capacity)) {
                capacity = e.getDuration();
            }
            return super.add(e);
        }
    }

    public static class OptionalSet extends MContentSet<OptionalItem> {

        public OptionalSet(BarTime capacity) {
            super(capacity);
        }

        public OptionalSet(OptionalItem item) {
            super(item.getDuration());
        }

        @Override
        public List<ContentSet<OptionalItem>> getDecoration() {
            return Collections.emptyList();
        }
    }
}
