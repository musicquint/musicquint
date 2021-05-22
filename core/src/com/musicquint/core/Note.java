package com.musicquint.core;

public class Note extends AbstractNote {

    public Note(Pitch pitch, BarTime duration, Type type, int dots) {
        super(pitch, duration, type, dots);
    }

    @Override
    public Category getCategory() {
        return Category.PRINCIPAL;
    }
}
