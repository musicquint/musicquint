package com.musicquint.api;

import java.util.Set;

import com.musicquint.impl.MQAttributeSet;

public interface AttributeSet extends Set<BarAttribute> {

    public static AttributeSet create() {
        return new MQAttributeSet();
    }

}
