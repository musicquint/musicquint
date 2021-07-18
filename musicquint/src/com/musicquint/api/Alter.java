package com.musicquint.api;

import java.util.EnumSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO documentation
public enum Alter {

    FLAT_FLAT(-2),

    FLAT(-1),

    NATURAL(0),

    SHARP(1),

    DOUBLE_SHARP(2);

    public static final String REGEX = "(?<alter>(?<sharp>s{1,2})|(?<flat>f{1,2}))?";

    public static final Pattern PATTERN = Pattern.compile(REGEX);

    private final int alteration;

    private Alter(int i) {
        this.alteration = i;
    }

    public static Alter parse(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.matches()) {
            String sharps = matcher.group("sharp");
            String flats = matcher.group("flat");
            if (sharps != null) {
                return Alter.valueOf(sharps.length());
            } else if (flats != null) {
                return Alter.valueOf(-flats.length());
            } else {
                return NATURAL;
            }
        } else {
            throw new IllegalArgumentException(
                    "The input " + string + " does not match the regular expression " + REGEX);
        }
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