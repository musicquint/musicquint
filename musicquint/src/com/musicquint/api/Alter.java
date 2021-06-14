package com.musicquint.api;

import java.util.EnumSet;

//TODO documentation
public enum Alter {

    FLAT_FLAT(-2), FLAT(-1), NATURAL(0), SHARP(1), DOUBLE_SHARP(2);

    private final int alteration;

    Alter(int i) {
        this.alteration = i;
    }

    public static Alter valueOf(int i) {
        return EnumSet.allOf(Alter.class).stream().filter(t -> t.asInt() == i).findFirst().get();
    }

    public int asInt() {
        return alteration;
    }

    public String getSimpleName() {
        switch (this) {
        case DOUBLE_SHARP:
            return "ss";
        case FLAT:
            return "f";
        case FLAT_FLAT:
            return "ff";
        case NATURAL:
            return "";
        case SHARP:
            return "s";
        default:
            throw new RuntimeException("The Alteration is not defined " + this);
        }
    }
}