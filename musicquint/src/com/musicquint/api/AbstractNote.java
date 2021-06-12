package com.musicquint.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractNote implements ContentItem {

    private Pitch pitch;

    private final BarTime duration;

    private final int dots;

    private final Type type;

    private final Map<Class<? extends NoteAttribute>, NoteAttribute> attributes;

    protected AbstractNote(Pitch pitch, BarTime duration, int dots, Type type) {
        this.pitch = pitch;
        this.duration = Objects.requireNonNull(duration);
        this.dots = dots;
        this.type = Objects.requireNonNull(type);
        attributes = new HashMap<>();
    }

    @Override
    public BarTime getDuration() {
        return duration;
    }

    @Override
    public Pitch getPitch() {
        return pitch;
    }

    @Override
    public void setPitch(Pitch pitch) {
        this.pitch = Objects.requireNonNull(pitch);
    }

    @Override
    public int getDots() {
        return dots;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void addNoteAttribute(NoteAttribute attribute) {
        attributes.put(attribute.getClass(), attribute);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends NoteAttribute> T getNoteAttribute(Class<T> key) {
        if (attributes.containsKey(key)) {
            return (T) attributes.get(key);
        } else {
            return null;
        }
    }

    @Override
    public int compareTo(ContentItem o) {
        return getPitch().asInt();
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
        return getClass().getSimpleName() + " [pitch=" + pitch + ", duration=" + duration + ", dots=" + dots + ", type="
                + type + "]";
    }

}
