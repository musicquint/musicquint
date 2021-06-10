package com.musicquint.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import com.musicquint.api.OptionalSet;
import com.musicquint.api.PrincipalItem;
import com.musicquint.api.PrincipalSet;
import com.musicquint.util.ForwardingSortedSet;

public class MQPrincipalSet extends ForwardingSortedSet<PrincipalItem> implements PrincipalSet {

    private List<OptionalSet> optionals;

    public MQPrincipalSet() {
        super(TreeSet::new);
    }

    public MQPrincipalSet(Collection<PrincipalItem> collection) {
        this();
        collection.addAll(collection);
    }

    @Override
    public void appendOptional(OptionalSet optional) {
        optionals.add(optional);
    }

    @Override
    public void insertOptional(int i, OptionalSet optional) {
        optionals.add(i, optional);
    }

    @Override
    public void clearOptionalList() {
        optionals.clear();
    }

    @Override
    public List<OptionalSet> getOptionalList() {
        return Collections.unmodifiableList(optionals);
    }

    @Override
    public OptionalSet removeOptional(int i) {
        return optionals.remove(i);

    }
}
