package com.musicquint.api;

import java.util.List;
import java.util.SortedSet;

public interface ContentItem extends BarItem<Note> {

    SortedSet<Pitch> getPitches();

    BarTime getDuration();

    boolean isChord();

    boolean isPitched();

    boolean isRest();

    void appendOptional(ContentItem e);

    List<ContentItem> getOptionals();
}