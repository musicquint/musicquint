package com.musicquint.api;

import java.util.NavigableMap;
import java.util.Set;

public interface BarAttributeMap extends NavigableMap<BarTime, BarAttributeMap.AttributeSet>{

    interface AttributeSet extends Set<BarAttribute> {

    }
}
