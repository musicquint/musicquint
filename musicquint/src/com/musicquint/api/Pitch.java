package com.musicquint.api;

import java.util.Objects;

/**
 * Data structure for a pitch in the note system. Every Pitch consists of a Step, an Alteration and an Octave.
 */
public final class Pitch implements Comparable<Pitch> {

    private Step step;

    private Alter alter;

    private Octave octave;

    public Pitch(Step step, Alter alter, Octave octave) {
        this.step = Objects.requireNonNull(step);
        this.alter = Objects.requireNonNullElse(alter, Alter.NATURAL);
        this.octave = Objects.requireNonNullElse(octave, Octave.ONE_LINED);
    }

    public Pitch(Step step, Octave octave) {
        this(step, null, octave);
    }

    public Pitch(Step step) {
        this(step, null, null);
    }

    /**
     * Returns the Step of the Pitch
     * @return {@link Step}
     */
    public Step getStep() {
        return step;
    }

    /**
     * Setter for the step in the pitch. Throws a NullpointerException if the input variable is null
     * @param step
     * @throws NullPointerException
     */
    public void setStep(Step step) {
        this.step = Objects.requireNonNull(step);
    }

    /**
     * Returns the alteration of the Pitch
     * @return {@link Alter}
     */
    public Alter getAlter() {
        return alter;
    }

    /**
     * Setter for the alteration in the pitch. Throws a NullpointerException if the input variable is null
     * @param alter
     * @throws NullPointerException
     */
    public void setAlter(Alter alter) {
        this.alter = Objects.requireNonNull(alter);
    }

    /**
     * Returns the alteration of the Pitch
     * @return {@link Octave}
     */
    public Octave getOctave() {
        return octave;
    }

    /**
     * Setter for the octave in the pitch. Throws a NullpointerException if the input variable is null
     * @param octave
     * @throws NullPointerException
     */
    public void setOctave(Octave octave) {
        this.octave = Objects.requireNonNull(octave);
    }

    /**
     * Every Pitch has an integer for Midi-output associated with it.
     * @return
     */
    public int asInt() {
        return step.asInt() + alter.asInt() + octave.asInt() * 12;
    }

    @Override
    public int compareTo(Pitch o) {
        if(asInt() > o.asInt()) {
            return 1;
        } else if(asInt() < o.asInt()) {
            return -1;
        } else {
            if(getStep().asInt() > o.getStep().asInt()) {
                return 1;
            } else if(getStep().asInt() < o.getStep().asInt()) {
                return -1;
            } else {
                return 0;
            }
        }
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
}