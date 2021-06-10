package com.musicquint.api;

import java.util.Collection;
import java.util.List;

import com.musicquint.impl.MQPrincipalSet;

public interface PrincipalSet extends ContentSet<PrincipalItem> {

    public static PrincipalSet create() {
        return new MQPrincipalSet();
    }

    public static PrincipalSet create(Collection<PrincipalItem> collection) {
        return new MQPrincipalSet(collection);
    }

    void appendOptional(OptionalSet optional);

    void insertOptional(int i, OptionalSet optional);

    OptionalSet removeOptional(int i);

    void clearOptionalList();

    List<OptionalSet> getOptionalList();

}
