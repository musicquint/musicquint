package com.musicquint.api;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Data structure for a pitch in the note system. Every Pitch consists of a
 * Step, an Alteration and an Octave.
 */
public class Pitch implements Pitched {

    public static final String REGEX = "(?<pitch>" + Step.REGEX + Alter.REGEX + Octave.REGEX + ")";

    public static final Pattern PATTERN = Pattern.compile(REGEX);

    // Class fields
    private final Step step;

    private final Alter alter;

    private final Octave octave;

    public Pitch(Step step, Alter alter, Octave octave) {
        this.step = Objects.requireNonNull(step);
        this.alter = Objects.requireNonNullElse(alter, Alter.NATURAL);
        this.octave = Objects.requireNonNullElse(octave, Octave.SMALL);
    }

    public static Pitch parse(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (matcher.matches()) {
            String step = matcher.group("step");
            String alter = Objects.requireNonNullElse(matcher.group("alter"), "");
            String octave = Objects.requireNonNullElse(matcher.group("octave"), "");
            return new Pitch(Step.parse(step), Alter.parse(alter), Octave.parse(octave));
        } else {
            throw new IllegalArgumentException("The input " + string + " does not match the pattern " + REGEX);
        }
    }

    /**
     * Returns the Step of the Pitch
     *
     * @return {@link Step}
     */
    public Step getStep() {
        return step;
    }

    /**
     * Returns the alteration of the Pitch
     *
     * @return {@link Alter}
     */
    public Alter getAlter() {
        return alter;
    }

    /**
     * Returns the alteration of the Pitch
     *
     * @return {@link Octave}
     */
    public Octave getOctave() {
        return octave;
    }

    /**
     * Every Pitch has an integer for Midi-output associated with it.
     *
     * @return
     */
    public int asInt() {
        return step.asInt() + alter.asInt() + octave.asInt() * 12;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alter == null) ? 0 : alter.hashCode());
        result = prime * result + ((octave == null) ? 0 : octave.hashCode());
        result = prime * result + ((step == null) ? 0 : step.hashCode());
        return result;
    }

    @Override
    public Pitch getPitch() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pitch other = (Pitch) obj;
        if (alter != other.alter) {
            return false;
        }
        if (octave != other.octave) {
            return false;
        }
        if (step != other.step) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return step.getSimpleName() + alter.getSimpleName() + octave.getSimpleName();
    }
}