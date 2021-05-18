package com.musicquint.core;

import java.util.NavigableMap;


public interface Voice extends NavigableMap<BarTime, NoteSet> {

    public static Voice create() {
        return null;
    }

    public static Voice create(BarTime capacity) {
        return null;
    }

    public BarTime until(BarTime t);

    public BarTime next(BarTime t);

    public boolean fits(BarTime t, NoteSet item);

    public BarTime getDuration();

    public BarTime getCapacity();

}