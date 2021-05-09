package com.musicquint.core;

public class Time {

    public final int beats;

    public final int beatsType;

    public Time(int beats, int beatsType) {
        this.beats = beats;
        this.beatsType = beatsType;
    }

    public int getBeats() {
        return beats;
    }

    public int getBeatsType() {
        return beatsType;
    }
}
