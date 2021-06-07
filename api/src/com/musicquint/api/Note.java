package com.musicquint.api;

import java.util.Collection;

public interface Note extends Comparable<Note> {

    Pitch getPitch();

    BarTime getDuration();

    Type getType();

    int getDots();

    boolean isPitched();

    boolean isRest();

    void addAttribute(NoteAttribute attribute);

    <T extends NoteAttribute> T getAttribute(Class<T> attributeClass);

    Collection<NoteAttribute> getAttributes();

}
