package com.musicquint.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.musicquint.api.BarTime;
import com.musicquint.api.ContentItem;
import com.musicquint.api.PrincipalItem;
import com.musicquint.api.Voice.OptionalSet;
import com.musicquint.api.Voice.PrincipalSet;
import com.musicquint.util.ForwardingSortedSet;

public class MQPrincipalSet extends ForwardingSortedSet<PrincipalItem> implements PrincipalSet {

    private List<OptionalSet> optionals;

    private BarTime capacity;

    public MQPrincipalSet(Collection<PrincipalItem> collection) {
        super(TreeSet::new);
        Objects.requireNonNull(collection);
        if (!collection.isEmpty()) {
            capacity = collection.stream().map(ContentItem::getDuration).max(BarTime::compareTo).orElse(BarTime.ZERO);
        }
        // Comparator orders all items according to their duration in the inverted
        // natural order resulting in the biggest element being added first.
        collection.stream().sorted((i1, i2) -> BarTime.compareTo(i2, i1)).forEach(this::add);
        optionals = new ArrayList<>();
    }

    public MQPrincipalSet(PrincipalItem... items) {
        this(Stream.of(items).collect(Collectors.toSet()));
    }

    public MQPrincipalSet(BarTime capacity) {
        super(TreeSet::new);
        this.capacity = capacity;
        this.optionals = new ArrayList<>();
    }

    @Override
    public boolean add(PrincipalItem e) {
        if (isEmpty()) {
            if (e.getDuration().isLessOrEqual(getDuration())) {
                capacity = e.getDuration();
                return super.add(e);
            } else {
                throw new IllegalStateException("The PrincipalSet can only hold Items of length " + capacity);
            }
        } else {
            if (e.getDuration().isLessOrEqual(getDuration())) {
                return super.add(e);
            } else {
                throw new IllegalStateException("The PrincipalSet can only hold Items of length " + getDuration());
            }
        }
    }

    @Override
    public void appendOptional(OptionalSet optional) {
        optionals.add(optional);
    }

    @Override
    public void insertOptional(int i, OptionalSet optional) {
        optionals.add(i, optional);
    }

    @Override
    public void clearOptionalList() {
        optionals.clear();
    }

    @Override
    public List<OptionalSet> getOptionalList() {
        return Collections.unmodifiableList(optionals);
    }

    @Override
    public OptionalSet removeOptional(int i) {
        return optionals.remove(i);
    }

    @Override
    public BarTime capacity() {
        return capacity;
    }
}
