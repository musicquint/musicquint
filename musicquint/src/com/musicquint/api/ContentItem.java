package com.musicquint.api;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public interface ContentItem extends BarItem, Measurable {

    BarTime getDuration();

    Optional<Pitch> getPitch();

    void setPitch(Pitch pitch);

    int getDots();

    Type getType();

    void addNoteAttribute(NoteAttribute attribute);

    <T extends NoteAttribute> T getNoteAttribute(Class<T> key);

    default boolean isPitched() {
        return !isRest();
    }

    default boolean isRest() {
        return getPitch().isEmpty();
    }

    static <T extends ContentItem> Comparator<T> pitchComparator() {
        return (T c1, T c2) -> {
            Objects.requireNonNull(c1);
            Objects.requireNonNull(c2);
            if(c1.getPitch().isEmpty() && c2.getPitch().isEmpty()) {
                return 0;
            } else if(c1.getPitch().isPresent() && c2.getPitch().isEmpty()) {
                return 1;
            } else if(c1.getPitch().isEmpty() && c2.getPitch().isPresent()) {
                return -1;
            } else {
                Pitch p1 = c1.getPitch().get();
                Pitch p2 = c2.getPitch().get();
                return p1.compareTo(p2);
            }
        };
    }
}