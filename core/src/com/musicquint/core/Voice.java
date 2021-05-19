package com.musicquint.core;

import java.util.NavigableMap;


public interface Voice extends NavigableMap<BarTime, ContentItem> {

    public static Voice create() {
        return new com.musicquint.implementation.Voice();
    }

    public static Voice create(BarTime capacity) {
        return new com.musicquint.implementation.Voice(capacity);
    }

    ContentItem put(BarTime key, ContentItem value);

    BarTime until(BarTime t);

    BarTime next(BarTime t);

    boolean fits(BarTime t, ContentItem item);

    BarTime getDuration();

    BarTime getCapacity();

}