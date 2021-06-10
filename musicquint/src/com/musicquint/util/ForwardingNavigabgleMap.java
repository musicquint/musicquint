package com.musicquint.util;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.Supplier;

public class ForwardingNavigabgleMap<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>{

    private final NavigableMap<K, V> forwardedMap;

    protected ForwardingNavigabgleMap(Supplier<? extends NavigableMap<K, V>> supplier) {
        forwardedMap = supplier.get();
    }

    @Override
    public V put(K key, V value) {
        return forwardedMap.put(key, value);
    }

    @Override
    public Comparator<? super K> comparator() {
        return forwardedMap.comparator();
    }

    @Override
    public K firstKey() {
        return forwardedMap.firstKey();
    }

    @Override
    public K lastKey() {
        return forwardedMap.lastKey();
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return forwardedMap.lowerEntry(key);
    }

    @Override
    public K lowerKey(K key) {
        return forwardedMap.lowerKey(key);
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        return forwardedMap.floorEntry(key);
    }

    @Override
    public K floorKey(K key) {
        return forwardedMap.floorKey(key);
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return forwardedMap.ceilingEntry(key);
    }

    @Override
    public K ceilingKey(K key) {
        return forwardedMap.ceilingKey(key);
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        return forwardedMap.higherEntry(key);
    }

    @Override
    public K higherKey(K key) {
        return forwardedMap.higherKey(key);
    }

    @Override
    public Entry<K, V> firstEntry() {
        return forwardedMap.firstEntry();
    }

    @Override
    public Entry<K, V> lastEntry() {
        return forwardedMap.lastEntry();
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        return forwardedMap.pollFirstEntry();
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        return forwardedMap.pollLastEntry();
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return forwardedMap.descendingMap();
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return forwardedMap.navigableKeySet();
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return forwardedMap.descendingKeySet();
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return forwardedMap.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return forwardedMap.headMap(toKey, inclusive);
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return forwardedMap.tailMap(fromKey, inclusive);
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return forwardedMap.subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return forwardedMap.headMap(toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return forwardedMap.tailMap(fromKey);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return forwardedMap.entrySet();
    }
}
