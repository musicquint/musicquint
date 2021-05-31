package com.musicquint.api;

import java.util.NavigableMap;

public interface BarMap<T extends BarItem> extends NavigableMap<BarTime, T> {

}
