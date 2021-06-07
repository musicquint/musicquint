package com.musicquint.api;

public interface ContentItem extends BarItem {

    BarTime getDuration();

    Pitch getPitch();

    int getDots();

    Type getType();

    void addNoteAttribute(NoteAttribute attribute);

    <T extends NoteAttribute> T getNoteAttribute(Class<T> key);

}
