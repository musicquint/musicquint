package com.musicquint.api;

import java.util.List;
import java.util.Optional;

public interface Bar extends List<BarMap> {

    Optional<Integer> getBarNumber();

    void setBarNumber(Integer barNumber);

    BarTime getCapacity();

    BarAttributeMap getAttributeMap();

    Optional<Time> getTime();

    void setTime(Time time);

    void setImplicit(boolean isImplicit);

    boolean isImplicit();

}
