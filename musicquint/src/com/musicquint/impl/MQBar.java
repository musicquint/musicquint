package com.musicquint.impl;

import java.util.ArrayList;

import com.musicquint.api.Bar;
import com.musicquint.api.BarAttributeMap;
import com.musicquint.api.BarTime;
import com.musicquint.api.Time;
import com.musicquint.api.Voice;
import com.musicquint.util.ForwardingList;

public class MQBar extends ForwardingList<Voice> implements Bar {

    private final BarTime capacity;

    private Integer barNumber;

    private Time time;

    private boolean isImplicit;

    private final BarAttributeMap attributeMap;

    public MQBar() {
        super(ArrayList::new);
        capacity = BarTime.FOUR_QUARTER;
        this.attributeMap = new MQBarAttributeMap();
    }

    public MQBar(BarTime capacity) {
        super(ArrayList::new);
        this.capacity = capacity;
        this.attributeMap = new MQBarAttributeMap();
    }

    @Override
    public Integer getBarNumber() {
        return barNumber;
    }

    @Override
    public void setBarNumber(Integer barNumber) {
        this.barNumber = barNumber;
    }

    @Override
    public BarTime getCapacity() {
        return capacity;
    }

    @Override
    public BarAttributeMap getAttributeMap() {
        return attributeMap;
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
    public void setImplicit(boolean isImplicit) {
        this.isImplicit = isImplicit;
    }

    @Override
    public boolean isImplicit() {
        return isImplicit;
    }
}
