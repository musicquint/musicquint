package com.musicquint.api;

public final class Note extends AbstractNote implements PrincipalItem {

    public Note(Pitch pitch, BarTime duration, int dots, Type type) {
        super(pitch, duration, dots, type);
    }

}
