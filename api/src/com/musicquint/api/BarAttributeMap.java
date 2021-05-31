package com.musicquint.api;

import java.util.NavigableMap;
import java.util.Set;

public interface BarAttributeMap extends NavigableMap<BarTime, BarAttributeMap.AttributeSet> {

    void put(BarTime key, BarAttribute value);

    boolean remove(Object key, Object value);

    interface AttributeSet extends Set<BarAttribute> {

    }
}
