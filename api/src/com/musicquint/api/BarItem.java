package com.musicquint.api;

import java.util.SortedSet;

//TODO documentation
public interface BarItem<E> extends SortedSet<E> {

    BarTime getDuration();

}
