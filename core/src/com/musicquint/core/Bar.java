package com.musicquint.core;

import java.util.List;

public interface Bar extends List<Voice> {

    public void add(BarTime time, BarAttribute attribute);

    public Time getTime();

    public void setTime(Time time);

    public BarTime getCapacity();

    public boolean isImplicit();

    public void setImplicit(boolean isImplicit);
}
