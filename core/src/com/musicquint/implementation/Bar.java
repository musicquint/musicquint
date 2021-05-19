package com.musicquint.implementation;

import java.util.ArrayList;

import com.musicquint.core.BarAttribute;
import com.musicquint.core.BarTime;
import com.musicquint.core.Time;
import com.musicquint.core.Voice;

public final class Bar extends MQList<Voice> implements com.musicquint.core.Bar {

    private BarTime capacity;

    private Time time;

    private boolean isImplicit;

    public Bar() {
        this(BarTime.FOUR_QUARTER);
    }

    public Bar(BarTime capacity) {
        super(ArrayList::new);
        this.capacity = capacity;
    }

    @Override
    public void addAttribute(BarTime time, BarAttribute attribute) {
        // TODO Auto-generated method stub

    }

    @Override
    public Time getTime() {
        return time;
    }

    @Override
    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public BarTime getCapacity() {
        return capacity;
    }

    @Override
    public boolean isImplicit() {
        return isImplicit;
    }

    @Override
    public void setImplicit(boolean isImplicit) {
        this.isImplicit = isImplicit;
    }
}
