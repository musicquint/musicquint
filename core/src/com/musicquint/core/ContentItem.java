package com.musicquint.core;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.musicquint.util.MQSet;

public class ContentItem extends MQSet<AbstractNote> {

    private List<SortedSet<AbstractNote>> optionals;

    public ContentItem() {
        super(TreeSet::new);
        optionals = new ArrayList<>();
    }

    @Override
    public boolean add(AbstractNote e) {
        switch (e.getCategory()) {
        case OPTIONAL:
            SortedSet<AbstractNote> optionalSet = new TreeSet<>();
            optionalSet.add(e);
            optionals.add(optionalSet);
        case PRINCIPAL:
            return add(e);
        default:
            throw new RuntimeException("Unknown Categoryt");

        }
    }

    public BarTime getDuration() {
        return stream().map(AbstractNote::getDuration).max(BarTime::compareTo).orElse(BarTime.ZERO);
    }

    public SortedSet<Pitch> getPitches() {
        return stream().filter(AbstractNote::isPitched).map(AbstractNote::getPitch)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public boolean isRest() {
        return getPitches().isEmpty();
    }

    public boolean isChord() {
        return getPitches().size() > 1;
    }
}
