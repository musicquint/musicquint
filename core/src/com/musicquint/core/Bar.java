package com.musicquint.core;


import java.util.ArrayList;

import com.musicquint.api.BarAttribute;
import com.musicquint.api.BarTime;
import com.musicquint.api.Time;
import com.musicquint.util.MQList;

public final class Bar extends MQList<Voice> {

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

    public void addAttribute(BarTime time, BarAttribute attribute) {
        // TODO Auto-generated method stub

    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public BarTime getCapacity() {
        return capacity;
    }

    public boolean isImplicit() {
        return isImplicit;
    }

    public void setImplicit(boolean isImplicit) {
        this.isImplicit = isImplicit;
    }
}