package com.musicquint.core;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Voice extends AbstractMap<BarTime, ContentItem> implements NavigableMap<BarTime, ContentItem> {

    private final TreeMap<BarTime, ContentItem> voice;

    private final BarTime capacity;

    public Voice() {
        voice = new TreeMap<>();
        capacity = BarTime.FOUR_QUARTER;
    }

    public Voice(BarTime capacity) {
        voice = new TreeMap<>();
        this.capacity = capacity;
    }

    @Override
    public ContentItem put(BarTime key, ContentItem value) {
        return super.put(key, value);
    }

    @Override
    public Comparator<? super BarTime> comparator() {
        return voice.comparator();
    }

    @Override
    public BarTime firstKey() {
        return voice.firstKey();
    }

    @Override
    public BarTime lastKey() {
        return voice.lastKey();
    }

    @Override
    public Entry<BarTime, ContentItem> lowerEntry(BarTime key) {
        return voice.lowerEntry(key);
    }

    @Override
    public BarTime lowerKey(BarTime key) {
        return voice.lowerKey(key);
    }

    @Override
    public Entry<BarTime, ContentItem> floorEntry(BarTime key) {
        return voice.floorEntry(key);
    }

    @Override
    public BarTime floorKey(BarTime key) {
        return voice.floorKey(key);
    }

    @Override
    public Entry<BarTime, ContentItem> ceilingEntry(BarTime key) {
        return voice.ceilingEntry(key);
    }

    @Override
    public BarTime ceilingKey(BarTime key) {
        return voice.ceilingKey(key);
    }

    @Override
    public Entry<BarTime, ContentItem> higherEntry(BarTime key) {
        return voice.higherEntry(key);
    }

    @Override
    public BarTime higherKey(BarTime key) {
        return voice.higherKey(key);
    }

    @Override
    public Entry<BarTime, ContentItem> firstEntry() {
        return voice.firstEntry();
    }

    @Override
    public Entry<BarTime, ContentItem> lastEntry() {
        return voice.lastEntry();
    }

    @Override
    public Entry<BarTime, ContentItem> pollFirstEntry() {
        return voice.pollFirstEntry();
    }

    @Override
    public Entry<BarTime, ContentItem> pollLastEntry() {
        return voice.pollLastEntry();
    }

    @Override
    public NavigableMap<BarTime, ContentItem> descendingMap() {
        return voice.descendingMap();
    }

    @Override
    public NavigableSet<BarTime> navigableKeySet() {
        return voice.navigableKeySet();
    }

    @Override
    public NavigableSet<BarTime> descendingKeySet() {
        return voice.descendingKeySet();
    }

    @Override
    public NavigableMap<BarTime, ContentItem> subMap(BarTime fromKey, boolean fromInclusive, BarTime toKey,
            boolean toInclusive) {
        return voice.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    @Override
    public NavigableMap<BarTime, ContentItem> headMap(BarTime toKey, boolean inclusive) {
        return voice.headMap(toKey, inclusive);
    }

    @Override
    public NavigableMap<BarTime, ContentItem> tailMap(BarTime fromKey, boolean inclusive) {
        return voice.tailMap(fromKey, inclusive);
    }

    @Override
    public SortedMap<BarTime, ContentItem> subMap(BarTime fromKey, BarTime toKey) {
        return voice.subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<BarTime, ContentItem> headMap(BarTime toKey) {
        return voice.headMap(toKey);
    }

    @Override
    public SortedMap<BarTime, ContentItem> tailMap(BarTime fromKey) {
        return voice.tailMap(fromKey);
    }

    @Override
    public Set<Entry<BarTime, ContentItem>> entrySet() {
        return voice.entrySet();
    }
}