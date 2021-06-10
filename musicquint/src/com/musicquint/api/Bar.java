package com.musicquint.api;

import java.util.List;

import com.musicquint.impl.MQBar;

public interface Bar extends List<Voice> {

    public static Bar create() {
        return new MQBar();
    }

    public static Bar create(BarTime capacity) {
        return new MQBar(capacity);
    }

    Integer getBarNumber();

    void setBarNumber(Integer barNumber);

    BarTime getCapacity();

    BarAttributeMap getAttributeMap();

    Time getTime();

    void setTime(Time time);

    void setImplicit(boolean isImplicit);

    boolean isImplicit();

}
