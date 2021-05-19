package com.musicquint.implementation;

import java.util.Objects;
import java.util.TreeMap;

import com.musicquint.core.BarTime;
import com.musicquint.core.ContentItem;

public class Voice extends MQMap<BarTime, ContentItem> implements com.musicquint.core.Voice {

    private BarTime capacity;

    public Voice() {
        super(TreeMap::new);
        capacity = BarTime.FOUR_QUARTER;
    }

    public Voice(BarTime capacity) {
        super(TreeMap::new);
        this.capacity = capacity;
    }

    @Override
    public ContentItem put(BarTime key, ContentItem value) {
        if (fits(key, value)) {
            return super.put(key, value);
        } else {
            throw new IllegalArgumentException("The item " + value + "does not fit into the voice at " + key);
        }
    }

    @Override
    public BarTime until(BarTime t) {
        if (lowerKey(t) == null) {
            return BarTime.ZERO;
        } else {
            BarTime lower = lowerKey(t);
            BarTime lowerEnd = BarTime.add(lower, get(lower).getDuration());
            return BarTime.max(BarTime.ZERO, BarTime.subtract(lowerEnd, t));
        }
    }

    @Override
    public BarTime next(BarTime t) {
        if (higherKey(t) == null) {
            return BarTime.subtract(capacity, t);
        } else {
            BarTime higher = higherKey(t);
            return BarTime.subtract(higher, t);
        }
    }

    @Override
    public boolean fits(BarTime t, ContentItem item) {
        Objects.requireNonNull(t, "The given time is null");
        Objects.requireNonNull(item, "The given item is null");
        return t.isGreaterThanOrEqual(BarTime.ZERO) && until(t).equals(BarTime.ZERO)
                && item.getDuration().isLessThanOrEqual(next(t));
    }

    @Override
    public BarTime getDuration() {
        if (isEmpty()) {
            return BarTime.ZERO;
        } else {
            return BarTime.add(lastKey(), lastEntry().getValue().getDuration());
        }
    }

    @Override
    public BarTime getCapacity() {
        return capacity;
    }

}
