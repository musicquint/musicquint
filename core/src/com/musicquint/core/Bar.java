package com.musicquint.core;

import java.util.List;

public interface Bar extends List<Voice> {

    public static Bar create() {
        return new com.musicquint.implementation.Bar();
    }

    public static Bar create(BarTime capacity) {
        return new com.musicquint.implementation.Bar(capacity);
    }

    public void addAttribute(BarTime time, BarAttribute attribute);

    public Time getTime();

    public void setTime(Time time);

    public BarTime getCapacity();

    public boolean isImplicit();

    public void setImplicit(boolean isImplicit);
}
