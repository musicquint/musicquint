package com.musicquint.api;

import java.util.NavigableMap;
import java.util.Objects;

import com.musicquint.impl.MQVoice;

public interface Voice extends NavigableMap<BarTime, PrincipalSet> {

    public static Voice create() {
        return new MQVoice();
    }

    public static Voice create(BarTime capacity) {
        return new MQVoice(capacity);
    }

    @Override
    PrincipalSet put(BarTime key, PrincipalSet value);

    BarTime capacity();

    default boolean fits(BarTime key, PrincipalSet value) {
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
            BarTime endLower = BarTime.add(lowerEntry.getKey(), lowerEntry.getValue().getDuration());
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
            return BarTime.add(lastEntry.getKey(), lastEntry.getValue().getDuration());
        }
    }
}
