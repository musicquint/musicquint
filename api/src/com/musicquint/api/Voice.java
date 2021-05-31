package com.musicquint.api;

import java.util.NavigableMap;

public interface Voice extends BarMap<ContentItem> {

    void insert(BarTime key, Note value);

    void insertOptional(BarTime key, Note value);

    boolean fits(BarTime key, Note value);

    BarTime continuity(BarTime key);

    BarTime occurence(BarTime key);

    BarTime length();

    BarTime capacity();
}
