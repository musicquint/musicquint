package com.musicquint.api;

import java.util.NavigableMap;

import com.musicquint.impl.MQBarAttributeMap;

public interface BarAttributeMap extends NavigableMap<BarTime, AttributeSet> {

    public static BarAttributeMap create() {
        return new MQBarAttributeMap();
    }
}
