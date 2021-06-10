package com.musicquint.api;

import java.util.Collection;

import com.musicquint.impl.MQOptionalSet;

public interface OptionalSet extends ContentSet<OptionalItem> {

    public static OptionalSet create() {
        return new MQOptionalSet();
    }

    public static OptionalSet create(Collection<OptionalItem> collection) {
        return new MQOptionalSet(collection);
    }

}
