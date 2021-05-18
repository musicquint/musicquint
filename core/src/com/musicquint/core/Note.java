package com.musicquint.core;

public interface Note extends Comparable<Note>{

    public final static NoteType PRINCIPAL = NoteType.PRINCIPAL;

    public final static NoteType OPTIONAL = NoteType.OPTIONAL;

    Pitch getPitch();

    void setPitch();

    BarTime getDuration();

    int getDots();

    Type getType();

    NoteType getNoteType();

    void addAttribute(NoteAttribute attribute);

    <T extends NoteAttribute> T getAttribute(Class<T> attributeKey);

    public enum NoteType {
        PRINCIPAL, OPTIONAL
    }

}
