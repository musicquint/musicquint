package com.musicquint.api;

import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static final String REGEX = "(?<octave>(?<subsmall>,{1,3})|(?<lined>\'{1,5}))?";

    public static final Pattern PATTERN = Pattern.compile(REGEX);

    private final int octaveNumber;

    private Octave(int i) {
        octaveNumber = i;
    }

    public static Octave parse(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.matches()) {
            String subgreat = matcher.group("subsmall");
            String lined = matcher.group("lined");
            if (subgreat != null) {
                return Octave.valueOf(3 - subgreat.length());
            } else if(lined != null) {
                return Octave.valueOf(3 + lined.length());
            } else {
                return Octave.SMALL;
            }
        } else {
            throw new IllegalArgumentException(
                    "The input " + string + " does not match the regular expression " + REGEX);
        }
    }

    public static Octave valueOf(int i) {
        return EnumSet.allOf(Octave.class).stream().filter(t -> t.asInt() == i).findAny().get();
    }

    public int asInt() {
        return octaveNumber;
    }

    public String getSimpleName() {
        switch (this) {
        case SUBCONTRA:
            return ",,,";
        case CONTRA:
            return ",,";
        case GREAT:
            return ",";
        case SMALL:
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