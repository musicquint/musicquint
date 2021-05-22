package com.musicquint.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractNote implements BarItem, Comparable<AbstractNote> {

    private Pitch pitch;

    private final BarTime duration;

    private final Type type;

    private final int dots;

    private int staffNumber;

    private Map<Class<? extends NoteAttribute>, NoteAttribute> attributes;

    public AbstractNote(Pitch pitch, BarTime duration, Type type, int dots) {
        attributes = new HashMap<>();
        this.pitch = pitch;
        this.duration = Objects.requireNonNull(duration);
        this.type = Objects.requireNonNull(type);
        this.dots = dots;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public BarTime getDuration() {
        return duration;
    }

    public Type getType() {
        return type;
    }

    public int getDots() {
        return dots;
    }

    public void addAttribute(NoteAttribute attribute) {
        attributes.put(attribute.getClass(), attribute);
    }

    @SuppressWarnings("unchecked")
    public <T extends NoteAttribute> T getAttribute(Class<T> attributeKey) {
        if(attributes.containsKey(attributeKey)) {
            return (T) attributes.get(attributeKey);
        } else {
            return null;
        }
    }

    public boolean isRest() {
        return pitch == null;
    }

    public boolean isPitched() {
        return !isRest();
    }

    @Override
    public int getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(int staffNumber) {
        this.staffNumber = staffNumber;
    }

    public abstract Category getCategory();

    @Override
    public int compareTo(AbstractNote o) {
        if(isPitched()) {
            return pitch.asInt();
        } else {
            return -1;
        }
    }

    public enum Category {
        PRINCIPAL, OPTIONAL
    }
}
