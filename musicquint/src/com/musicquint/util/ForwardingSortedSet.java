package com.musicquint.util;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.function.Supplier;

public class ForwardingSortedSet<E> extends AbstractSet<E> implements SortedSet<E> {

    private final SortedSet<E> forwardedSet;

    protected ForwardingSortedSet(Supplier<? extends SortedSet<E>> supplier) {
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

    @Override
    public Comparator<? super E> comparator() {
        return forwardedSet.comparator();
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return forwardedSet.subSet(fromElement, toElement);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return forwardedSet.headSet(toElement);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return forwardedSet.tailSet(fromElement);
    }

    @Override
    public E first() {
        return forwardedSet.first();
    }

    @Override
    public E last() {
        return forwardedSet.last();
    }
}
