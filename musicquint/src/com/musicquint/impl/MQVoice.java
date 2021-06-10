package com.musicquint.impl;

import java.util.TreeMap;

import com.musicquint.api.BarTime;
import com.musicquint.api.PrincipalSet;
import com.musicquint.api.Voice;
import com.musicquint.util.ForwardingNavigabgleMap;

public class MQVoice extends ForwardingNavigabgleMap<BarTime, PrincipalSet> implements Voice {

    private final BarTime capacity;

    public MQVoice() {
        super(TreeMap::new);
        capacity = BarTime.FOUR_QUARTER;
    }

    @Override
    public PrincipalSet put(BarTime key, PrincipalSet value) {
        if(fits(key, value)) {
            return super.put(key, value);
        } else {
            throw new IllegalStateException("The value "+ value + " does not fit in the voice at " + key);
        }
    }

    public MQVoice(BarTime capacity) {
        super(TreeMap::new);
        this.capacity = capacity;
    }

    @Override
    public BarTime capacity() {
        return capacity;
    }
}
