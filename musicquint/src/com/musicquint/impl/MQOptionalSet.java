package com.musicquint.impl;

import java.util.Collection;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.musicquint.api.OptionalItem;
import com.musicquint.api.Voice.OptionalSet;
import com.musicquint.util.ForwardingSortedSet;

public class MQOptionalSet extends ForwardingSortedSet<OptionalItem> implements OptionalSet{

    public MQOptionalSet() {
        super(TreeSet::new);
    }

    public MQOptionalSet(Collection<OptionalItem> collection) {
        this();
    }

    public MQOptionalSet(OptionalItem... items) {
        this(Stream.of(items).collect(Collectors.toSet()));
    }
}
