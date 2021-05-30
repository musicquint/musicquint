package com.musicquint.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.musicquint.api.BarItem;
import com.musicquint.api.BarTime;
import com.musicquint.api.NoteAttribute;
import com.musicquint.api.Pitch;
import com.musicquint.api.Type;

public abstract class AbstractNote implements BarItem, Comparable<AbstractNote> {

    public enum Category {
        PRINCIPAL, OPTIONAL
    }

    private Pitch pitch;

    private final BarTime duration;

    private final Type type;

    private final int dots;

    private int staffNumber;

    private Map<Class<? extends NoteAttribute>, NoteAttribute> attributes;

    public AbstractNote(Pitch pitch, BarTime duration, Type type, int dots) {
        BarTime dotFactor = calculateDotFactor(dots);
        if (!duration.equals(type.asBarTime().multiply(dotFactor))) {
            throw new IllegalArgumentException("The duration " + duration + " Cannot be matched with the type " + type
                    + " and the number of dots " + dots);
        }
        attributes = new HashMap<>();
        this.pitch = pitch;
        this.duration = Objects.requireNonNull(duration);
        this.type = Objects.requireNonNull(type);
        this.dots = dots;
    }

    public AbstractNote(Pitch pitch, Type type, int dots) {
        BarTime dotFactor = calculateDotFactor(dots);
        BarTime noteDuration = type.asBarTime();
        duration = noteDuration.multiply(dotFactor);
        attributes = new HashMap<>();
        this.pitch = pitch;
        this.type = Objects.requireNonNull(type);
        this.dots = dots;
    }

    private BarTime calculateDotFactor(int dots) {
        BarTime dotsDuration = BarTime.of(1);
        for (int i = 0; i < dots; i++) {
            dotsDuration = BarTime.add(BarTime.of(1), dotsDuration.multiply(BarTime.of(1, 2)));
        }
        return dotsDuration;
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
        if (attributes.containsKey(attributeKey)) {
            return (T) attributes.get(attributeKey);
        } else {
            return null;
        }
    }

    public Set<NoteAttribute> getAttributesAsList() {
        return attributes.values().stream().collect(Collectors.toSet());
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
        if (isPitched()) {
            return pitch.asInt();
        } else {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dots;
        result = prime * result + ((duration == null) ? 0 : duration.hashCode());
        result = prime * result + ((pitch == null) ? 0 : pitch.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractNote other = (AbstractNote) obj;
        if (dots != other.dots)
            return false;
        if (duration == null) {
            if (other.duration != null)
                return false;
        } else if (!duration.equals(other.duration))
            return false;
        if (pitch == null) {
            if (other.pitch != null)
                return false;
        } else if (!pitch.equals(other.pitch))
            return false;
        if (type != other.type)
            return false;
        return true;
    }

    @Override
    public String toString() {
        // TODO implement shorter toString method
        return getClass().getSimpleName() + " [pitch=" + pitch + ", duration=" + duration + ", type=" + type + ", dots="
                + dots + "]";
    }
}
