package com.musicquint.api;

import java.util.EnumSet;

//TODO documentation
public enum Octave {

    SUBCONTRA(0),

    CONTRA(1),

    GREAT(2),

    SMALL(3),

    ONE_LINED(4),

    TWO_LINED(5),

    THREE_LINED(6),

    FOUR_LINED(7),

    FIVE_LINED(8);

    private final int octaveNumber;

    private Octave(int i) {
        octaveNumber = i;
    }

    public static Octave valueOf(int i) {
        return EnumSet.allOf(Octave.class).stream().filter(t -> t.asInt() == i).findAny().get();
    }

    public int asInt() {
        return octaveNumber;
    }

    public String getSimpleName() {
        switch(this) {
        case SUBCONTRA:
            return ",,,";
        case CONTRA:
            return ",,";
        case SMALL:
            return ",";
        case GREAT:
            return "";
        case ONE_LINED:
            return "'";
        case TWO_LINED:
            return "''";
        case THREE_LINED:
            return "'''";
        case FOUR_LINED:
            return "''''";
        case FIVE_LINED:
            return "'''''";
        default:
            throw new RuntimeException("The Octave " + this + " does not define a simple name");
        }
    }
}