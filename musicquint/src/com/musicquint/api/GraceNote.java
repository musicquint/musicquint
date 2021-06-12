package com.musicquint.api;

public class GraceNote extends AbstractNote implements OptionalItem {

    public GraceNote(Pitch pitch, BarTime duration, int dots, Type type) {
        super(pitch, duration, dots, type);
    }

}
