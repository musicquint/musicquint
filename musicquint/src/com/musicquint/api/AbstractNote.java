package com.musicquint.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractNote implements ContentItem {

    private Pitch pitch;

    private final BarTime duration;

    private final int dots;

    private final Type type;

    private final Map<Class<? extends NoteAttribute>, NoteAttribute> attributes;

    protected AbstractNote(Builder<?> builder) {
        this.pitch = builder.getPitch();
        this.duration = Objects.requireNonNull(builder.duration);
        this.dots = builder.dots;
        this.type = Objects.requireNonNull(builder.type);
        attributes = new HashMap<>();
    }

    @Override
    public BarTime getDuration() {
        return duration;
    }

    @Override
    public Optional<Pitch> getPitch() {
        return Optional.ofNullable(pitch);
    }

    @Override
    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
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
        //FIXME TODO this is not correct and needs to be fixed
        return pitch.asInt();
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
        StringBuilder dots = new StringBuilder();
        for (int i = 0; i < this.dots; i++) {
            dots.append(".");
        }
        return getPitch() + getType().getSimpleName() + dots;
    }

    public static abstract class Builder<T extends AbstractNote> {

        private BarTime duration;

        private Pitch pitch;

        private Pitch.Builder pitchBuilder;

        private boolean isRest;

        private int dots;

        private Type type;

        public Builder() {
            //Standard Constructor
        }

        public Builder(Builder<?> builder) {
            this.duration = builder.duration;
            this.pitch = builder.pitch;
            this.pitchBuilder = builder.pitchBuilder;
            this.dots = builder.dots;
            this.type = builder.type;
            this.isRest = builder.isRest;
        }

        public Builder<T> duration(BarTime duration) {
            this.duration = Objects.requireNonNull(duration);
            return this;
        }
        
        public Builder<T> isRest(boolean isRest) {
        	this.isRest = isRest;
        	return this;
        }

        public Builder<T> pitch(Pitch pitch) {
            this.pitch = pitch;
            return this;
        }

        public Builder<T> step(Step step) {
            if(pitchBuilder == null) {
                pitchBuilder = new Pitch.Builder();
            }
            pitchBuilder.step(step);
            return this;
        }

        public Builder<T> alter(Alter alter) {
            if(pitchBuilder == null) {
                pitchBuilder = new Pitch.Builder();
            }
            pitchBuilder.alter(alter);
            return this;
        }

        public Builder<T> octave(Octave octave) {
            if(pitchBuilder == null) {
                pitchBuilder = new Pitch.Builder();
            }
            pitchBuilder.octave(octave);
            return this;
        }

        public Builder<T> dots(int dots) {
            this.dots = dots;
            return this;
        }

        public Builder<T> type(Type type) {
            this.type = type;
            return this;
        }

        private Pitch getPitch() {
            if(isRest == false) {
                if (pitch != null) {
                    return pitch;
                } else {
                    return pitchBuilder.build();
                }
            } else {
                return null;
            }
        }

        public abstract T build();
    }
}
