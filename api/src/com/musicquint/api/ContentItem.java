package com.musicquint.api;

import java.util.SortedSet;

public interface ContentItem<T extends Note> extends BarItem<T> {

    SortedSet<Pitch> getPitches();

    BarTime getDuration();

    boolean isChord();

    boolean isPitched();

    boolean isRest();
}