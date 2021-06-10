package com.musicquint.impl;

import java.util.SortedSet;
import java.util.TreeMap;

import com.musicquint.api.BarAttribute;
import com.musicquint.api.BarAttributeMap;
import com.musicquint.api.BarTime;
import com.musicquint.util.ForwardingNavigabgleMap;

public class MQBarAttributeMap extends ForwardingNavigabgleMap<BarTime, SortedSet<BarAttribute>> implements BarAttributeMap {

    public MQBarAttributeMap() {
        super(TreeMap::new);
    }

}
