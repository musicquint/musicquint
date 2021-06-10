package com.musicquint.api;

public interface ContentItem extends BarItem {

    BarTime getDuration();

    Pitch getPitch();

    void setPitch(Pitch pitch);

    int getDots();

    Type getType();

    void addNoteAttribute(NoteAttribute attribute);

    <T extends NoteAttribute> T getNoteAttribute(Class<T> key);

    default boolean isPitched() {
        return !isRest();
    }

    default boolean isRest() {
        return getPitch() == null;
    }
}
