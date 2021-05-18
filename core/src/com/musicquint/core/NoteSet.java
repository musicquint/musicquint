package com.musicquint.core;

import java.util.SortedSet;

public interface NoteSet extends SortedSet<Note>{

    @Override
    boolean add(Note e);

    @Override
    int size();

    BarTime getDuration();

    SortedSet<Pitch> getPitches();

    boolean isRest();

    boolean isChord();

}
