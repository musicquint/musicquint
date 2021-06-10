package com.musicquint.api;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public interface ContentSet<T extends ContentItem> extends SortedSet<T> {

    default SortedSet<Pitch> getPitches() {
        return stream().filter(ContentItem::isPitched).map(ContentItem::getPitch)
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
