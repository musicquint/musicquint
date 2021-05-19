package com.musicquint.implementation;

import java.util.AbstractList;
import java.util.List;
import java.util.function.Supplier;

public abstract class MQList<E> extends AbstractList<E> {

    private List<E> mqList;

    protected MQList(Supplier<? extends List<E>> supplier) {
        mqList = supplier.get();
    }

    @Override
    public void add(int index, E element) {
        mqList.add(index, element);
    }

    @Override
    public E remove(int index) {
        return mqList.remove(index);
    }

    @Override
    public E get(int index) {
        return mqList.get(index);
    }

    @Override
    public int size() {
        return mqList.size();
    }

}
