package com.musicquint.util;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Supplier;

public class ForwardingSet<E> extends AbstractSet<E> implements Set<E> {

    private final Set<E> forwardedSet;

    protected ForwardingSet(Supplier<? extends Set<E>> supplier) {
        forwardedSet = supplier.get();
    }

    @Override
    public boolean add(E e) {
        return forwardedSet.add(e);
    }

    @Override
    public Iterator<E> iterator() {
        return forwardedSet.iterator();
    }

    @Override
    public int size() {
        return forwardedSet.size();
    }
}
