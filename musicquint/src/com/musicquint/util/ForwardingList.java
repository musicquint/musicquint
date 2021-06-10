package com.musicquint.util;

import java.util.AbstractList;
import java.util.List;
import java.util.function.Supplier;

public class ForwardingList<E> extends AbstractList<E> implements List<E> {

    private final List<E> forwardedList;

    protected ForwardingList(Supplier<? extends List<E>> supplier) {
        forwardedList = supplier.get();
    }

    @Override
    public void add(int index, E element) {
        forwardedList.add(index, element);
    }

    @Override
    public E remove(int index) {
        return forwardedList.remove(index);
    }

    @Override
    public E set(int index, E element) {
        return forwardedList.set(index, element);
    }

    @Override
    public E get(int index) {
        return forwardedList.get(index);
    }

    @Override
    public int size() {
        return forwardedList.size();
    }
}
