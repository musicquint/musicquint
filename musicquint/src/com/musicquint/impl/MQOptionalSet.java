package com.musicquint.impl;

import java.util.Collection;
import java.util.TreeSet;

import com.musicquint.api.OptionalItem;
import com.musicquint.api.OptionalSet;
import com.musicquint.util.ForwardingSortedSet;

public class MQOptionalSet extends ForwardingSortedSet<OptionalItem> implements OptionalSet{

    public MQOptionalSet() {
        super(TreeSet::new);
    }

    public MQOptionalSet(Collection<OptionalItem> collection) {
        this();
    }
}
