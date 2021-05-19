package com.musicquint.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.musicquint.core.BarTime;
import com.musicquint.core.Note;
import com.musicquint.core.Pitch;

public class ContentItem extends MQSet<Note> implements com.musicquint.core.ContentItem {

    private List<com.musicquint.core.ContentItem> optionals;

    private int staffNumber;

    public ContentItem() {
        super(TreeSet::new);
        optionals = new ArrayList<>();
    }

    @Override
    public boolean add(Note e) {
        return false;
    }

    @Override
    public int getStaffNumber() {
        return staffNumber;
    }

    @Override
    public BarTime getDuration() {
        return stream().map(Note::getDuration).max(BarTime::compareTo).orElse(BarTime.ZERO);
    }

    @Override
    public SortedSet<Pitch> getPitches() {
        return stream().filter(Note::isPitched).map(Note::getPitch).collect(Collectors.toCollection(TreeSet::new));
    }

    @Override
    public boolean isRest() {
        return getPitches().size() == 0;
    }

    @Override
    public boolean isChord() {
        return getPitches().size() > 1;
    }

    @Override
    public boolean addDecoration(com.musicquint.core.ContentItem decorationItem) {
        return optionals.add(decorationItem);
    }
}
