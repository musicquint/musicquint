package com.musicquint.api;

import java.util.List;
import java.util.NavigableMap;
import java.util.SortedSet;

public interface Voice extends NavigableMap<BarTime, Voice.ContentItem> {

    @Override
    ContentItem put(BarTime key, ContentItem value);

    boolean fits(BarTime key, Note value);

    BarTime continuity(BarTime key);

    BarTime occurence(BarTime key);

    BarTime length();

    BarTime capacity();

    interface ContentItem extends SortedSet<Note> {

        SortedSet<Pitch> getPitches();

        BarTime getDuration();

        boolean isChord();

        boolean isPitched();

        boolean isRest();

        void appendOptional(Note e);

        void insertOptional(int i, Note e);

        List<ContentItem> getOptionals();
    }
}
