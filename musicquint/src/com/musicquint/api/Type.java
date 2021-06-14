package com.musicquint.api;

import java.util.EnumSet;
import java.util.NoSuchElementException;

/**
 * Notes and Rest usually have a Type for visual representation.
 */
public enum Type implements Comparable<Type> {

    BREVE(BarTime.BREVE),

    WHOLE(BarTime.WHOLE),

    HALF(BarTime.HALF),

    QUARTER(BarTime.QUARTER),

    EIGHTH(BarTime.EIGHTH),

    SIXTEENTH(BarTime.SIXTEENTH),

    THIRTY_SECOND(BarTime.THIRTY_SECOND),

    SIXTHY_FOURTH(BarTime.SIXTHY_FOURTH),

    ONE_HUNDERED_TWENTHY_EIGHTH(BarTime.ONE_HUNDERED_TWENTHY_EIGHTH);

    private BarTime typeDuration;

    /**
     * Private Constructor
     * @param t
     */
    private Type(BarTime t) {
        typeDuration = t;
    }

    /**
     * Static factory for the class type.
     * @param t
     * @return
     * @throws NoSuchElementException
     */
    public static Type of(BarTime t) {
        return EnumSet.allOf(Type.class).stream().filter(type -> type.asBarTime().equals(t)).findFirst().get();
    }

    /**
     * Every type is associated to a BarTime.
     * @return
     */
    public BarTime asBarTime() {
        return typeDuration;
    }

    public String getSimpleName() {
        switch(this) {
        case BREVE:
            return "\\breve";
        case WHOLE:
            return "1";
        case HALF:
            return "2";
        case QUARTER:
            return "4";
        case EIGHTH:
            return "8";
        case SIXTEENTH:
            return "16";
        case THIRTY_SECOND:
            return "32";
        case SIXTHY_FOURTH:
            return "64";
        case ONE_HUNDERED_TWENTHY_EIGHTH:
            return "128";
        default:
            throw new RuntimeException("The Type " + this + " has no simple name.");
        }
    }
}