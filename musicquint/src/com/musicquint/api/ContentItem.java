package com.musicquint.api;

import java.util.Optional;

public interface ContentItem extends BarItem, Comparable<ContentItem>, TimeMeasurable {

    BarTime getDuration();

    Optional<Pitch> getPitch();

    void setPitch(Pitch pitch);

    int getDots();

    Type getType();

    void addNoteAttribute(NoteAttribute attribute);

    <T extends NoteAttribute> T getNoteAttribute(Class<T> key);

    default boolean isPitched() {
        return !isRest();
    }

    default boolean isRest() {
        return getPitch().isEmpty();
    }
}
