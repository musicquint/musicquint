package com.musicquint.impl;

import java.util.TreeMap;

import com.musicquint.api.AttributeSet;
import com.musicquint.api.BarAttributeMap;
import com.musicquint.api.BarTime;
import com.musicquint.util.ForwardingNavigabgleMap;

public class MQBarAttributeMap extends ForwardingNavigabgleMap<BarTime, AttributeSet> implements BarAttributeMap {

    public MQBarAttributeMap() {
        super(TreeMap::new);
    }
}
