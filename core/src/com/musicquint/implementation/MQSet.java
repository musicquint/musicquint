package com.musicquint.implementation;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.function.Supplier;

public abstract class MQSet<E> extends AbstractSet<E> implements SortedSet<E> {

    private SortedSet<E> mqSet;

    protected MQSet(Supplier<? extends SortedSet<E>> supplier) {
        this.mqSet = supplier.get();
    }

    @Override
    public boolean add(E e) {
        return mqSet.add(e);
    }

    @Override
    public Comparator<? super E> comparator() {
        return mqSet.comparator();
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return mqSet.subSet(fromElement, toElement);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return mqSet.headSet(toElement);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return mqSet.tailSet(fromElement);
    }

    @Override
    public E first() {
        return mqSet.first();
    }

    @Override
    public E last() {
        return mqSet.last();
    }

    @Override
    public Iterator<E> iterator() {
        return mqSet.iterator();
    }

    @Override
    public int size() {
        return mqSet.size();
    }
}
