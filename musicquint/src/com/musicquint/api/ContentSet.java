package com.musicquint.api;

import java.util.SortedSet;

public interface ContentSet<T extends ContentItem> extends SortedSet<T> {

    BarTime getDuration();

    SortedSet<Pitch> getPitches();

}
