package com.musicquint.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.musicquint.util.ForwardingList;

public class MBar extends ForwardingList<Voice> implements Bar {

    private final BarTime capacity;

    Integer barNumber;

    public MBar() {
        super(ArrayList::new);
        this.capacity = BarTime.FOUR_QUARTER;
    }

    public MBar(BarTime capacity) {
        super(ArrayList::new);
        this.capacity = capacity;
    }

    public MBar(Collection<Voice> voices) {
        super(ArrayList::new);
        this.capacity = voices.stream().map(Voice::length).max(BarTime::compareTo).orElse(BarTime.FOUR_QUARTER);
        addAll(voices);
    }

    @Override
    public Optional<Integer> getBarNumber() {
        return Optional.ofNullable(barNumber);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Time> getTime() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setTime(Time time) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setImplicit(boolean isImplicit) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isImplicit() {
        // TODO Auto-generated method stub
        return false;
    }

}
