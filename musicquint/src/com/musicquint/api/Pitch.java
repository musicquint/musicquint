package com.musicquint.api;

import static java.util.Comparator.comparingInt;

import java.util.Comparator;
import java.util.Objects;

/**
 * Data structure for a pitch in the note system. Every Pitch consists of a
 * Step, an Alteration and an Octave.
 */
public class Pitch implements Comparable<Pitch> {

    private static final Comparator<Pitch> COMPARATOR = comparingInt((Pitch p) -> p.asInt())
            .thenComparingInt(p -> p.getStep().asInt());

    private Step step;

    private Alter alter;

    private Octave octave;

    private Pitch(Builder builder) {
        this.step = Objects.requireNonNull(builder.step);
        this.alter = Objects.requireNonNullElse(builder.alter, Alter.NATURAL);
        this.octave = Objects.requireNonNullElse(builder.octave, Octave.ONE_LINED);
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
     * Setter for the step in the pitch. Throws a NullpointerException if the input
     * variable is null
     *
     * @param step
     * @throws NullPointerException
     */
    public void setStep(Step step) {
        this.step = Objects.requireNonNull(step);
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
     * Setter for the alteration in the pitch. Throws a NullpointerException if the
     * input variable is null
     *
     * @param alter
     * @throws NullPointerException
     */
    public void setAlter(Alter alter) {
        this.alter = Objects.requireNonNull(alter);
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
     * Setter for the octave in the pitch. Throws a NullpointerException if the
     * input variable is null
     *
     * @param octave
     * @throws NullPointerException
     */
    public void setOctave(Octave octave) {
        this.octave = Objects.requireNonNull(octave);
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
    public int compareTo(Pitch o) {
        return COMPARATOR.compare(this, o);
    }

    public static Comparator<Pitch> comparator() {
        return COMPARATOR;
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

    public static class Builder {

        private Step step;

        private Alter alter;

        private Octave octave;

        public Builder step(Step step) {
            this.step = step;
            return this;
        }

        public Builder octave(Octave octave) {
            this.octave = octave;
            return this;
        }

        public Builder alter(Alter alter) {
            this.alter = alter;
            return this;
        }

        public Pitch build() {
            return new Pitch(this);
        }
    }
}