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
        if(attributes.containsKey(key)) {
            return (T) attributes.get(key);
        } else {
            return null;
        }
    }
}
