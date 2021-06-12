package com.musicquint.impl;

import java.util.HashSet;

import com.musicquint.api.AttributeSet;
import com.musicquint.api.BarAttribute;
import com.musicquint.util.ForwardingSet;

public class MQAttributeSet extends ForwardingSet<BarAttribute> implements AttributeSet{

    public MQAttributeSet() {
        super(HashSet::new);
    }
}
