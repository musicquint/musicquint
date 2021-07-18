package com.musicquint.api;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO documentation
public enum Step {

    C(0),

    D(2),

    E(4),

    F(5),

    G(7),

    A(9),

    B(11);

    private final int step;

    // Regular Expression
    public static final String REGEX = "(?<step>[a-g|A-G])";

    public static final Pattern PATTERN = Pattern.compile(REGEX);

    public static Step parse(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.matches()) {
            return Step.valueOf(matcher.group("step").toUpperCase());
        } else {
            throw new IllegalArgumentException("The input " + string + " does not match the pattern " + REGEX);
        }
    }

    private Step(int i) {
        this.step = i;
    }

    public int asInt() {
        return step;
    }

    public String getSimpleName() {
        return toString().toLowerCase();
    }
}
