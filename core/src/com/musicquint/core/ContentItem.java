package com.musicquint.core;

import java.util.SortedSet;

public interface ContentItem extends SortedSet<Note>, BarItem {

    @Override
    boolean add(Note e);

    boolean addDecoration(ContentItem decorationItem);

    @Override
    int size();

    BarTime getDuration();

    SortedSet<Pitch> getPitches();

    boolean isRest();

    boolean isChord();

}
