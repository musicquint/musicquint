package com.musicquint.core;

import java.util.SortedSet;

public interface ContentItem extends BarItem, SortedSet<Pitch> {

    BarTime getDuration();

    int getDots();

    Type getType();

    ContentType getContentType();

    default boolean isPitched() {
        return !isRest();
    }

    default boolean isRest() {
        return size() == 0;
    }

    default boolean isChord() {
        return size() > 1;
    }

    <T extends ContentAttribute> T getContentAttribute(Class<T> attributeClass);

    void addContentAttribute(ContentAttribute attribute);

    public enum ContentType {
        PRINCIPAL, OPTIONAL
    }
}
