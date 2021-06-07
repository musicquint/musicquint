package com.musicquint.api;

import java.util.List;

public interface Bar extends List<Voice> {

    Integer getBarNumber();

    void setBarNumber(Integer barNumber);

    BarTime getCapacity();

    BarMap getAttributeMap();

    Time getTime();

    void setTime(Time time);

}
