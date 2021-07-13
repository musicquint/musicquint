package com.musicquint.io;

public @interface XMLTag {

    String tagName();

    Class<?> inputType() default String.class;

    TagType tagType();

    public enum TagType {
        START_TAG, CHARACTER_TAG, END_TAG;
    }
}
