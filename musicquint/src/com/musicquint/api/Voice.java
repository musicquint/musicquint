package com.musicquint.api;

import java.util.NavigableMap;
import java.util.Objects;

public interface Voice extends NavigableMap<BarTime, PrincipalSet> {

    @Override
    PrincipalSet put(BarTime key, PrincipalSet value);

    BarTime capacity();

    default boolean fits(BarTime key, PrincipalSet value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return lasting(key).equals(BarTime.ZERO) && value.getDuration().isLessOrEqual(nextIn(key));
    }

    default BarTime lasting(BarTime key) {
        Objects.requireNonNull(key);
        Entry<BarTime, PrincipalSet> lowerEntry = floorEntry(key);
        if (lowerEntry == null) {
            return BarTime.ZERO;
        } else {
            BarTime endLower = BarTime.add(lowerEntry.getKey(), lowerEntry.getValue().getDuration());
            return BarTime.max(BarTime.ZERO, BarTime.subtract(endLower, key));
        }
    }

    default BarTime nextIn(BarTime key) {
        Objects.requireNonNull(key);
        BarTime ceilingKey = ceilingKey(key);
        if (ceilingKey != null) {
            return BarTime.subtract(ceilingKey, key);
        } else {
            return BarTime.subtract(capacity(), key);
        }
    }

    default BarTime length() {
        if (isEmpty()) {
            return BarTime.ZERO;
        } else {
            Entry<BarTime, PrincipalSet> lastEntry = lastEntry();
            return BarTime.add(lastEntry.getKey(), lastEntry.getValue().getDuration());
        }
    }
}
