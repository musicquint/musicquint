package com.musicquint.core;

import java.util.EnumSet;

//TODO documentation
public enum Alter {

    FLAT_FLAT(-2), FLAT(-1), NATURAL(0), SHARP(1), DOUBLE_SHARP(2);

    private final int alteration;

    Alter(int i) {
        this.alteration = i;
    }

    public static Alter valueOf(int i) {
        return EnumSet.allOf(Alter.class).stream().filter(t -> t.asInt() == i).findAny().get();
    }

    public int asInt() {
        return alteration;
    }

}