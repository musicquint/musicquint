package com.musicquint.implementation;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.Supplier;

public abstract class MQMap<K,V > extends AbstractMap<K, V> implements NavigableMap<K, V> {

    private NavigableMap<K, V> mqMap;

    protected MQMap(Supplier<? extends NavigableMap<K, V>> supplier) {
        mqMap = supplier.get();
    }

    @Override
    public Comparator<? super K> comparator() {
        return mqMap.comparator();
    }

    @Override
    public K firstKey() {
        return mqMap.firstKey();
    }

    @Override
    public K lastKey() {
        return mqMap.lastKey();
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return mqMap.lowerEntry(key);
    }

    @Override
    public K lowerKey(K key) {
        return mqMap.lowerKey(key);
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        return mqMap.floorEntry(key);
    }

    @Override
    public K floorKey(K key) {
        return mqMap.floorKey(key);
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return mqMap.ceilingEntry(key);
    }

    @Override
    public K ceilingKey(K key) {
        return mqMap.ceilingKey(key);
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        return mqMap.higherEntry(key);
    }

    @Override
    public K higherKey(K key) {
        return mqMap.higherKey(key);
    }

    @Override
    public Entry<K, V> firstEntry() {
        return mqMap.firstEntry();
    }

    @Override
    public Entry<K, V> lastEntry() {
        return mqMap.lastEntry();
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        return mqMap.pollFirstEntry();
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        return mqMap.pollLastEntry();
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return mqMap.descendingMap();
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return mqMap.navigableKeySet();
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return mqMap.descendingKeySet();
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return mqMap.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return mqMap.headMap(toKey, inclusive);
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return mqMap.tailMap(fromKey, inclusive);
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return mqMap.subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return mqMap.headMap(toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return mqMap.tailMap(fromKey);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return mqMap.entrySet();
    }
}
